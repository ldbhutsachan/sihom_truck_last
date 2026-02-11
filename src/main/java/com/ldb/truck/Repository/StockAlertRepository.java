package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Stock.StockAlertEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockAlertRepository extends CrudRepository<StockAlertEntity, Long> {

    @Query(value = "SELECT * FROM v_alert_stock WHERE bor_no = :borNo", nativeQuery = true)
    List<StockAlertEntity> getAlertByBorNo(@Param("borNo") String borNo);

}

