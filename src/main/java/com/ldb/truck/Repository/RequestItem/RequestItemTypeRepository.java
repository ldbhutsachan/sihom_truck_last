package com.ldb.truck.Repository.RequestItem;

import com.ldb.truck.Entity.RequestItem.requestItemTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestItemTypeRepository extends CrudRepository<requestItemTypeEntity, Long> {

    @Query(value = "SELECT * FROM request_item_type WHERE bansi IS NULL", nativeQuery = true)
    List<requestItemTypeEntity> findAllWhereBansiIsNull();
}

