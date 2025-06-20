package com.ldb.truck.Repository.Payment;

import com.ldb.truck.Entity.ItemPayment.VCalOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VCalOrderEntityRepository extends CrudRepository<VCalOrderEntity,Long> {

        @Query(value = "SELECT * FROM v_sum_order_item WHERE bill_no = :billNo ORDER BY bill_no DESC", nativeQuery = true)
        List<VCalOrderEntity> getVCalOrderEntity(@Param("billNo") String billNo);

}
