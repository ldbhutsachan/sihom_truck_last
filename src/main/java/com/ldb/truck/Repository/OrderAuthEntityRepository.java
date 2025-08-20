package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderAuthEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAuthEntityRepository extends CrudRepository<OrderAuthEntity,Long> {
    @Query(value = "select * from v_order_item where branchno=:branchno and status=:status and borkey=:borNo order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderAuthByBranchNoByMaker(@Param("branchno") String branchno,@Param("status") String status,@Param("borNo") String borNo );

   // @Query(value = "select * from v_order_item where branchno=:branchno and status in ('auth','wait','wait-item') order by status desc ", nativeQuery = true)
    @Query(value = "select * from v_order_item where branchno=:branchno and  status=:status and borkey=:borNo order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderAuthByBranchNo(@Param("branchno") String branchno,@Param("status") String status,@Param("borNo") String borNo);

   // @Query(value = "select * from v_order_item where status  in ('auth') order by status desc ", nativeQuery = true)
    @Query(value = "select * from v_order_item where status=:status order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderAuthByBuyer(@Param("status") String status);

  //  @Query(value = "select * from v_order_item where status  in ('buyer') order by status desc ", nativeQuery = true)
    @Query(value = "select * from v_order_item where status=:status order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderAuthByAccounting(@Param("status") String status);

    @Query(value = "select * from v_order_item order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderByAdmin(@Param("status") String status);
}
