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

    @Query(value = "SELECT * FROM v_bor " +
            "WHERE type_bor = :typeBor " +
            "AND ( " +
            "   (:role IN ('ADMIN', 'USER') AND type = 'HRM') " +
            "   OR " +
            "   (:role NOT IN ('ADMIN', 'USER') AND type != 'HRM') " +
            ")",
            nativeQuery = true)
    List<BorViewEntity> getBorViewEntityAll(
            @Param("typeBor") String typeBor,
            @Param("role") String role
    );

    // new
    @Query(value = "SELECT * FROM v_bor " +
            "WHERE type_bor = :typeBor " +
            "AND type != 'HRM'",
            nativeQuery = true)
    List<BorViewEntity> getBor4HR(
            @Param("typeBor") String typeBor
    );

}
