package com.ldb.truck.Model.Bansi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({
        "discount_id",
        "bill_id",
        "bill_no",
        "discount_type",
        "discount_percent",
        "discount_amount",
        "amount_before",
        "amount_after",
        "remark",
        "create_date",
        "create_by",
        "big_project_id",
        "big_project",
        "small_project_id",
        "small_project",
        "pay_type_id",
        "pay_type",
        "pay_typegroup_id",
        "pay_typegroup_name",
        "type_of",
        "supplierid",
        "supplier_name",
        "bank_account_name",
        "bank_account_no",
        "bank_name",
        "currency",
        "exchange_rate",
        "original_lak_price",
        "original_usd_price",
        "price",
        "usd_price"
})
public class FinanceDiscountListResponseDto {

    @JsonProperty("discount_id")
    private Long discountId;

    @JsonProperty("bill_id")
    private Long billId;

    @JsonProperty("bill_no")
    private String billNo;

    @JsonProperty("discount_type")
    private String discountType;

    @JsonProperty("discount_percent")
    private BigDecimal discountPercent;

    @JsonProperty("discount_amount")
    private BigDecimal discountAmount;

    @JsonProperty("amount_before")
    private BigDecimal amountBefore;

    @JsonProperty("amount_after")
    private BigDecimal amountAfter;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("create_date")
    private LocalDateTime createDate;

    @JsonProperty("create_by")
    private Long createBy;

    @JsonProperty("big_project_id")
    private Integer bigProjectId;

    @JsonProperty("big_project")
    private String bigProject;

    @JsonProperty("small_project_id")
    private Integer smallProjectId;

    @JsonProperty("small_project")
    private String smallProject;

    @JsonProperty("pay_type_id")
    private String payTypeId;

    @JsonProperty("pay_type")
    private String payType;

    @JsonProperty("pay_typegroup_id")
    private String payTypegroupId;

    @JsonProperty("pay_typegroup_name")
    private String payTypegroupName;

    @JsonProperty("type_of")
    private String typeOf;

    @JsonProperty("supplierid")
    private Integer supplierId;

    @JsonProperty("supplier_name")
    private String supplierName;

    @JsonProperty("bank_account_name")
    private String bankAccountName;

    @JsonProperty("bank_account_no")
    private String bankAccountNo;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("exchange_rate")
    private String exchangeRate;

    @JsonProperty("original_lak_price")
    private Double originalLakPrice;

    @JsonProperty("original_usd_price")
    private Double originalUsdPrice;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("usd_price")
    private Double usdPrice;

    public static FinanceDiscountListResponseDto fromProjection(FinanceDiscountListProjection p) {
        FinanceDiscountListResponseDto dto = new FinanceDiscountListResponseDto();
        dto.setDiscountId(p.getId());
        dto.setBillId(p.getBillId());
        dto.setBillNo(p.getBillNo());
        dto.setDiscountType(p.getDiscountType());
        dto.setDiscountPercent(p.getDiscountPercent());
        dto.setDiscountAmount(p.getDiscountAmount());
        dto.setAmountBefore(p.getAmountBefore());
        dto.setAmountAfter(p.getAmountAfter());
        dto.setRemark(p.getRemark());
        dto.setCreateDate(p.getCreateDate());
        dto.setCreateBy(p.getCreateBy());
        dto.setBigProjectId(p.getBigProjectId());
        dto.setBigProject(p.getBigProject());
        dto.setSmallProjectId(p.getSmallProjectId());
        dto.setSmallProject(p.getSmallProject());
        dto.setPayTypeId(p.getPayTypeId());
        dto.setPayType(p.getPayType());
        dto.setPayTypegroupId(p.getPayTypegroupId());
        dto.setPayTypegroupName(p.getPayTypegroupName());
        dto.setTypeOf(p.getTypeOf());
        dto.setSupplierId(p.getSupplierId());
        dto.setSupplierName(p.getSupplierName());
        dto.setBankAccountName(p.getBankAccountName());
        dto.setBankAccountNo(p.getBankAccountNo());
        dto.setBankName(p.getBankName());
        dto.setCurrency(p.getCurrency());
        dto.setExchangeRate(p.getExchangeRate());
        dto.setOriginalLakPrice(p.getOriginalLakPrice());
        dto.setOriginalUsdPrice(p.getOriginalUsdPrice());
        dto.setPrice(p.getPrice());
        dto.setUsdPrice(p.getUsdPrice());
        return dto;
    }
}
