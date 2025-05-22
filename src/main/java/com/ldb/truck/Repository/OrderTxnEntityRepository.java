package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderTxnEntityRepository extends CrudRepository<OrderItemEntity,Long> {
    @Transactional
    @Modifying
    @Query(value = "select * from v_order_item where saveby=:saveby and detail_id=:detailId and status='wait' order by detail_id desc", nativeQuery = true)
    List<OrderItemEntity> getOrderAll(@Param("saveby") String saveby,@Param("detailId") Integer detailId);
@Transactional
    @Modifying
    @Query(value = "select * from v_order_item where saveby=:saveby and status='wait' order by detail_id desc", nativeQuery = true)
    List<OrderItemEntity> getOrderBySaveby(@Param("saveby") String saveby);

@Transactional
    @Modifying
    @Query(value = "select * from v_order_item where saveby=:saveby and bill_no=:billNo and status='wait' order by detail_id desc ", nativeQuery = true)
    List<OrderItemEntity> getOrderByBillNo(@Param("saveby") String saveby,@Param("billNo") String billNo);

    @Transactional
    @Modifying
    @Query(value = "select * from v_order_item where status='wait' order by detail_id desc ", nativeQuery = true)
    List<OrderItemEntity> getOrderByAdmin();

    @Transactional
    @Modifying
    @Query(value = "select * from v_order_item  order by status desc ", nativeQuery = true)
    List<OrderItemEntity> getOrderByBillNoAdmin();

    @Transactional
    @Modifying
    @Query(value = "select * from v_order_item where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate   and status=:status order by detail_id desc ", nativeQuery = true)
    List<OrderItemEntity> getOrderReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "select * from v_order_item where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate order by detail_id desc ", nativeQuery = true)
    List<OrderItemEntity> getOrderReportNoStatus(@Param("startDate") String startDate,@Param("endDate") String endDate);


}
