package com.ldb.truck.Repository.Payment;

import com.ldb.truck.Entity.ItemPayment.ItemDetailsEntity;
import com.ldb.truck.Entity.ItemPayment.ItemPaymentViewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDetailsEntityRepository extends CrudRepository<ItemDetailsEntity,Long> {
    @Query(value = "SELECT * FROM v_payment_item_del ORDER BY status DESC", nativeQuery = true)
    List<ItemDetailsEntity> getBillPaymentDelAll();
}
