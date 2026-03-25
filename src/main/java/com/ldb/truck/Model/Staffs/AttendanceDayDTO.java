package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AttendanceDayDTO {
    private String staffCode;
    private String username;
    private LocalDate date;
    private LocalDateTime checkIn;    // ✅ CHECK_IN แยก field
    private LocalDateTime checkOut;   // ✅ CHECK_OUT แยก field
}
