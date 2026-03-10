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

    @Query(value = "SELECT * FROM v_bor WHERE key_id =? and type_bor=?", nativeQuery = true)
    List<BorViewEntity> findBykeyId(String keyId , String typeBor);

    @Query(value = "SELECT * FROM v_bor where  type_bor=?", nativeQuery = true)
    List<BorViewEntity> getBorViewEntityAll(String typeBor);

    // new
    @Query(value = "SELECT * FROM v_bor where  type_bor=?", nativeQuery = true)
    List<BorViewEntity> getBor4HR(String typeBor);

}
