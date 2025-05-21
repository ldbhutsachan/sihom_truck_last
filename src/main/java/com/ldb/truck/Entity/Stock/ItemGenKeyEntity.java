package com.ldb.truck.Entity.Stock;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "v_genkey_item")
public class ItemGenKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "max_stock_id")
    private Integer stockNo;
}