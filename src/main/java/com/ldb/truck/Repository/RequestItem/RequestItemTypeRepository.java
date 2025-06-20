package com.ldb.truck.Repository.RequestItem;

import com.ldb.truck.Entity.RequestItem.requestItemTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestItemTypeRepository extends CrudRepository<requestItemTypeEntity,Long> {

}
