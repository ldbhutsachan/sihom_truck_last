package com.ldb.truck.Entity.Branch;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "tb_bors")
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    private Long brachNo;

    @Column(name = "b_name")
    private String brachName;

    @Column(name = "location")
    private String location;
}
