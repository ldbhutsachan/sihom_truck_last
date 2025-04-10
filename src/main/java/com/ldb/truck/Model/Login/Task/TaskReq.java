package com.ldb.truck.Model.Login.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskReq {
    private String topic_task;
    private String parent;
    private String startDate;
    private String endDate;
    private String toKen;
    private String branch_id;
    private String userId;
    private String branch;
    private String progress;
    private String key_id;
    private String status;
}
