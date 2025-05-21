package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Item.ItemEntity;
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
public interface StockDetailsRepository extends CrudRepository<StockItemDetailsEntity,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE StockItemDetailsEntity s SET s.billNo = :billNo, s.barcode = :barcode, s.itemId = :itemId, "
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
    @Query(value = "UPDATE sotck_item_details SET approveby = :approveBy, " +
            "approvedate = :approveDate, status = :status " +
            "WHERE FIND_IN_SET(detail_id, :detailId)", nativeQuery = true)
    int approveStockItemDetails(
            @Param("approveBy") String approveBy,
            @Param("approveDate") Date approveDate,
            @Param("status") String status,
            @Param("detailId") String detailId); // Use String instead of List<Long>


    @Query(value = "SELECT * FROM sotck_item_details WHERE detail_id IN (:itemId)", nativeQuery = true)
    List<StockItemDetailsEntity> findByItemId(@Param("itemId") List<Long> itemId);
}
