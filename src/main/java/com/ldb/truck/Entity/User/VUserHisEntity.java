package com.ldb.truck.Entity.User;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "V_USER_HIS_STOCK")

public class VUserHisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Maps to DB column name
    private String user_id;

    @Column(name = "user_name") // Maps to DB column name
    private String userName;

    @Column(name = "details") // Maps to DB column name
    private String details;

    @Column(name = "details_id") // Maps to DB column name
    private Integer detailId;

    @Column(name = "bill_no") // Maps to DB column name
    private String billNo;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
}
