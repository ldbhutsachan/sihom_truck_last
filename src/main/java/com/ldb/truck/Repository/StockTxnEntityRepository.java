package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Stock.StockTxnEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StockTxnEntityRepository extends CrudRepository<StockTxnEntity,Long> {
    @Transactional
    @Modifying
    @Query(value = "select * from v_stock_details where saveby=:saveby and detail_id=:detailId and status='wait' order by detail_id desc", nativeQuery = true)
    List<StockTxnEntity> getStockAll(@Param("saveby") String saveby,@Param("detailId") Integer detailId);
@Transactional
    @Modifying
    @Query(value = "select * from v_stock_details where saveby=:saveby and status='wait' order by detail_id desc", nativeQuery = true)
    List<StockTxnEntity> getStockBySaveby(@Param("saveby") String saveby);

@Transactional
    @Modifying
    @Query(value = "select * from v_stock_details where saveby=:saveby and bill_no=:billNo and status='wait' order by detail_id desc ", nativeQuery = true)
    List<StockTxnEntity> getStockByBillNo(@Param("saveby") String saveby,@Param("billNo") String billNo);

    @Transactional
    @Modifying
    @Query(value = "select * from v_stock_details where status='wait' order by detail_id desc ", nativeQuery = true)
    List<StockTxnEntity> getStockByAdmin();


    @Transactional
    @Modifying
    @Query(value = "select * from v_stock_details  order by status desc ", nativeQuery = true)
    List<StockTxnEntity> getStockByBillNoAdmin();

    @Transactional
    @Modifying
    @Query(value = "select * from v_stock_details where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate   and status=:status order by detail_id desc ", nativeQuery = true)
    List<StockTxnEntity> getStockReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "select * from v_stock_details where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate order by detail_id desc ", nativeQuery = true)
    List<StockTxnEntity> getStockReportNoStatus(@Param("startDate") String startDate,@Param("endDate") String endDate);



}
