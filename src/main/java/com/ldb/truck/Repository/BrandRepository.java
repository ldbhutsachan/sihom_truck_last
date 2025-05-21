package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Brand.BrandEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM brand WHERE brandId = ?1 ORDER BY brandId DESC", nativeQuery = true)
    List<BrandEntity> findByBrandId(String brandId);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM brand ORDER BY brandId DESC", nativeQuery = true)
    List<BrandEntity> findByAll();

    @Transactional
    @Modifying
    @Query(value = "UPDATE brand SET brand_Name =:brandName, company_Product =:companyProduct," +
            " status =:status WHERE brandid =:brandid ", nativeQuery = true)
    int update(@Param("brandName") String brandName,@Param("companyProduct") String companyProduct,@Param("status") String status,
               @Param("brandid") String brandid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE brand SET status = 'disable' WHERE brandid =:brandid ", nativeQuery = true)
    int updateStatus(@Param("brandid") String brandid);

}