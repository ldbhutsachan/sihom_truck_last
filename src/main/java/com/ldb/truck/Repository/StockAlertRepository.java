package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Stock.StockAlertEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockAlertRepository extends CrudRepository<StockAlertEntity,Long> {
    @Query(value = "select  * from v_alert_stock where branch_no=:branchNo ",nativeQuery = true)
    List<StockAlertEntity> getAlertByBranchNo(@Param("branchNo") String branchNo);

}
