package com.ldb.truck.Repository;

import com.ldb.truck.Entity.ItemPayment.VPaymentKeyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceKeyRepository extends CrudRepository<VPaymentKeyEntity, Long> {

    @Query(value = "SELECT max_stock_id FROM v_payment_key", nativeQuery = true)
    Optional<String> getMaxStockId();
}
