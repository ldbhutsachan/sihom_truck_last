package com.ldb.truck.Repository;

import com.ldb.truck.Entity.RequestItem.RequestItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RequestItemEntityRepository extends CrudRepository<RequestItemEntity,String> {
}
