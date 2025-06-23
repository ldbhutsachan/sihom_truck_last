package com.ldb.truck.Repository;

import com.ldb.truck.Entity.ItemPayment.ItemPaymentViewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemPaymentViewEntityRepository extends CrudRepository<ItemPaymentViewEntity,Long> {
    @Query(value = "SELECT * FROM v_payment_item ORDER BY status DESC", nativeQuery = true)
    List<ItemPaymentViewEntity> getBillPaymentAll();
    @Query(value = "SELECT * FROM v_payment_item " +
            "WHERE DATE(savedate) >= :startDate " +
            "AND DATE(savedate) <= :endDate " +
            "AND status = :status " +
            "ORDER BY status DESC", nativeQuery = true)
    List<ItemPaymentViewEntity> getBillPaymentByDateHaveStatus(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("status") String status);

    @Query(value = "SELECT * FROM v_payment_item " +
            "WHERE DATE(savedate) >= :startDate " +
            "AND DATE(savedate) <= :endDate " +
            "AND status = :status AND borno=:borno " +
            "ORDER BY status DESC", nativeQuery = true)
    List<ItemPaymentViewEntity> getBillPaymentByDateHaveStatusBor(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("status") String status,
            @Param("borno") String borno);
    @Query(value = "SELECT * FROM v_payment_item WHERE DATE(savedate) >=:startDate  AND DATE(savedate) <= :endDate ORDER BY status DESC", nativeQuery = true)
    List<ItemPaymentViewEntity> findPaymentsByDateRangeBor(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT * FROM v_payment_item WHERE DATE(savedate) >=:startDate  AND DATE(savedate) <= :endDate and borno=:borno ORDER BY status DESC", nativeQuery = true)
    List<ItemPaymentViewEntity> findPaymentsByDateRangeBorNumber(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("borno") String borno);


}
