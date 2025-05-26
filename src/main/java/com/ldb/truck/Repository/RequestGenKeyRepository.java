package com.ldb.truck.Repository;

import com.ldb.truck.Entity.RequestItem.RequestGenKeyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestGenKeyRepository extends CrudRepository<RequestGenKeyEntity,Long> {
}
