package com.ldb.truck.Model.Staffs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AttendanceDayDTO {
    private Long  id;
    private Long staffId;
    private String staffCode;
    private String username;
    private LocalDate date;
    private LocalDateTime checkIn;    // ✅ CHECK_IN แยก field
    private String checkInStatus;    // ✅ ON-TIME / LATE X mins
    private LocalDateTime checkOut;   // ✅ CHECK_OUT แยก field
    private String checkOutStatus;   // ✅ ON-TIME / EARLY X mins/hrs
    private String ipAddress;
    private String machineAddress;
}
