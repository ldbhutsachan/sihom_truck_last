package com.ldb.truck.Entity.RequestItem;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "request_item")
public class RequestItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rqquet_item_id")
    private Integer rqquet_item_id;

    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "saveby")
    private String saveBy;

    @Column(name = "savedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveDate;

}
