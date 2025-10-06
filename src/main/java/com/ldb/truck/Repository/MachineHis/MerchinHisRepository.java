package com.ldb.truck.Repository.MachineHis;

import com.ldb.truck.Entity.MerchineHis.MerchineHisEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MerchinHisRepository extends CrudRepository<MerchineHisEntity,String> {

    @Query(value = "SELECT * FROM tb_machine_his WHERE key_id =:keyId order by txn_date desc",nativeQuery = true)
    Optional<MerchineHisEntity> findByKeyId (@Param("keyId") Integer keyId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_machine_his SET status = 3  WHERE mch_no =:mchNo", nativeQuery = true)
    int updateMachineStatusToClosed(@Param("mchNo") String mchNo);


}
