package com.ldb.truck.Model.Borcar;

import com.ldb.truck.Model.Machine.GroupHeaderReport;
import lombok.Data;

import java.util.List;

@Data
public class BorCarResponse {
    private String status;
    private String message;
    private GroupHeaderReport groupList;
    private List<BorCarModel> data;
}
