package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Bor.BorViewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BorRepository extends CrudRepository<BorViewEntity,Long> {

    @Query(value = "SELECT * FROM v_bor WHERE key_id = :keyId", nativeQuery = true)
    List<BorViewEntity> findBykeyId(@Param("keyId") String keyId);

    @Query(value = "SELECT * FROM v_bor", nativeQuery = true)
    List<BorViewEntity> getBorViewEntityAll();

}
