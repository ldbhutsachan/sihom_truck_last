package com.ldb.truck.Entity.RequestItem;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "v_req_type")
public class requestItemTypeBorNameEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private String reqId;

    @Column(name = "req_name")
    private String reqName;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    @Column(name = "bor_no")
    private String borNo;

    @Column(name = "bor_id")
    private String borId;

}
