package com.ldb.truck.Model.Login.Noti;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeReq {
    private String toKen;
    private String userId;
    private String branch;
    private String branchName;

}
