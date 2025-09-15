package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.util.Date;

@Data
public class MachineHis {
    private Integer keyId; // key_id int(30)
    private String mchNo; // mch_no varchar(100)
    private Date createDate; // create_date datetime
    private String createBy; // create_by varchar(100)
    private String timeTotal; // time_close varchar(10)
    private Date txnDate; // txn_date date
    private Integer status; // status int(11)
    private String borNo;
    private String borName;
}
