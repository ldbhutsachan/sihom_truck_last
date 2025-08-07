package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderGenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGenKeyRepository extends CrudRepository<OrderGenEntity,Long> {
    @Query(value = "select * from v_order_item_key ",nativeQuery = true)
    OrderGenEntity maxReqKey();
}
