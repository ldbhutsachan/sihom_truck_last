package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDetailRes {
    private String status;
    private String message;
    //  เพิ่ม cursor สำหรับหน้าถัดไป
    private String nextLastDate;
    private Long nextLastKeyId;
    private List<PaymentDetailModel> data; // เปลี่ยนจาก PaymentDetailListModel → PaymentDetailModel


}

