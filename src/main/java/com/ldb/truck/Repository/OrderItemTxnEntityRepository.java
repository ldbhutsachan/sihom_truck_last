package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderItemTxnEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemTxnEntityRepository extends CrudRepository<OrderItemTxnEntity,Long> {
}
