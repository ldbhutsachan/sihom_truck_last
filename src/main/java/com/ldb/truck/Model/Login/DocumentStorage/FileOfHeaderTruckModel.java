package com.ldb.truck.Model.Login.DocumentStorage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileOfHeaderTruckModel {
    private String files;
    private String key_id_of_lod;
    private String key_id_of_file;
    private String license_plate;
    private String date2;
}
