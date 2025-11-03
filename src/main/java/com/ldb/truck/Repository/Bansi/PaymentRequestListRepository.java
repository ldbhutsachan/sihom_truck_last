package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.PaymentDetailListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestListRepository extends JpaRepository<PaymentDetailListEntity, Long> {
}
