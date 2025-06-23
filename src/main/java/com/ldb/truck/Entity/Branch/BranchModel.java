package com.ldb.truck.Entity.Branch;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TB_BRANCH")
public class BranchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KEY_ID")
    private Long brachNo;

    @Column(name = "B_NAME")
    private String brachName;

    @Column(name = "B_LOCATION")
    private String location;
}
