package com.ldb.truck.Entity.Staff;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;

@Component
public class AttendanceLogListener implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @PostPersist
    public void onPostPersist(AttendanceLog log) {
        if (context == null)
            return;

        try {
            com.ldb.truck.Repository.Staffs.AttendanceLogBackupRepository backupRepo = context
                    .getBean(com.ldb.truck.Repository.Staffs.AttendanceLogBackupRepository.class);

            AttendanceLogBackup backup = new AttendanceLogBackup();
            backup.setOriginalLogId(log.getId());
            backup.setStaff(log.getStaff());
            backup.setCheckType(log.getCheckType());
            backup.setCheckTime(log.getCheckTime());
            backup.setRemark(log.getRemark());
            backup.setCreatedAt(log.getCreatedAt());
            backup.setIpAddress(log.getIpAddress());
            backup.setMacAddress(log.getMacAddress());

            backupRepo.save(backup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
