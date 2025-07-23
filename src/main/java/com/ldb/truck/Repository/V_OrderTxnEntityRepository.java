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
    @Query(value = "select * from v_order_item where saveby=:saveby and status=:status  order by detail_id desc", nativeQuery = true)
    List<V_order_item_details> getOrderBySaveby(@Param("saveby") String saveby,@Param("status") String status);

    @Query(value = "select * from v_order_item where status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByAdmin(@Param("status") String status);

    @Query(value = "select * from v_order_item where branchno=:branchNo and status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByBr(@Param("status") String status);

    @Query(value = "select * from v_order_item where status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderBybuyer(@Param("status") String status);

    @Query(value = "select * from v_order_item where  status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByAccount(@Param("status") String status);

     @Query(value = "select * from v_order_item where branchno=:branchNo and saveby=:saveby and status=:status  order by detail_id desc", nativeQuery = true)
     List<V_order_item_details> getOrderBySavebyWithBranchNo(@Param("branchNo") String branchNo,@Param("saveby") String saveby,@Param("status") String status);

    @Query(value = "select * from v_order_item where branchno=:branchNo and status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByBranchNo(@Param("branchNo") String branchNo,@Param("status") String status);

    @Query(value = "select * from v_order_item where  branchno=:branchNo and status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderBybuyerBranchNo(@Param("branchNo") String branchNo,@Param("status") String status);
    @Query(value = "select * from v_order_item where  branchno=:branchNo and status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByAccountBranchNo(@Param("branchNo") String branchNo,@Param("status") String status);

    @Query(value = "select * from v_order_item where status=:status order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByAdminBranchNo(@Param("status") String status);

    //================status all
    @Query(value = "select * from v_order_item where branchno=:branchNo and saveby=:saveby   order by detail_id desc", nativeQuery = true)
    List<V_order_item_details> getOrderBySavebyStatus(@Param("branchNo") String branchNo,@Param("saveby") String saveby);
    @Query(value = "select * from v_order_item where branchno=:branchNo  order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByBranchNoStatusAll(@Param("branchNo") String branchNo);
    @Query(value = "select * from v_order_item where branchno=:branchNo  order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByBranchNoStatus(@Param("branchNo") String branchNo);
    @Query(value = "select * from v_order_item  order by detail_id desc ", nativeQuery = true)
    List<V_order_item_details> getOrderByAdminNo();

}
