package com.ldb.truck.Entity.Brand;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "brand")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandid") // Match the exact DB column name
    private String brandId;

    @Column(name = "brand_Name")
    private String brandName;

    @Column(name = "company_Product")
    private String companyProduct;

    @Column(name = "status")
    private String status;

}
