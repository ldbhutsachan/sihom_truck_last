package com.ldb.truck.Entity.Stock;

import lombok.Data;

import java.util.List;

@Data
public class RequestItemDetailsReq {
    String toKen;
    String userId;
    String billNo;
    String status;
    String remark;
    String pathApi;
    String role;
    private List<OrderObject> detailId;

    @Data
    public static class OrderObject {
        private Long itemId;
        private String status;
    }

}
