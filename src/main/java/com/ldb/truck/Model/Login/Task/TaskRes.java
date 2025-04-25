package com.ldb.truck.Model.Login.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRes {
    private String status;
    private String message;
    public List<TaskModel> data;

}
