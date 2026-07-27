package com.ldb.truck.Entity.Staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candidate_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "fullname",
        "nickname",
        "nameEng",
        "dob",
        "age",
        "gender",
        "weight",
        "height",
        "religion",
        "position",
        "salary",
        "rotatePosition",
        "experience",
        "interviewDate",
        "interviewTime",
        "dateInterview",
        "status",
        "interviewStatus",
        "approveBy",
        "pobVillage",
        "pobDistrict",
        "pobProvince",
        "addrVillage",
        "addrDistrict",
        "addrProvince",
        "phone",
        "tel2",
        "email",
        "idCardNo",
        "idIssuedAt",
        "idIssueDate",
        "idExpireDate",
        "residence",
        "maritalStatus",
        "mbDrive",
        "mbLicense",
        "carDrive",
        "carLicense",
        "carLicenseType",
        "fatherAlive",
        "fatherName",
        "fatherAge",
        "fatherOcc",
        "fatherTel",
        "motherAlive",
        "motherName",
        "motherAge",
        "motherOcc",
        "motherTel",
        "spouseName",
        "spouseAge",
        "spouseTel",
        "spouseOcc",
        "image",
        "files",
        "createdAt",
        "educations"
})
public class CandidateProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "name_eng")
    private String nameEng;

    @Column(name = "dob")
    private String dob;

    @Column(name = "age")
    private String age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "weight")
    private String weight;

    @Column(name = "height")
    private String height;

    @Column(name = "religion")
    private String religion;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private String salary;

    @Column(name = "rotate_position")
    private String rotatePosition;

    @Column(name = "experience")
    private String experience;

    @Column(name = "interview_date")
    private String interviewDate;

    @Column(name = "interview_time")
    private String interviewTime;

    @Column(name = "date_interview")
    private String dateInterview;

    @Column(name = "status")
    private String status = "WAIT";

    @Column(name = "approve_by")
    private String approveBy;

    @Column(name = "pob_village")
    private String pobVillage;

    @Column(name = "pob_district")
    private String pobDistrict;

    @Column(name = "pob_province")
    private String pobProvince;

    @Column(name = "addr_village")
    private String addrVillage;

    @Column(name = "addr_district")
    private String addrDistrict;

    @Column(name = "addr_province")
    private String addrProvince;

    @Column(name = "phone")
    private String phone;

    @Column(name = "tel2")
    private String tel2;

    @Column(name = "email")
    private String email;

    @Column(name = "id_card_no")
    private String idCardNo;

    @Column(name = "id_issued_at")
    private String idIssuedAt;

    @Column(name = "id_issue_date")
    private String idIssueDate;

    @Column(name = "id_expire_date")
    private String idExpireDate;

    @Column(name = "residence")
    private String residence;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "mb_drive")
    private String mbDrive;

    @Column(name = "mb_license")
    private String mbLicense;

    @Column(name = "car_drive")
    private String carDrive;

    @Column(name = "car_license")
    private String carLicense;

    @Column(name = "car_license_type")
    private String carLicenseType;

    @Column(name = "father_alive")
    private String fatherAlive;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "father_age")
    private String fatherAge;

    @Column(name = "father_occ")
    private String fatherOcc;

    @Column(name = "father_tel")
    private String fatherTel;

    @Column(name = "mother_alive")
    private String motherAlive;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_age")
    private String motherAge;

    @Column(name = "mother_occ")
    private String motherOcc;

    @Column(name = "mother_tel")
    private String motherTel;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "spouse_age")
    private String spouseAge;

    @Column(name = "spouse_tel")
    private String spouseTel;

    @Column(name = "spouse_occ")
    private String spouseOcc;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "files", columnDefinition = "TEXT")
    private String files;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateEducation> educations = new ArrayList<>();

    @JsonProperty("interviewStatus")
    public String getInterviewStatus() {
        return calculateInterviewStatus();
    }

    @JsonProperty("interview-status")
    public String getInterviewStatusKebab() {
        return calculateInterviewStatus();
    }

    public String calculateInterviewStatus() {
        if (this.status == null || !"IN-PROGRESS".equalsIgnoreCase(this.status.trim())) {
            return " ";
        }
        String dateStr = this.dateInterview;
        if (dateStr == null || dateStr.trim().isEmpty()) {
            dateStr = this.interviewDate;
        }
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return " ";
        }
        try {
            dateStr = dateStr.trim();
            if (dateStr.length() < 10)
                return " ";
            LocalDate date = LocalDate.parse(dateStr.substring(0, 10));
            LocalDate today = LocalDate.now();

            if (date.isEqual(today)) {
                return "EXPIRED";
            } else if (date.isBefore(today)) {
                long daysPast = ChronoUnit.DAYS.between(date, today);
                return "OVERDUE, " + daysPast + (daysPast == 1 ? " DAY" : " DAYS");
            } else {
                long daysFuture = ChronoUnit.DAYS.between(today, date);
                return "COMING, " + daysFuture + (daysFuture == 1 ? " DAY" : " DAYS");
            }
        } catch (Exception e) {
            return " ";
        }
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null || this.status.trim().isEmpty()) {
            this.status = "WAIT";
        }
    }
}
