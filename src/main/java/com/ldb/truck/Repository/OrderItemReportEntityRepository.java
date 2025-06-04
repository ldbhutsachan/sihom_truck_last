package com.ldb.truck.Repository;

import com.ldb.truck.Entity.OrderItem.OrderItemReportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemReportEntityRepository extends CrudRepository<OrderItemReportEntity,Long> {
}
