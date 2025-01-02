package com.ldb.truck.Model.Login.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    private String key_id;
    private String topic_task;
    private String sub_task;
    private String startDate;
    private String endDate;
//    private String branch_id;
    private String branch_name;
    private String duration;
}
