package com.ldb.truck.Repository.Payment;

import com.ldb.truck.Entity.ItemPayment.ItemPaymentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemPaymentEntityRepository extends CrudRepository<ItemPaymentEntity,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE payment_item SET status ='ok' WHERE invoice_no = :invoiceNo", nativeQuery = true)
    int updateStatusPayment( @Param("invoiceNo") String invoiceNo);
}
