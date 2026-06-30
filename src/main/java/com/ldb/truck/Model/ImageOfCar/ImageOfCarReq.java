package com.ldb.truck.Model.ImageOfCar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageOfCarReq {
    private String toKen;
    private Long id;
    private Integer carId;
    private String imageType;
    private String file;
}
