package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderGenReqEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGenKeyReqRepository extends CrudRepository<OrderGenReqEntity,Long> {
    @Query(value = "select * from v_request_item_key ",nativeQuery = true)
    OrderGenReqEntity maxReqKey();
}
