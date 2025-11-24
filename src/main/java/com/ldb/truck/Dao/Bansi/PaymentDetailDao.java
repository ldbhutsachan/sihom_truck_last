package com.ldb.truck.Dao.Bansi;

import com.ldb.truck.Model.Bansi.IntervieweeModel;
import com.ldb.truck.Model.Bansi.PaymentDetailListModel;
import com.ldb.truck.Model.Bansi.PaymentDetailModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentDetailDao {

    private final JdbcTemplate jdbcTemplate;

    // 🔹 ดึงข้อมูลหลักจาก tb_accounting (filter ตามตัวเลือก)
    public List<PaymentDetailModel> findPaymentDetails(Long itemTypeid, Long req_id, Long pid, String role) {
        String sql = "SELECT a.key_id, a.bill_No, a.title, a.currency, a.exchange_rate, a.date, a.datermine_date, " +
                "a.reference, a.reference_number, a.remark, a.internal_remark, a.tag, a.file, " +
                "pt.pid as payId, pt.type_name, rt.req_id, rt.req_name, rt.bansi, it.itemTypeid, it.itemtype_Name, l.USER_LOGIN, l.role " +
                "FROM tb_accounting a " +
                "INNER JOIN pay_type pt ON a.pay_typeid = pt.pid " +
                "LEFT JOIN LOGIN l ON a.user_id = l.KEY_ID " +
                "LEFT JOIN supplier s ON a.supplierid = s.supplierid " +
                "LEFT JOIN request_item_type rt ON pt.req_id = rt.req_id " +
                "LEFT JOIN item_type it ON rt.item_typeid = it.itemTypeid ";

        List<Object> params = new ArrayList<>();
        List<String> conditions = new ArrayList<>();

        if (itemTypeid != null) {
            conditions.add("it.itemTypeid = ?");
            params.add(itemTypeid);
        }
        if (req_id != null) {
            conditions.add("rt.req_id = ?");
            params.add(req_id);
        }
        if (pid != null) {
            conditions.add("pt.pid = ?");
            params.add(pid);
        }

        // 🔹 Filter role: ดูทุกคนที่มี role เท่ากับ role ของผู้ใช้
        if ("SUPERACCOUNT".equalsIgnoreCase(role) || "SUPERBANSI".equalsIgnoreCase(role)) {
            conditions.add("l.role = ?");
            params.add(role.toUpperCase()); // role ต้อง match กับ column l.role
        }
        // PADMIN เห็นทุกอย่าง → ไม่ต้อง filter

        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }

        sql += " ORDER BY a.date DESC";

        log.info("SQL: {} , params: {}", sql, params);

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapPaymentDetail(rs), params.toArray());
    }



    // 🔹 Mapper สำหรับ PaymentDetailModel
    private PaymentDetailModel mapPaymentDetail(java.sql.ResultSet rs) throws java.sql.SQLException {
        PaymentDetailModel model = new PaymentDetailModel();
        model.setKeyId(rs.getLong("key_id"));
        model.setBillNo(rs.getString("bill_No"));
        model.setBansi(rs.getString("bansi"));
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
        model.setFile(rs.getString("file"));
        model.setPayId(rs.getLong("payId"));
        model.setPaytype(rs.getString("type_name"));
        model.setReq_id(rs.getLong("req_id"));
        model.setSmallProject(rs.getString("req_name"));
        model.setItemTypeid(rs.getLong("itemTypeid"));
        model.setBigProject(rs.getString("itemtype_Name"));
        model.setUser(rs.getString("USER_LOGIN"));

        //  เติม listItems จาก tb_accounting_list
        model.setListItems(findListItemsByBillNo(model.getBillNo()));

        return model;
    }

    //  ดึงข้อมูล list item ตาม billNo
    public List<PaymentDetailListModel> findListItemsByBillNo(String billNo) {
        String sql = "SELECT id, key_id, bill_No, list_name, qty, unit, price, reduce, reduce_status, tax, tax_status " +
                "FROM tb_accounting_list WHERE bill_No = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PaymentDetailListModel item = new PaymentDetailListModel();
            item.setId(rs.getLong("id"));
            item.setKeyId(rs.getLong("key_id"));
            item.setBill_No(rs.getString("bill_No"));
            item.setListName(rs.getString("list_name"));
            item.setQty(rs.getDouble("qty"));
            item.setUnit(rs.getString("unit"));
            item.setPrice(rs.getDouble("price"));
            item.setReduce(rs.getDouble("reduce"));
            item.setReduceStatus(rs.getString("reduce_status"));
            item.setTax(rs.getDouble("tax"));
            item.setTaxStatus(rs.getString("tax_status"));
            return item;
        }, billNo);
    }

    // query interviewee
    public List<IntervieweeModel> findInterviewees(String status, String startDate, String endDate) {

        String sql = "SELECT i.interviewee, i.position, i.experience, i.age, i.tel, i.tel1, i.status, " +
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

            model.setInterviewee(rs.getString("interviewee"));
            model.setPosition(rs.getString("position"));
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

}
