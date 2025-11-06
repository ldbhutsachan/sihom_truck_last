package com.ldb.truck.Dao.Bansi;

import com.ldb.truck.Model.Bansi.PaymentDetailListModel;
import com.ldb.truck.Model.Bansi.PaymentDetailModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentDetailDao {

    private final JdbcTemplate jdbcTemplate;

    // 🔹 ดึงข้อมูลหลักจาก tb_accounting (filter ตามตัวเลือก)
    public List<PaymentDetailModel> findPaymentDetails(Long itemTypeid, Long req_id, Long pid) {
        String sql = "SELECT a.key_id, a.bill_No, a.title, a.currency, a.exchange_rate, a.date, a.datermine_date, " +
                "a.reference, a.reference_number, a.remark, a.internal_remark, a.tag, a.file, " +
                "pt.pid as payId, pt.type_name, rt.req_id, rt.req_name, it.itemTypeid, it.itemtype_Name, l.USER_LOGIN " +
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

        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }

        log.info("SQL: {} , params: {}", sql, params);

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapPaymentDetail(rs), params.toArray());
    }


    // 🔹 Mapper สำหรับ PaymentDetailModel
    private PaymentDetailModel mapPaymentDetail(java.sql.ResultSet rs) throws java.sql.SQLException {
        PaymentDetailModel model = new PaymentDetailModel();
        model.setKeyId(rs.getLong("key_id"));
        model.setBillNo(rs.getString("bill_No"));
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

        // 🔹 เติม listItems จาก tb_accounting_list
        model.setListItems(findListItemsByBillNo(model.getBillNo()));

        return model;
    }

    // 🔹 ดึงข้อมูล list item ตาม billNo
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
}
