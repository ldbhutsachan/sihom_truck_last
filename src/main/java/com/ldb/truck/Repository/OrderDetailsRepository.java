package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Entity.OrderItem.OrderItemReportEntity;
import com.ldb.truck.Entity.Stock.StockItemDetailsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderItemReportEntity,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE OrderItemEntity s SET s.billNo = :billNo, s.barcode = :barcode, s.itemId = :itemId, "
            + "s.unit = :unit, s.size = :size, s.currency = :currency, s.exchangeRate = :exchangeRate, s.qty = :qty, "
            + "s.price = :price,s.editBy = :editBy, s.editDate = :editDate, "
            + "s.status = :status "
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
            @Param("detailId") Integer detailId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE order_item_details SET approveby = :approveBy, " +
            "approvedate = :approveDate, status = :status " +
            "WHERE FIND_IN_SET(detail_id, :detailId) and bill_no =:billNo ", nativeQuery = true)
    int approveStockItemDetailsAuth(
            @Param("approveBy") String approveBy,
            @Param("approveDate") Date approveDate,
            @Param("status") String status,
            @Param("billNo") String billNo,
            @Param("detailId") String detailId); // Use String instead of List<Long>



  @Modifying
    @Transactional
    @Query(value = "UPDATE order_item_details SET buyer_id = :buyerId, " +
            "buyer_date = :buyerDate, status = :status " +
            "WHERE FIND_IN_SET(detail_id, :detailId) and bill_no =:billNo and status='auth' ", nativeQuery = true)
    int approveStockItemDetailsBuyer(
            @Param("buyerId") String buyerId,
            @Param("buyerDate") Date buyerDate,
            @Param("status") String status,
            @Param("billNo") String billNo,
            @Param("detailId") String detailId); // Use String instead of List<Long>
 @Modifying
    @Transactional
    @Query(value = "UPDATE order_item_details SET account_id = :accountId, " +
            "account_date = :accountDate, status = :status " +
            "WHERE FIND_IN_SET(detail_id, :detailId) and bill_no =:billNo  ", nativeQuery = true)
    int approveStockItemDetailsAccounting(
            @Param("accountId") String accountId,
            @Param("accountDate") Date accountDate,
            @Param("status") String status,
            @Param("billNo") String billNo,
            @Param("detailId") String detailId); // Use String instead of List<Long>
  @Modifying
    @Transactional
    @Query(value = "UPDATE order_item_details SET acceptby = :acceptby, " +
            "acceptdate = :acceptdate, status = :status " +
            "WHERE FIND_IN_SET(detail_id, :detailId) ", nativeQuery = true)
    int approveToStock(
            @Param("acceptby") String acceptby,
            @Param("acceptdate") Date acceptdate,
            @Param("status") String status,
            @Param("detailId") String detailId); // Use String instead of List<Long>

    @Query(value = "SELECT * FROM order_item_details WHERE detail_id IN (:itemId) and status='wait-order'", nativeQuery = true)
    List<OrderItemReportEntity> findByItemId(@Param("itemId") List<Long> itemId);
}
