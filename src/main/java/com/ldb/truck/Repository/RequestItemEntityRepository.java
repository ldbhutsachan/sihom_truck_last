package com.ldb.truck.Repository;

import com.ldb.truck.Entity.RequestItem.RequestItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestItemEntityRepository extends CrudRepository<RequestItemEntity,String> {
}
