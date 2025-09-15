package com.ldb.truck.Entity.MerchineHis;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_machine_his")
public class MerchineHisEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer keyId; // key_id int(30)

        @Column(name = "mch_no", length = 100)
        private String mchNo; // mch_no varchar(100)

        @Column(name = "create_date")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createDate; // create_date datetime

        @Column(name = "create_by", length = 100)
        private String createBy; // create_by varchar(100)

//        @Column(name = "time_open", length = 10)
//        private String timeOpen; // time_open varchar(10)
//
//        @Column(name = "time_total")
//        private Integer timeTotal; // time_total int(11)

        @Column(name = "time_total", length = 10)
        private String time_total; // time_close varchar(10)

        @Column(name = "txn_date")
        @Temporal(TemporalType.DATE)
        private Date txnDate; // txn_date date

        @Column(name = "status")
        private Integer status; // status int(11)
}
