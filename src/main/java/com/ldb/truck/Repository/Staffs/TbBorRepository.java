package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.TbBorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbBorRepository extends JpaRepository<TbBorEntity, Integer> {

    // ดึงเฉพาะ Bor ที่ Active
    List<TbBorEntity> findAllByStatus(String status);

    // หา Bor ตาม key_id และ Active
    Optional<TbBorEntity> findByKeyIdAndStatus(Integer keyId, String status);

}
