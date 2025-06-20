package com.ldb.truck.Repository.Payment;

import com.ldb.truck.Entity.ItemPayment.PaymentDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsEntityRepository extends CrudRepository<PaymentDetailsEntity,Long> {
}
