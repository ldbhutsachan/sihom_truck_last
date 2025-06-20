package com.ldb.truck.Repository.RequestItem;

import com.ldb.truck.Entity.RequestItem.requestItemTypeBorNameEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface requestItemTypeBorNameEntityRepository extends CrudRepository<requestItemTypeBorNameEntity,Long> {
    @Query(name = "SELECT * FROM v_requestItem_bytype WHERE type = ? AND borNo = ? ",nativeQuery = true)
    List<requestItemTypeBorNameEntity> findByTypeAndBorNo(String type, String borNo);

}
