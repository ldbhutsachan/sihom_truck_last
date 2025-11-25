package com.ldb.truck.Model.Bansi;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PaymentRequestDto {

    private String toKen;
    private Long user_id;
    private Long pay_typeid;
    private Long supplierid;
    private String billNo;
    private String title;
    private String currency;
    private String exchange_rate;
    private String date;
    private String reference_number;
    private String reference;
    private String remark;
    private String internal_remark;
    private String tag;
//    private String file;
    private String datermine_date;
    private MultipartFile file;

    private List<ToolDto> tools;

    @Data
    public static class ToolDto {
        private String list_name;
        private Integer qty;
        private String unit;
        private Float price;
        private Integer reduce;
        private String reduce_status;
        private Integer tax;
        private String tax_status;
    }
}
