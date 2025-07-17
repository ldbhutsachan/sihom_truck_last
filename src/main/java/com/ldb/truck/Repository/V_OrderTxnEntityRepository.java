package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Entity.OrderItem.V_order_item_details;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface V_OrderTxnEntityRepository extends CrudRepository<V_order_item_details,Long> {
    @Query(value = "select * from v_order_item where saveby=:saveby and detail_id=:detailId  order by detail_id desc", nativeQuery = true)
    List<V_order_item_details> getOrderAll(@Param("saveby") String saveby,@Param("detailId") Integer detailId);

    @Query(value = "select * from v_order_item where saveby=:saveby and status=:status  order by detail_id desc", nativeQuery = true)
    List<V_order_item_details> getOrderBySaveby(@Param("saveby") String saveby,@Param("status") String status);

    @Query(value = "select * from v_order_item where saveby=:saveby and bill_no=:billNo and status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByBillNo(@Param("saveby") String saveby,@Param("billNo") String billNo,@Param("status") String status);

   // @Query(value = "select * from v_order_item where status in ('wait','wait-order') order by detail_id desc ", nativeQuery = true)
    @Query(value = "select * from v_order_item where status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByAdmin(@Param("status") String status);



    @Query(value = "select * from v_order_item  order by status desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByBillNoAdmin();

    @Query(value = "select * from v_order_item where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate   and status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);

    @Query(value = "select * from v_order_item where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderReportNoStatus(@Param("startDate") String startDate,@Param("endDate") String endDate);






}
