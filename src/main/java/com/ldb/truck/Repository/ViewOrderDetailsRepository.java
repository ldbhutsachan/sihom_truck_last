package com.ldb.truck.Repository;


import com.ldb.truck.Entity.OrderItem.ViewOrderItemReportEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ViewOrderDetailsRepository extends CrudRepository<ViewOrderItemReportEntity,Long> {

    @Query(value = "SELECT * FROM v_aprove_tostock WHERE detail_id IN (:itemId) ", nativeQuery = true)
    List<ViewOrderItemReportEntity> findByItemIdToStock(@Param("itemId") List<Long> itemId);
}
