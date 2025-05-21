package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Stock.ItemGenKeyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemKeyRepository extends CrudRepository<ItemGenKeyEntity, Integer> {
    @Query(value = "SELECT * FROM v_genkey_item", nativeQuery = true)
    List<ItemGenKeyEntity> getKeyItem();
}