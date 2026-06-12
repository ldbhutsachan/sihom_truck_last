package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class PayTypeGroupRequest {
    private String token;
    private Integer gid;
    private String groupName;
    private Integer pid;
}
