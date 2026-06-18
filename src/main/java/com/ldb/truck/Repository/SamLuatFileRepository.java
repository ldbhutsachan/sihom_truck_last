package com.ldb.truck.Repository;

import com.ldb.truck.Entity.SamLuat.SamLuatFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SamLuatFileRepository extends JpaRepository<SamLuatFileEntity, Long> {
    List<SamLuatFileEntity> findByFolderIdAndStatus(Long folderId, String status);
    List<SamLuatFileEntity> findByFolderIdIsNullAndStatus(String status);
    List<SamLuatFileEntity> findByStatus(String status);
}
