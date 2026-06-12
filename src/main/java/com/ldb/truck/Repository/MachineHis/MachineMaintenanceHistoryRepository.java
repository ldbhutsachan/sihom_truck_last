package com.ldb.truck.Repository.MachineHis;


import com.ldb.truck.Entity.MerchineHis.MachineMaintenanceHistory;
import com.ldb.truck.enums.MaintenanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineMaintenanceHistoryRepository
        extends JpaRepository<MachineMaintenanceHistory, Long> {

    // ดึง history ทั้งหมดของเครื่องจักร เรียงจากใหม่ไปเก่า
    List<MachineMaintenanceHistory> findByMachineKeyIdOrderByCreatedDateDesc(
            Integer machineKeyId
    );

    // ดึง history ตาม key_id + ประเภท เช่น ENGINE_OIL อย่างเดียว
    List<MachineMaintenanceHistory> findByMachineKeyIdAndMaintenanceTypeOrderByCreatedDateDesc(
            Integer machineKeyId,
            MaintenanceType maintenanceType
    );

    // ดึง history ตาม mch_no (กรณีไม่มี key_id)
    List<MachineMaintenanceHistory> findByMchNoOrderByCreatedDateDesc(
            String mchNo
    );

    Optional<MachineMaintenanceHistory> findById(Long id);
    // มีอยู่แล้วใน JpaRepository ครับ ใช้ได้เลย
}