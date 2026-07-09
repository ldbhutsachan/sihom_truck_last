package com.ldb.truck.Model.ImageOfCar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageOfCar {
    private Long id;
    private Integer carId;
    private String imageType;
    private String file;
    private String createBy;
    private String createDate;
}
