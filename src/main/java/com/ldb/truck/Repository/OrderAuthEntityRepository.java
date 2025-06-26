package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderAuthEntity;
import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAuthEntityRepository extends CrudRepository<OrderAuthEntity,Long> {
    @Query(value = "select * from v_order_item where branchno=:branchno and status in ('auth','wait','wait-item') order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderAuthByBranchNo(@Param("branchno") String branchno);

    @Query(value = "select * from v_order_item where status  in ('auth') order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderAuthByBuyer();

    @Query(value = "select * from v_order_item where status  in ('buyer') order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderAuthByAccounting();

    @Query(value = "select * from v_order_item  order by status desc ", nativeQuery = true)
    List<OrderAuthEntity> getOrderByAdmin();
}
