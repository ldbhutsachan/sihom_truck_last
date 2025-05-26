package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderGenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGenKeyRepository extends CrudRepository<OrderGenEntity,Long> {
}
