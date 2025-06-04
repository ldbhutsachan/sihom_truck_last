package com.ldb.truck.Repository;
import com.ldb.truck.Entity.OrderItem.OrderItemReportEntity;
import com.ldb.truck.Entity.OrderItem.OrderStoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemSaveEntityRepository extends CrudRepository<OrderItemReportEntity,Long> {
}
