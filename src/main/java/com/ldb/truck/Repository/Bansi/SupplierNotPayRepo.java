package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Model.Bansi.ItemForAccountingDto;
import com.ldb.truck.Model.Bansi.SupplierNotPayDto;
import com.ldb.truck.Model.Bansi.SupplierSummaryRowDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SupplierNotPayRepo {

    private final JdbcTemplate jdbcTemplate;

    public SupplierNotPayRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ==============================
    // 1️⃣ Detail: FinanceBills
    // ==============================
    public List<SupplierNotPayDto> findSupplierNotPay(
            String startDate,
            String endDate,
            String typeOf,
            String supplierId,
            String show
    ) {
        // ตรวจสอบค่า ถ้า "" ให้เปลี่ยนเป็น null
        startDate = (startDate == null || startDate.trim().isEmpty()) ? null : startDate;
        endDate = (endDate == null || endDate.trim().isEmpty()) ? null : endDate;
        typeOf = (typeOf == null || typeOf.trim().isEmpty()) ? null : typeOf;
        supplierId = (supplierId == null || supplierId.trim().isEmpty()) ? null : supplierId;
        show = (show == null || show.trim().isEmpty()) ? "all" : show;

        String sql =
                "SELECT key_id, bill_no, finance_bill, supplier_id, supplier_name,\n" +
                        "       type_of, amount_must_pay, pay1, next_date_pay4, pay_status, currency,\n" +
                        "       create_by, create_date, big_project, small_project, pay_type, bank_account_name, bank_account_no, title\n" +
                        "FROM v_finace_supplier\n" +
                        "WHERE (? = 'all' OR (? = '1' AND pay_status = 'DONE') OR (? = '2' AND pay_status = 'IN-PROGRESS'))\n" +
                        "AND create_date >= IFNULL(?, create_date)\n" +
                        "AND create_date <  IFNULL(DATE_ADD(?, INTERVAL 1 DAY), create_date)\n" +
                        "AND (? IS NULL OR type_of = ?)\n" +
                        "AND (? IS NULL OR supplier_id = ?)\n" +
                        "ORDER BY supplier_id, finance_bill\n";

        return jdbcTemplate.query(sql, new Object[]{
                show, show, show,         // สำหรับเงื่อนไข pay_status
                startDate,                // IFNULL start
                endDate,                  // IFNULL end
                typeOf, typeOf,           // type_of
                supplierId, supplierId    // supplier_id
        }, (rs, rowNum) -> {
            SupplierNotPayDto dto = new SupplierNotPayDto();
            dto.setKeyId(rs.getLong("key_id"));
            dto.setBigProject(rs.getString("big_project"));
            dto.setSmallProject(rs.getString("small_project"));
            dto.setTypePay(rs.getString("pay_type"));
            dto.setBankName(rs.getString("bank_account_name"));
            dto.setBankNo(rs.getString("bank_account_no"));
            dto.setBillNo(rs.getString("bill_no"));
            dto.setTitle(rs.getString("title"));
            dto.setFinanceBill(rs.getString("finance_bill"));
            dto.setSupplierId(rs.getLong("supplier_id"));
            dto.setSupplierName(rs.getString("supplier_name"));
            dto.setTypeOf(rs.getString("type_of"));
            dto.setAmountMustPay(rs.getBigDecimal("amount_must_pay"));
            dto.setPaid(rs.getBigDecimal("pay1"));
            dto.setNextDatePay(rs.getString("next_date_pay4"));
            dto.setPayStatus(rs.getString("pay_status"));
            dto.setCurrency(rs.getString("currency"));
            dto.setCreateBy(rs.getString("create_by"));
            dto.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            return dto;
        });
    }
    private SupplierNotPayDto mapRow(ResultSet rs) throws SQLException {
        SupplierNotPayDto dto = new SupplierNotPayDto();
        dto.setKeyId(rs.getLong("key_id"));
        dto.setBigProject(rs.getString("big_project"));
        dto.setSmallProject(rs.getString("small_project"));
        dto.setTypePay(rs.getString("pay_type"));
        dto.setBankName(rs.getString("bank_account_name"));
        dto.setBankNo(rs.getString("bank_account_no"));
        dto.setBillNo(rs.getString("bill_no"));
        dto.setTitle(rs.getString("title"));
        dto.setFinanceBill(rs.getString("finance_bill"));
        dto.setSupplierId(rs.getLong("supplier_id"));
        dto.setSupplierName(rs.getString("supplier_name"));
        dto.setTypeOf(rs.getString("type_of"));
        dto.setAmountMustPay(rs.getBigDecimal("amount_must_pay"));
        dto.setPaid(rs.getBigDecimal("pay1"));
        dto.setNextDatePay(rs.getString("next_date_pay4"));
        dto.setPayStatus(rs.getString("pay_status"));
        dto.setCurrency(rs.getString("currency"));
        dto.setCreateBy(rs.getString("create_by"));
        dto.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        return dto;
    }

    // ListItem must pay for accounting
    public List<ItemForAccountingDto> findItemforaccounting(
            String startDate,
            String endDate,
            String supplierId) {

        // ตรวจสอบค่า ถ้า "" ให้เปลี่ยนเป็น null
        startDate = (startDate == null || startDate.trim().isEmpty()) ? null : startDate;
        endDate = (endDate == null || endDate.trim().isEmpty()) ? null : endDate;
        supplierId = (supplierId == null || supplierId.trim().isEmpty()) ? null : supplierId;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM v_accounting_item WHERE 1=1 ");

        List<Object> params = new ArrayList<>();

        // filter date
        if (startDate != null && endDate != null) {
            sql.append(" AND DATE(buydate) BETWEEN ? AND ? ");
            params.add(startDate);
            params.add(endDate);
        }

        // filter supplier
        if (supplierId != null) {
            sql.append(" AND supplier_id = ? ");
            params.add(supplierId);
        }

        sql.append(" ORDER BY buydate DESC ");

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> {

            ItemForAccountingDto dto = new ItemForAccountingDto();

            dto.setBorkey((Integer) rs.getObject("borkey"));
            dto.setBorname(rs.getString("borname"));

            dto.setHousid((Integer) rs.getObject("housid"));
            dto.setHousname(rs.getString("housname"));

            dto.setDetailId((Integer) rs.getObject("detail_id"));
            dto.setBillNo(rs.getString("bill_no"));

            dto.setItemId((Integer) rs.getObject("item_id"));
            dto.setItemName(rs.getString("item_name"));
            dto.setUnit(rs.getString("unit"));
            dto.setSize(rs.getString("size"));

            dto.setCurrency(rs.getString("currency"));
            dto.setExchangeRate((Integer) rs.getObject("exchange_rate"));

            dto.setQty((Integer) rs.getObject("qty"));
            dto.setToalAmount(rs.getDouble("toal_amount"));

            dto.setStatus(rs.getString("status"));

            dto.setSaveby(rs.getString("saveby"));
            dto.setSavedate(rs.getTimestamp("savedate") != null ?
                    rs.getTimestamp("savedate").toLocalDateTime() : null);

            dto.setBuyby(rs.getString("buyby"));
            dto.setBuydate(rs.getTimestamp("buydate") != null ?
                    rs.getTimestamp("buydate").toLocalDateTime() : null);

            dto.setEditby(rs.getString("editby"));
            dto.setEditdate(rs.getTimestamp("editdate") != null ?
                    rs.getTimestamp("editdate").toLocalDateTime() : null);

            dto.setApproveby(rs.getString("approveby"));
            dto.setApprovedate(rs.getTimestamp("approvedate") != null ?
                    rs.getTimestamp("approvedate").toLocalDateTime() : null);

            dto.setAcceptby(rs.getString("acceptby"));
            dto.setAcceptdate(rs.getDate("acceptdate") != null ?
                    rs.getDate("acceptdate").toLocalDate() : null);

            dto.setPlaceBuy(rs.getString("place_buy"));

            dto.setShopId((Integer) rs.getObject("shop_id"));
            dto.setShopName(rs.getString("shop_name"));

            dto.setTypeOfOrder(rs.getString("type_of_order"));
            dto.setDatePay(rs.getString("date_pay"));
            dto.setItemArriveDate(rs.getString("item_arrive_date"));
            dto.setPayStatus(rs.getString("pay_status"));

            dto.setImagefile(rs.getString("imagefile"));

            return dto;
        });
    }




}
