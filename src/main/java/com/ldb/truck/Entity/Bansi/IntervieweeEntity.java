package com.ldb.truck.Entity.Bansi;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "tb_interviewer")
public class IntervieweeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    private Integer keyId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "interviewee")
    private String interviewee;

    @Column(name = "position")
    private String position;

    @Column(name = "experience")
    private String experience;

    @Column(name = "age")
    private Integer age;

    @Column(name = "tel")
    private String tel;

    @Column(name = "tel1")
    private String tel1;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "interview_date")
    private String interviewDate;

    @Column(name = "status")
    private String status;

    @Column(name = "interviewer1")
    private String interviewer1;

    @Column(name = "interviewer2")
    private String interviewer2;

    @Column(name = "interviewer3")
    private String interviewer3;

    @Column(name = "image")
    private String image;

    @Column(name = "profile")
    private String profile;

    @Column(name = "interview_time")
    private String interviewTime;


    @Transient
    private String toKen; //สำหรับ token จาก client
}
