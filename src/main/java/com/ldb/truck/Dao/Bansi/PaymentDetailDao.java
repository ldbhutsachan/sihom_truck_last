package com.ldb.truck.Dao.Bansi;

import com.ldb.truck.Model.Bansi.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentDetailDao {

    private final JdbcTemplate jdbcTemplate;

    // 🔹 ดึงข้อมูลหลักจาก tb_accounting (filter ตามตัวเลือก)
    public List<PaymentDetailModel> findPaymentDetailsCursor(
            PaymentDetailReq req, String role, int size) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.key_id, a.bill_No, a.title, a.currency, a.exchange_rate, ");
        sql.append("a.date, a.datermine_date, a.date_create, a.data_type, ");
        sql.append("a.reference, a.reference_number, a.remark, a.internal_remark, ");
        sql.append("a.tag, a.file, s.supplier_name, a.supplierid, a.bill_status, ");
        sql.append("pt.pid as payId, pt.type_name, pt.type_pay as type_of, ");
        sql.append("rt.req_id, rt.req_name, it.itemTypeid, it.itemtype_Name, ");
        sql.append("l.USER_LOGIN, a.basi_approve_date, a.bansi_approveby, ");
        sql.append("a.returnby, a.return_date, b.account_name, b.account_no, ");
        sql.append("b.bank_name, b.bank_name_lao ");
        sql.append("FROM tb_accounting a ");
        sql.append("INNER JOIN pay_type pt ON a.pay_typeid = pt.pid ");
        sql.append("LEFT JOIN LOGIN l ON a.user_id = l.KEY_ID ");
        sql.append("LEFT JOIN supplier s ON a.supplierid = s.supplierid ");
        sql.append("LEFT JOIN request_item_type rt ON pt.req_id = rt.req_id ");
        sql.append("LEFT JOIN item_type it ON rt.item_typeid = it.itemTypeid ");
        sql.append("LEFT JOIN tb_bank b ON a.b_id = b.b_id ");

        List<Object> params = new ArrayList<>();
        List<String> conditions = new ArrayList<>();

        // 🔹 filter ปกติ
        if (req.getStartDate() != null && !req.getStartDate().isEmpty()
                && req.getEndDate() != null && !req.getEndDate().isEmpty()) {
            conditions.add("a.date_create BETWEEN ? AND ?");
            params.add(req.getStartDate() + " 00:00:00");
            params.add(req.getEndDate() + " 23:59:59");
        }

        if (req.getItemTypeid() != null) {
            conditions.add("it.itemTypeid = ?");
            params.add(req.getItemTypeid());
        }

        if (req.getReq_id() != null) {
            conditions.add("rt.req_id = ?");
            params.add(req.getReq_id());
        }

        if (req.getPid() != null) {
            conditions.add("pt.pid = ?");
            params.add(req.getPid());
        }

        if (req.getBillNo() != null && !req.getBillNo().isEmpty()) {
            conditions.add("a.bill_No = ?");
            params.add(req.getBillNo());
        }
        if (req.getBillStatus() != null && !req.getBillStatus().isEmpty()) {
            conditions.add("a.bill_status = ?");
            params.add(req.getBillStatus());
        }


        //  cursor logic (สำคัญสุด)
        if (req.getLastDate() != null && req.getLastKeyId() != null) {
            conditions.add("(a.date < ? OR (a.date = ? AND a.key_id < ?))");
            params.add(req.getLastDate());
            params.add(req.getLastDate());
            params.add(req.getLastKeyId());
        }

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        //  order ต้องตรงกับ cursor
        sql.append(" ORDER BY a.date DESC, a.key_id DESC ");
        sql.append(" LIMIT ?");

        params.add(size);

        List<PaymentDetailModel> mainList = jdbcTemplate.query(
                sql.toString(),
                (rs, rowNum) -> mapPaymentDetailBase(rs),
                params.toArray()
        );

        // 🔥 batch load list items
        if (mainList.isEmpty()) return mainList;

        List<String> billNos = mainList.stream()
                .map(PaymentDetailModel::getBillNo)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<String, List<PaymentDetailListModel>> itemMap =
                findListItemsByBillNos(billNos);

        for (PaymentDetailModel m : mainList) {
            m.setListItems(itemMap.getOrDefault(m.getBillNo(), new ArrayList<>()));
        }

        return mainList;
    }



    // 🔹 Mapper สำหรับ PaymentDetailModel
    private PaymentDetailModel mapPaymentDetailBase(ResultSet rs) throws SQLException {
        PaymentDetailModel model = new PaymentDetailModel();

        model.setKeyId(rs.getLong("key_id"));
        model.setBillNo(rs.getString("bill_No"));
        model.setBill_status(rs.getString("bill_status"));
        model.setDate_create(rs.getString("date_create"));
        model.setTitle(rs.getString("title"));
        model.setCurrency(rs.getString("currency"));
        model.setExchangeRate(rs.getDouble("exchange_rate"));
        model.setDate(rs.getString("date"));
        model.setDatermineDate(rs.getString("datermine_date"));
        model.setReference(rs.getString("reference"));
        model.setReferenceNumber(rs.getString("reference_number"));
        model.setRemark(rs.getString("remark"));
        model.setInternalRemark(rs.getString("internal_remark"));
        model.setTag(rs.getString("tag"));

        String fileStr = rs.getString("file");
        model.setFile(fileStr);
        model.setFileList(fileStr != null ? Arrays.asList(fileStr.split(",")) : new ArrayList<>());

        model.setPayId(rs.getLong("payId"));
        model.setPaytype(rs.getString("type_name"));
        model.setType_of(rs.getString("type_of"));
        model.setReq_id(rs.getLong("req_id"));
        model.setSmallProject(rs.getString("req_name"));
        model.setItemTypeid(rs.getLong("itemTypeid"));
        model.setBigProject(rs.getString("itemtype_Name"));
        model.setSupplierid(rs.getString("supplierid"));
        model.setSupplier_name(rs.getString("supplier_name"));
        model.setUser(rs.getString("USER_LOGIN"));

        model.setBansi_approveby(rs.getString("bansi_approveby"));
        model.setBasi_approve_date(rs.getString("basi_approve_date"));
        model.setReturnby(rs.getString("returnby"));
        model.setReturn_date(rs.getString("return_date"));

        model.setAccount_name(rs.getString("account_name"));
        model.setAccount_no(rs.getString("account_no"));
        model.setBank_name(rs.getString("bank_name"));
        model.setBank_lao_name(rs.getString("bank_name_lao"));

        return model;
    }

    //  ดึงข้อมูล list item ตาม billNo
    public Map<String, List<PaymentDetailListModel>> findListItemsByBillNos(List<String> billNos) {

        String inSql = String.join(",", Collections.nCopies(billNos.size(), "?"));

        String sql = "SELECT id, key_id, bill_No, list_name, qty, unit, price, usd_price, reduce, reduce_status, tax, tax_status " +
                "FROM tb_accounting_list WHERE bill_No IN (" + inSql + ")";

        List<PaymentDetailListModel> items = jdbcTemplate.query(sql, billNos.toArray(), (rs, rowNum) -> {
            PaymentDetailListModel item = new PaymentDetailListModel();
            item.setId(rs.getLong("id"));
            item.setKeyId(rs.getLong("key_id"));
            item.setBill_No(rs.getString("bill_No"));
            item.setListName(rs.getString("list_name"));
            item.setQty(rs.getDouble("qty"));
            item.setUnit(rs.getString("unit"));
            item.setPrice(rs.getDouble("price"));
            item.setUsd_price(rs.getDouble("usd_price"));
            item.setReduce(rs.getDouble("reduce"));
            item.setReduceStatus(rs.getString("reduce_status"));
            item.setTax(rs.getDouble("tax"));
            item.setTaxStatus(rs.getString("tax_status"));
            return item;
        });

        return items.stream().collect(Collectors.groupingBy(PaymentDetailListModel::getBill_No));
    }
    // query interviewee
    public List<IntervieweeModel> findInterviewees(String status, String startDate, String endDate) {

        String sql = "SELECT i.key_id, i.interviewee, i.position, i.salary, i.currency, i.experience, i.age, i.tel, i.tel1, i.status, " +
                "i.interview_date, i.interview_time, i.interviewer1, i.interviewer2, i.interviewer3, " +
                "i.image AS intervieweeImage, i.profile AS cv, i.date_create, l.USER_LOGIN AS createBy " +
                "FROM tb_interviewer i " +
                "LEFT JOIN LOGIN l ON i.user_id = l.KEY_ID";

        List<Object> params = new ArrayList<>();
        List<String> conditions = new ArrayList<>();

        // 🔹 Filter status
        if (status != null && !status.isEmpty()) {
            conditions.add("i.status = ?");
            params.add(status);
        }

        // 🔹 Filter date range (เฉพาะเมื่อ startDate & endDate ไม่เป็น null พร้อมกัน)
        if (startDate != null && !startDate.isEmpty()
                && endDate != null && !endDate.isEmpty()) {

            conditions.add("DATE(i.date_create) BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }

        //  Build WHERE clause
        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }

        sql += " ORDER BY i.date_create ASC"; // optional: เรียงใหม่ให้สวย

        log.info("SQL = {}, params = {}", sql, params);

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            IntervieweeModel model = new IntervieweeModel();

            model.setKey_id(rs.getInt("key_id"));
            model.setInterviewee(rs.getString("interviewee"));
            model.setPosition(rs.getString("position"));
            model.setSalary(rs.getString("salary"));
            model.setCurrency(rs.getString("currency"));
            model.setExperience(rs.getString("experience"));
            model.setAge(rs.getInt("age"));
            model.setTel(rs.getString("tel"));
            model.setTel1(rs.getString("tel1"));
            model.setStatus(rs.getString("status"));

            model.setInterviewDate(rs.getString("interview_date"));
            model.setInterviewTime(rs.getString("interview_time"));

            model.setInterviewer1(rs.getString("interviewer1"));
            model.setInterviewer2(rs.getString("interviewer2"));
            model.setInterviewer3(rs.getString("interviewer3"));

            model.setIntervieweeImage(rs.getString("intervieweeImage"));
            model.setCv(rs.getString("cv"));

            model.setDateCreate(rs.getString("date_create"));
            model.setCreateBy(rs.getString("createBy"));

            // Add calculation of InterStatus
            String interviewDateStr = rs.getString("interview_date");
            String intervieweeStatus = rs.getString("status");   // ดึงค่า status จาก DB ก่อน

// ตั้งค่า default
            model.setInterStatus("NODATE");

// เช็คเฉพาะคนที่ intervieweeStatus = "wait"
            if ("wait".equalsIgnoreCase(intervieweeStatus)) {

                if (interviewDateStr != null && !interviewDateStr.isEmpty()) {

                    LocalDate today = LocalDate.now();
                    LocalDate interviewDate = LocalDate.parse(interviewDateStr);

                    if (today.isBefore(interviewDate)) {
                        model.setInterStatus("COMING");    // ยังไม่ถึงวัน
                    } else if (today.isEqual(interviewDate)) {
                        model.setInterStatus("EXPIRED");   // วันนี้
                    } else {
                        model.setInterStatus("OVERDUE");   // เลยวัน
                    }

                } else {
                    model.setInterStatus("NODATE");        // ไม่มีวันที่
                }

            } else {
                // ถ้า intervieweeStatus ไม่ใช่ wait ไม่ต้องคำนวณ
                model.setInterStatus("DONE");              // ສຳພາດແລ້ວ
            }
            return model;
        }, params.toArray());

    }

    //ReportAccounting Dao
    public List<AccountingReportModel> reportAccounting(
            String big_project_id,
            String small_project_id,
            String pay_type_id,
            String type_of_pay,
            String startDate,
            String endDate,
            String role
    ) {
        String sql = "SELECT * FROM v_accounting_report";

        List<Object> params = new ArrayList<>();
        List<String> conditions = new ArrayList<>();

        // always force bill_status = ok
        conditions.add("bill_status = 'ok'");

        // Map field → value
        Map<String, String> filters = Map.of(
                "big_project_id", big_project_id,
                "small_project_id", small_project_id,
                "pay_type_id", pay_type_id,
                "type_of", type_of_pay
        );

        // loop ตรวจสอบค่า
        filters.forEach((field, value) -> {
            if (value != null && !value.isEmpty()) {
                conditions.add(field + " = ?");
                params.add(value);
            }
        });

        // Filter date range
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            conditions.add("DATE(basi_approve_date) BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }

        // Filter role (SUPERACCOUNT + SUPERBANSI)
//        if ("SUPERACCOUNT".equalsIgnoreCase(role) || "SUPERBANSI".equalsIgnoreCase(role)) {
//            conditions.add("role = ?");
//            params.add(role.toUpperCase());
//        }

        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }

        sql += " ORDER BY date ASC";

        log.info("SQL = {}, params = {}", sql, params);

        return jdbcTemplate.query(sql, params.toArray(), (rs, rowNum) -> {
            AccountingReportModel model = new AccountingReportModel();

            model.setKeyId(rs.getInt("key_id"));
            model.setBansiId(rs.getInt("Bansi_id"));
            model.setDate_create(rs.getDate("date_create"));
            model.setBasi_approve_date(rs.getDate("basi_approve_date"));
            model.setBigProjectId(rs.getInt("big_project_id"));
            model.setBigProject(rs.getString("big_project"));
            model.setSmallProjectId(rs.getInt("small_project_id"));
            model.setSmallProject(rs.getString("small_project"));
            model.setPayTypeId(rs.getInt("pay_type_id"));
            model.setPayType(rs.getString("pay_type"));
            model.setTypeOf(rs.getString("type_of"));
            model.setSupplierName(rs.getString("supplier_name"));
            model.setBunsiName(rs.getString("BunsiName"));
            model.setTitle(rs.getString("title"));
            model.setExchangeRate(rs.getString("exchange_rate"));
            model.setDatermineDate(rs.getDate("datermine_date"));
            model.setReferenceNumber(rs.getString("reference_number"));
            model.setReference(rs.getString("reference"));
            model.setRemark(rs.getString("remark"));
            model.setInternalRemark(rs.getString("internal_remark"));
            model.setTag(rs.getString("tag"));
//            model.setFile(rs.getString("file"));
            String fileStr = rs.getString("file");
            model.setFile(fileStr); // เก็บเหมือนเดิม
            if (fileStr != null && !fileStr.isEmpty()) {
                // แยกเป็น list ตาม comma
                List<String> fileList = Arrays.asList(fileStr.split(","));
                model.setFileList(fileList);
            } else {
                model.setFileList(new ArrayList<>()); // ถ้าไม่มีไฟล์
            }
            model.setBillNo(rs.getString("bill_No"));
            model.setBill_status(rs.getString("bill_status"));
            model.setCurrency(rs.getString("currency"));
            model.setPrice(rs.getDouble("price"));
            model.setUsd_price(rs.getDouble("usd_price"));
            model.setRole(rs.getString("role"));
            model.setBank_account_name(rs.getString("bank_account_name"));
            model.setBank_account_no(rs.getString("bank_account_no"));
            model.setBank_name(rs.getString("bank_name"));


            return model;
        });
    }






}
