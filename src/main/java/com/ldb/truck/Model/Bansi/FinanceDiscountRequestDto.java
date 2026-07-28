package com.ldb.truck.Model.Bansi;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinanceDiscountRequestDto {
    private String toKen;

    @JsonProperty("bill_id")
    @JsonAlias({ "billId", "bill_id" })
    private Long billId;

    private String billNo;
    private String remark;

    private String discountType; // "PERCENT" or "FIXED"
    private BigDecimal discountValue;

    @JsonProperty("discountPercent")
    @JsonAlias({ "discount_percent", "discountPercent" })
    private BigDecimal discountPercent;

    @JsonProperty("discountAmount")
    @JsonAlias({ "discount_amount", "discountAmount" })
    private BigDecimal discountAmount;

    public Long getEffectiveBillId() {
        return billId;
    }

    public BigDecimal getEffectiveDiscountPercent() {
        if (discountPercent != null) {
            return discountPercent;
        }
        if ("PERCENT".equalsIgnoreCase(discountType) && discountValue != null) {
            return discountValue;
        }
        return null;
    }

    public BigDecimal getEffectiveDiscountAmount() {
        if (discountAmount != null) {
            return discountAmount;
        }
        if ("FIXED".equalsIgnoreCase(discountType) && discountValue != null) {
            return discountValue;
        }
        return null;
    }
}
