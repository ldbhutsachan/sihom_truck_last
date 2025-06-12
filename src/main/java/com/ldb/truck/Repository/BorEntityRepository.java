package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Bor.BorEntity;
import com.ldb.truck.Entity.OrderItem.OrderItemReportEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BorEntityRepository extends CrudRepository<BorEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE BorEntity b SET b.bName = :bName, b.bTel = :bTel," +
            " b.location = :location, b.email = :email," +
            " b.updateBy = :updateBy, b.updateDate = :updateDate , b.sortName = :sortName, b.brandNo = :brandNo, b.status = :status WHERE b.keyId = :keyId")
    int updateBorEntity(
            @Param("keyId") Long keyId,
            @Param("bName") String bName,
            @Param("bTel") String bTel,
            @Param("location") String location,
            @Param("email") String email,
            @Param("updateBy") String updateBy,
            @Param("updateDate") Date updateDate,
            @Param("sortName") String sortName,
            @Param("brandNo") String brandNo,
            @Param("status") String status
    );

    @Modifying
    @Transactional
    @Query("UPDATE BorEntity b SET b.status = :status WHERE b.keyId = :keyId")
    int disbleBorEntity(
            @Param("keyId") Long keyId,
            @Param("status") String status
    );
}
