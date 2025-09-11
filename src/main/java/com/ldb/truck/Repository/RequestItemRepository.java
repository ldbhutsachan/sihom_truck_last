package com.ldb.truck.Repository;

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
    @Transactional//request_item_details
    @Query(value = "UPDATE request_item_details SET rejectby = :rejectby, " +
            "rejectDate =:rejectDate, status ='reject'  " +
            "WHERE FIND_IN_SET(item_id, :itemId) and bill_no=:billNo ", nativeQuery = true)
    int updateItemStatusById(
            @Param("rejectby") String rejectby,
            @Param("rejectDate") Date rejectDate,
            @Param("itemId") Long itemId,
            @Param("billNo") String billNo); // Use String instead of List<Long>

    @Modifying
    @Transactional
    @Query("UPDATE RequestItemEbtity s SET s.billNo = :billNo, s.barcode = :barcode, s.itemId = :itemId, "
            + "s.unit = :unit, s.size = :size, s.currency = :currency, s.exchangeRate = :exchangeRate, s.qty = :qty, "
            + "s.price = :price,s.editBy = :editBy, s.editDate = :editDate, "
            + "s.status = :status , s.type=:type,s.borNo=:borNo ,note=:note"
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
            @Param("type") String type,
            @Param("borNo") String borNo,
            @Param("note") String note,
            @Param("detailId") Integer detailId);

    @Modifying
    @Transactional//request_item_details
    @Query(value = "UPDATE request_item_details SET approveby = :approveBy, " +
            "approvedate =:approveDate, status = :status  " +
            "WHERE FIND_IN_SET(item_id, :itemId) and bill_no=:billNo ", nativeQuery = true)
    int approveRequestItem(
            @Param("approveBy") String approveBy,
            @Param("approveDate") Date approveDate,
            @Param("status") String status,
          //  @Param("beforeQty") Integer beforeQty,

            @Param("itemId") Long itemId,@Param("billNo") String billNo); // Use String instead of List<Long>

    @Modifying
    @Transactional//request_item_details
    @Query(value = "UPDATE request_item_details SET before_qty = :beforeQty  " +
            "WHERE detail_id=:detailId and item_id=:itemId ", nativeQuery = true)
    int approveRequestItemDetails(

            @Param("beforeQty") Integer beforeQty,
            @Param("detailId") Integer detailId, @Param("itemId") String itemId); // Use String instead of List<Long>


    @Modifying
    @Transactional//request_item_details
    @Query(value = "UPDATE request_item SET remark = :remark " +
            "WHERE FIND_IN_SET(bill_no, :billNo)", nativeQuery = true)
    int rejectItemRequestByUser(
            @Param("remark") String remark,
            @Param("billNo") String billNo); // Use String instead of List<Long>@Modifying

    @Transactional//request_item_details
    @Query(value = "UPDATE request_item SET remark = :remark " +
            "WHERE FIND_IN_SET(bill_no, :billNo)", nativeQuery = true)
    int rejectItemRequestRemark(
            @Param("remark") String remark,
            @Param("billNo") String billNo); // Use String instead of List<Long>

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

    @Query(value = "SELECT * FROM request_item_details WHERE detail_id IN (:itemId) and bill_no=:billNo  ", nativeQuery = true)
    List<RequestItemEbtity> findByItemId(@Param("itemId") Long itemId,@Param("billNo") String billNo );


    @Query(value = "SELECT * FROM request_item_details WHERE detail_id IN (:itemId) and bill_no=:billNo  ", nativeQuery = true)
    List<RequestItemEbtity> findByItemId2(@Param("itemId") List<Long> itemId,@Param("billNo") String billNo );

}
