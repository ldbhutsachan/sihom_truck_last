package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.PayTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface PayTypeRepository extends JpaRepository<PayTypeEntity, Long> {
//
//    @Query(value = "SELECT * FROM pay_type WHERE req_id = :reqId", nativeQuery = true)
//    List<PayTypeEntity> findAllByReqId(@Param("reqId") Long reqId);
//}
@Repository
public interface PayTypeRepository extends JpaRepository<PayTypeEntity, Long> {
    List<PayTypeEntity> findByReqId(Long reqId);
}



