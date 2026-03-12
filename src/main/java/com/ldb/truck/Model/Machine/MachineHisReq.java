package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.util.Date;

@Data
public class MachineHisReq {
    private String toKen;

    private Integer keyId; // key_id int(30)

    private String mchNo; // mch_no varchar(100)

    private String createBy; // create_by varchar(100)

   // private Integer timeTotal; // time_total int(11)

    private String timeClose; // time_close varchar(10)

    private Date txnDate; // txn_date date

    private Integer status; // status int(11)
    private Integer status2;

    private String borNo;

    private String type;
    private String startDate;
    private String endDate;
}
