package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class ProjectPaymentModel {
    private Long req_id;
    private String payment_type;
    private String bansi;
    private Long projectID;
    private String project_name;
}
