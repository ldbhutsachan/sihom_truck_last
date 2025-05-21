package com.ldb.truck.Repository;

import com.ldb.truck.Entity.PlaceStock.PlaceStockEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlaceStockEntityRepository extends CrudRepository<PlaceStockEntity, Long> {

    @Transactional
    @Query(value = "SELECT * FROM stock_house WHERE khid = :khid ORDER BY khid DESC", nativeQuery = true)
    List<PlaceStockEntity> findByPlaceStockEntityKhId(@Param("khid") String khid);

    @Transactional
    @Query(value = "SELECT * FROM stock_house ORDER BY khid DESC", nativeQuery = true)
    List<PlaceStockEntity> findAllStockHouses();


    @Transactional
    @Modifying
    @Query(value = "UPDATE stock_house SET khno = :khNo, khname = :khName, sole = :sole, solestep = :soleStep, blockno = :blockNo, status = :status WHERE khid = :khId", nativeQuery = true)
    int updatePlaceStock(
            @Param("khNo") String khNo,
            @Param("khName") String khName,
            @Param("sole") Integer sole,
            @Param("soleStep") String soleStep,
            @Param("blockNo") String blockNo,
            @Param("status") String status,
            @Param("khId") Long khId);

    @Transactional
    @Modifying
    @Query(value = "delete from stock_house WHERE khid = :khId", nativeQuery = true)
    int delPlaceStock(
            @Param("khId") Long khId);

}