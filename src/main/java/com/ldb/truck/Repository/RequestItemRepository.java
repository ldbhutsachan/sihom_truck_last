package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Entity.RequestItem.RequestItemEbtity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface RequestItemRepository extends CrudRepository<RequestItemEbtity,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE RequestItemEbtity s SET s.billNo = :billNo, s.barcode = :barcode, s.itemId = :itemId, "
            + "s.unit = :unit, s.size = :size, s.currency = :currency, s.exchangeRate = :exchangeRate, s.qty = :qty, "
            + "s.price = :price,s.editBy = :editBy, s.editDate = :editDate, "
            + "s.status = :status , s.headerNo=:headerNo,s.footerNo=:footerNo ,note=:note"
            + " WHERE s.detailId = :detailId")
    int updateStockItemDetails(

            @Param("billNo") String billNo,
            @Param("barcode") String barcode,
            @Param("itemId") Integer itemId,
            @Param("unit") Float unit,
            @Param("size") Integer size,
            @Param("currency") String currency,
            @Param("exchangeRate") Integer exchangeRate,
            @Param("qty") Integer qty,
            @Param("price") Float price,
            @Param("editBy") String editBy,
            @Param("editDate") Date editDate,
            @Param("status") String status,
            @Param("headerNo") String headerNo,
            @Param("footerNo") String footerNo,
            @Param("note") String note,
            @Param("detailId") Integer detailId);

    @Modifying
    @Transactional//request_item_details
    @Query(value = "UPDATE request_item_details SET approveby = :approveBy, " +
            "approvedate = :approveDate, status = :status " +
            "WHERE FIND_IN_SET(detail_id, :detailId)", nativeQuery = true)
    int approveRequestItem(
            @Param("approveBy") String approveBy,
            @Param("approveDate") Date approveDate,
            @Param("status") String status,
            @Param("detailId") String detailId); // Use String instead of List<Long>

    @Modifying
    @Transactional
    @Query(value = "UPDATE RequestItemEbtity SET acceptby = :acceptby, " +
            "acceptdate = :acceptdate, status = :status " +
            "WHERE FIND_IN_SET(detail_id, :detailId)", nativeQuery = true)
    int approveToStock(
            @Param("acceptby") String acceptby,
            @Param("acceptdate") Date acceptdate,
            @Param("status") String status,
            @Param("detailId") String detailId); // Use String instead of List<Long>

    @Query(value = "SELECT * FROM request_item_details WHERE detail_id IN (:itemId) and status='wait-order' ", nativeQuery = true)
    List<RequestItemEbtity> findByItemId(@Param("itemId") List<Long> itemId);


}
