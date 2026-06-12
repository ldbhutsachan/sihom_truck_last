package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class AttendanceResponseDTO {
    private boolean success;
    private String message;
    private List<AttendanceDayDTO> data;
}
