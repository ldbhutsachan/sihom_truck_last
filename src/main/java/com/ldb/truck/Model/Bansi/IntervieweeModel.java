package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class IntervieweeModel {
    private Integer key_id;
    private String interviewee;
    private String position;
    private String salary;
    private String currency;
    private String experience;
    private Integer age;
    private String tel;
    private String tel1;
    private String status;

    private String interviewDate;
    private String interviewTime;

    private String interviewer1;
    private String interviewer2;
    private String interviewer3;

    private String intervieweeImage;
    private String cv;

    private String dateCreate;
    private String createBy;
    private String interStatus;

    public String getInterStatus() {
        return interStatus;
    }

    public void setInterStatus(String interStatus) {
        this.interStatus = interStatus;
    }


}
