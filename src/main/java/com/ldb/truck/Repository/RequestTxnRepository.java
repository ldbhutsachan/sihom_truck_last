package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Stock.RequestTxnEntity;
import com.ldb.truck.Entity.Stock.StockTxnEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestTxnRepository extends CrudRepository<RequestTxnEntity,Long> {
    @Transactional
    @Modifying
    @Query(value = "select * from v_request_item_fix where status=:status and  saveby=:saveby order by status desc ", nativeQuery = true)
    List<RequestTxnEntity> getStockByBillByUser(@Param("status") String status,@Param("saveby") String saveby);
    @Transactional
    @Modifying
    @Query(value = "select * from v_request_item_fix where status=:status order by status desc ", nativeQuery = true)
    List<RequestTxnEntity> getStockByBillNoAdmin(@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "select * from v_request_item_fix  order by status desc ", nativeQuery = true)
    List<RequestTxnEntity> getStockByBillNoAdminAll();

    @Transactional
    @Modifying
    @Query(value = "select * from v_request_item_fix where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate   and status=:status order by detail_id desc ", nativeQuery = true)
    List<RequestTxnEntity> getRequestReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "select * from v_request_item_fix where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate order by detail_id desc ", nativeQuery = true)
    List<RequestTxnEntity> getRequestReportNoStatus(@Param("startDate") String startDate,@Param("endDate") String endDate);


}
