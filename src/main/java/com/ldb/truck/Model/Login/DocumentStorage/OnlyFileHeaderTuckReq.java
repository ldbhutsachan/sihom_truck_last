package com.ldb.truck.Model.Login.DocumentStorage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlyFileHeaderTuckReq {
    private String headtruck_id;
    private String toKen;
    private String date2;
    private String picOrFile;
    private String userId;
    private String branch;
    private String key_id_of_file;
    private String key_id_of_lod;
    private String file;
}
