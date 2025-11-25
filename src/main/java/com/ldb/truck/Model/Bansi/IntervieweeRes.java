package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;

@Data
public class IntervieweeRes {
    private String status;
    private String message;
    private List<IntervieweeModel> data;
}
