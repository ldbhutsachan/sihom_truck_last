package com.ldb.truck.Model.Login.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkReq {
    private String source;
    private String target;
    private String type;

    private String toKen;
    private String userId;
    private String branch;
    private String id;
}
