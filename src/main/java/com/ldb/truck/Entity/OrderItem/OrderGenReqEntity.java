package com.ldb.truck.Entity.OrderItem;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "v_request_item_key")
public class OrderGenReqEntity {//select * from v_request_item_key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "max_stock_id")
    private String maxReqKey;
}
