package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.PaymentRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequestEntity, Long> {

    // หา last billNo สำหรับ generate ต่อ
    @Query(value = "SELECT p.bill_No FROM tb_accounting p ORDER BY p.key_id DESC LIMIT 1", nativeQuery = true)
    String findLastBillNo();

    //  หา entity ด้วย billNo
    PaymentRequestEntity findByBillNo(String billNo);

    //  UPDATE pay_status = DONE-PAY ด้วย billNo
    @Modifying
    @Query(value = "UPDATE tb_accounting " +
            "SET pay_status = :status " +
            "WHERE bill_No = :billNo",
            nativeQuery = true)
    int updatePayStatusByBillNo(@Param("billNo") String billNo,
                                @Param("status") String status);
}

