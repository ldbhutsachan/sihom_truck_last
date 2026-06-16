package com.ldb.truck.Repository;

import com.ldb.truck.Entity.SamLuat.SamLuatFolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SamLuatFolderRepository extends JpaRepository<SamLuatFolderEntity, Long> {
    List<SamLuatFolderEntity> findByStatusOrderByCreatedDateAsc(String status);
    List<SamLuatFolderEntity> findByParentIdAndStatus(Long parentId, String status);
}
