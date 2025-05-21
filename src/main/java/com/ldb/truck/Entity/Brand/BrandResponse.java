package com.ldb.truck.Entity.Brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    private String status;
    private String message;
    private List<BrandEntity> data;
}
