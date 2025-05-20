package com.ldb.truck.Entity.Supplier;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "supplier")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid") // Maps to DB column name
    private Integer supplierId;

    @Column(name = "supplier_name", length = 200, nullable = false)
    private String supplierName;

    @Column(name = "mobile")
    private String mobile; // Changed to Long for large numbers

    @Column(name = "location", length = 500, nullable = true)
    private String location;

    @Column(name = "email", length = 200, nullable = true)
    private String email;

    @Column(name = "supplier_type", length = 500, nullable = true)
    private String supplierType;
}