package com.ldb.truck.Model.ImageOfCar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageOfCarRes {
    private String status;
    private String message;
    private List<ImageOfCar> data;
}
