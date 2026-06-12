package com.ldb.truck.Util;

import com.ldb.truck.Entity.Staff.StaffEntity;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class WorkScheduleUtil {

    public boolean isWorkDay(StaffEntity staff,
                             LocalDate date,
                             String schedule) {

        DayOfWeek day = date.getDayOfWeek();

        switch (schedule != null ? schedule : "MON_FRI") {
            case "MON_FRI":
                return day != DayOfWeek.SATURDAY
                        && day != DayOfWeek.SUNDAY;

            case "CYCLE":
                return isCycleWorkDay(staff, date);

            default:
                return day != DayOfWeek.SATURDAY
                        && day != DayOfWeek.SUNDAY;
        }
    }

    private boolean isCycleWorkDay(StaffEntity staff, LocalDate date) {

        LocalDate startDate = staff.getCycleStartDate();
        if (startDate == null) return true;

        int workDays    = staff.getCycleWorkDays() != null
                ? staff.getCycleWorkDays() : 24;
        int offDays     = staff.getCycleOffDays()  != null
                ? staff.getCycleOffDays()  : 7;
        int cycleLength = workDays + offDays;

        long daysSinceStart = ChronoUnit.DAYS.between(startDate, date);

        if (daysSinceStart < 0) return true;

        int positionInCycle = (int) (daysSinceStart % cycleLength);

        return positionInCycle < workDays;
    }

    // คำนวณวันทำงานในช่วงวันที่
    public double calculateWorkDays(StaffEntity staff,
                                    LocalDate startDate,
                                    LocalDate endDate) {
        String schedule = staff.getWorkSchedule() != null
                ? staff.getWorkSchedule() : "MON_FRI";

        double workDays = 0;
        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {
            if (isWorkDay(staff, current, schedule)) {
                workDays++;
            }
            current = current.plusDays(1);
        }

        return workDays;
    }
}
