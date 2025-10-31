package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.BansiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BansiRepository extends JpaRepository<BansiEntity, Long> {
    // JpaRepository มี save() ให้แล้ว

    // ถ้า role SUPERBANSI หรือ item_typeid ไม่เป็น null/empty
    @Query(value = "SELECT r.req_id, r.req_name AS payment_type, r.bansi,i.itemTypeid as projectID, i.itemtype_Name AS project_name " +
            "FROM request_item_type r " +
            "INNER JOIN item_type i ON r.item_typeid = i.itemTypeid " +
            "WHERE (r.bansi = '1' OR r.item_typeid IS NOT NULL) " +
            "AND (:itemTypeId IS NULL OR r.item_typeid = :itemTypeId)",
            nativeQuery = true)
    List<Object[]> findByItemTypeIdOrBansi(@Param("itemTypeId") Long itemTypeId);
}

