package com.ldb.truck.Entity.RequestItem;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "request_item_type")
public class requestItemTypeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Integer reqTypeId;

    @Column(name = "req_name")
    private String reqName;

}
