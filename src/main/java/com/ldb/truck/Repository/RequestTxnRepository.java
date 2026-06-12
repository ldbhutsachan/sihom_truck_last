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
    @Query(value = "select * from v_request_item_fix where status=:status and  saveby=:saveby order by status desc ", nativeQuery = true)
    List<RequestTxnEntity> getStockByBillByUser(@Param("status") String status,@Param("saveby") String saveby);

//    @Transactional
//    @Query(value = "select * from v_request_item_fix where status=:status and branchname=:branchNo order by status desc ", nativeQuery = true)
//    List<RequestTxnEntity> getStockByBillNoAdmin(@Param("status") String status,@Param("branchNo") String branchNo);
@Transactional(readOnly = true)
@Query(
        value = "SELECT * " +
                "FROM v_request_item_fix " +
                "WHERE status = :status " +
                "AND branchname = :branchNo " +
                "AND bor_no = :borNo " +
                "AND (:startDate IS NULL OR :endDate IS NULL OR DATE(savedate) BETWEEN :startDate AND :endDate) " +
                "ORDER BY status DESC",
        nativeQuery = true
)
List<RequestTxnEntity> getRequestBillByBor(
        @Param("branchNo") String branchNo,
        @Param("borNo") String borNo,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate,
        @Param("status") String status
);


    //    @Transactional
//    @Query(value = "select * from v_request_item_fix where  branchname=:branchNo order by status desc ", nativeQuery = true)
//    List<RequestTxnEntity> getStockByBranch(@Param("branchNo") String branchNo);
    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * " +
                    "FROM v_request_item_fix " +
                    "WHERE branchname = :branchNo " +
                    "AND bor_no = :borNo " +
                    "AND (:startDate IS NULL OR :endDate IS NULL OR DATE(savedate) BETWEEN :startDate AND :endDate) " +
                    "ORDER BY status DESC",
            nativeQuery = true
    )
    List<RequestTxnEntity> getStockByBranch(
            @Param("branchNo") String branchNo,
            @Param("borNo") String borNo,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );



    //    @Transactional
//    @Query(value = "select * from v_request_item_fix  order by status desc ", nativeQuery = true)
//    List<RequestTxnEntity> getStockByBillNoAdminAll(@Param("") String uMission);
    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM v_request_item_fix " +
                    "WHERE ( " +
                    "   :uMission IS NULL " +
                    "   OR :uMission = '' " +

                    // APPROVEOID
                    "   OR (:uMission = 'APPROVEOID' AND size = 'nammun') " +

                    // ALAIAPPROVE
                    "   OR (:uMission = 'ALAIAPPROVE' AND bor_no = '145') "+


                    // APPROVEINOUT
//                    "   OR (:uMission = 'APPROVEINOUT' AND size != 'nammun' AND size != 'item') " +
                    "   OR (:uMission = 'APPROVEINOUT' AND size != 'nammun' AND bor_no !='145') " +

                    // ADMIN
//                    "   OR (:uMission = 'ADMIN' AND size != 'item') " +
                    "   OR (:uMission = 'ADMIN') " +


                    ") " +
                    "AND ( " +
                    "   :startDate IS NULL " +
                    "   OR :endDate IS NULL " +
                    "   OR DATE(savedate) BETWEEN :startDate AND :endDate " +
                    ") " +
                    "ORDER BY status DESC",
            nativeQuery = true
    )
    List<RequestTxnEntity> getStockByBillNoAdminAll(
            @Param("uMission") String uMission,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM v_request_item_fix " +
                    "WHERE status = :status AND ( " +
                    "   :uMission IS NULL " +
                    "   OR :uMission = '' " +

                    // APPROVEOID
                    "   OR (:uMission = 'APPROVEOID' AND size = 'nammun') " +

                    // ALAIAPPROVE   bor=145 will show here only cuz all is item
                    "   OR (:uMission = 'ALAIAPPROVE' AND bor_no = '145') "
                    +

                    // APPROVEINOUT
//                    "   OR (:uMission = 'APPROVEINOUT' AND size != 'nammun' AND size != 'item') " +
                    "   OR (:uMission = 'APPROVEINOUT' AND size != 'nammun' AND bor_no !='145' ) " +

                    // ADMIN
//                    "   OR (:uMission = 'ADMIN' AND size != 'item') " +
                    "   OR (:uMission = 'ADMIN') " +


                    ") " +
                    "AND ( " +
                    "   :startDate IS NULL " +
                    "   OR :endDate IS NULL " +
                    "   OR DATE(savedate) BETWEEN :startDate AND :endDate " +
                    ") " +
                    "ORDER BY status DESC",
            nativeQuery = true
    )
    List<RequestTxnEntity> getStockByBillNoAdminStatus(
            @Param("uMission") String uMission,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("status") String status
    );




    @Transactional
    @Query(value = "select * from v_request_item_fix where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate   and status=:status order by detail_id desc ", nativeQuery = true)
    List<RequestTxnEntity> getRequestReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);

    @Transactional
    @Query(value = "select * from v_request_item_fix where to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate order by detail_id desc ", nativeQuery = true)
    List<RequestTxnEntity> getRequestReportNoStatus(@Param("startDate") String startDate,@Param("endDate") String endDate);


    @Transactional
    @Query(value = "select * from v_request_item_fix where branchname=:branchNo and  to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate   and status=:status order by detail_id desc ", nativeQuery = true)
    List<RequestTxnEntity> getRequestReportBranchNo(@Param("startDate") String startDate,
                                                    @Param("endDate") String endDate,
                                                    @Param("status") String status,@Param("branchNo") String branchNo);



    @Transactional
    @Modifying
    @Query(value = "select * from v_request_item_fix where branchname=:branchNo and  to_char(savedate,'yyyy-mm-dd') >=:startDate and to_char(savedate,'yyyy-mm-dd') <=:endDate order by detail_id desc ", nativeQuery = true)
    List<RequestTxnEntity> getRequestReportBranchNStatuso(@Param("startDate") String startDate,
                                                    @Param("endDate") String endDate,
                                                   @Param("branchNo") String branchNo);



}
