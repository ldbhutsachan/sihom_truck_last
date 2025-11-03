package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentModel {
    private Long pid;
    private String pay_type;
    private LocalDateTime date_create;
    private String small_project;
    private String big_project;
    private Long big_project_id;
}
