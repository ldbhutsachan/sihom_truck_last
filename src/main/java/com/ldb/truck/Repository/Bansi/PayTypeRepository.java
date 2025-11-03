package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.PayTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayTypeRepository extends JpaRepository<PayTypeEntity, Long> {
    @Query(value =
            "SELECT " +
                    "p.pid, " +
                    "p.type_name AS pay_type, " +
                    "p.date_create, " +
                    "r.req_name AS small_project, " +
                    "i.itemtype_Name AS big_project, " +
                    "p.req_id as big_project_id " +
                    "FROM pay_type p " +
                    "INNER JOIN request_item_type r ON p.req_id = r.req_id " +
                    "INNER JOIN item_type i ON r.item_typeid = i.itemTypeid " +
                    "WHERE (:reqId IS NULL OR p.req_id = :reqId)",
            nativeQuery = true)
    List<Object[]> findPayTypeByReqId(@Param("reqId") Long reqId);

}
