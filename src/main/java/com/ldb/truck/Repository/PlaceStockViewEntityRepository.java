package com.ldb.truck.Repository;

import com.ldb.truck.Entity.PlaceStock.PlaceStockEntity;
import com.ldb.truck.Entity.PlaceStock.PlaceStockViewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceStockViewEntityRepository extends CrudRepository<PlaceStockViewEntity,Long> {
    @Transactional
    @Query(value = "SELECT * FROM v_stock_house ORDER BY khid DESC", nativeQuery = true)
    List<PlaceStockViewEntity> findAllStockHouses();

    @Transactional
    @Query(value =
            "SELECT * FROM v_stock_house " +
                    "WHERE (:borNo IS NULL OR :borNo = '' OR bor_no = :borNo) " +
                    "AND ( :umission <> 'ALAIAPPROVE' " +
                    "      OR not_for_alai IS NULL " +
                    "      OR not_for_alai = '' " +
                    "      OR not_for_alai <> 'Y' ) " +
                    "AND (:status IS NULL OR :status = '' OR stock_status = :status) " +
                    "ORDER BY khid DESC",
            nativeQuery = true)
    List<PlaceStockViewEntity> findAllStockHousesAdminFilterBor(
            @Param("borNo") String borNoForAdmin,
            @Param("umission") String umission,
            @Param("status") String status
    );





    @Transactional
    @Query(value = "SELECT * FROM v_stock_house where branch_no=:branchNo and bor_no=:borNo ORDER BY khid DESC", nativeQuery = true)
    List<PlaceStockViewEntity> findAllStockHousesBranchNo(@Param("branchNo") String branchNo,@Param("borNo") String borNo);
  @Transactional
    @Query(value = "SELECT * FROM v_stock_house where userid=:userId ORDER BY khid DESC", nativeQuery = true)
    List<PlaceStockViewEntity> findAllStockHousesUserId(@Param("userId") String userId);
    @Query(value = "SELECT * FROM v_stock_house where bor_no=:borNo and stock_status='OLD-STOCK'  ORDER BY khid DESC", nativeQuery = true)
    Optional<PlaceStockViewEntity> findByBorNo(@Param("borNo") String borNo);

}
