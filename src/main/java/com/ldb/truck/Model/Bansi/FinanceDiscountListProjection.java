package com.ldb.truck.Model.Bansi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FinanceDiscountListProjection {
    @JsonProperty("discount_id")
    Long getId();

    @JsonProperty("bill_id")
    Long getBillId();

    @JsonProperty("bill_no")
    String getBillNo();

    @JsonProperty("discount_type")
    String getDiscountType();

    @JsonProperty("discount_percent")
    BigDecimal getDiscountPercent();

    @JsonProperty("discount_amount")
    BigDecimal getDiscountAmount();

    @JsonProperty("amount_before")
    BigDecimal getAmountBefore();

    @JsonProperty("amount_after")
    BigDecimal getAmountAfter();

    @JsonProperty("remark")
    String getRemark();

    @JsonProperty("create_date")
    LocalDateTime getCreateDate();

    @JsonProperty("create_by")
    Long getCreateBy();

    @JsonProperty("big_project_id")
    Integer getBigProjectId();

    @JsonProperty("big_project")
    String getBigProject();

    @JsonProperty("small_project_id")
    Integer getSmallProjectId();

    @JsonProperty("small_project")
    String getSmallProject();

    @JsonProperty("pay_type_id")
    String getPayTypeId();

    @JsonProperty("pay_type")
    String getPayType();

    @JsonProperty("pay_typegroup_id")
    String getPayTypegroupId();

    @JsonProperty("pay_typegroup_name")
    String getPayTypegroupName();

    @JsonProperty("type_of")
    String getTypeOf();

    @JsonProperty("supplierid")
    Integer getSupplierId();

    @JsonProperty("supplier_name")
    String getSupplierName();

    @JsonProperty("bank_account_name")
    String getBankAccountName();

    @JsonProperty("bank_account_no")
    String getBankAccountNo();

    @JsonProperty("bank_name")
    String getBankName();

    @JsonProperty("currency")
    String getCurrency();

    @JsonProperty("exchange_rate")
    String getExchangeRate();

    @JsonProperty("original_lak_price")
    Double getOriginalLakPrice();

    @JsonProperty("original_usd_price")
    Double getOriginalUsdPrice();

    @JsonProperty("price")
    Double getPrice();

    @JsonProperty("usd_price")
    Double getUsdPrice();
}
