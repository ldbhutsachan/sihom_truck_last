package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class PayTypeReq {
    private String toKen;  // token จาก client
    private Long req_id;   // req_id ที่ client ส่งมา (อาจจะมีหรือไม่มีก็ได้)
}
