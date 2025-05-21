package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Stock.StockItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<StockItemEntity,Long> {
}
