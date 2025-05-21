package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Stock.StockAlertEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAlertRepository extends CrudRepository<StockAlertEntity,Long> {

}
