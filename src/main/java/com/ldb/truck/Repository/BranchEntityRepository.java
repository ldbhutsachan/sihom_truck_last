package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Branch.BranchEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchEntityRepository extends CrudRepository<BranchEntity,Long> {
    @Query(value = "select * from TB_BRANCH",nativeQuery = true)
    List<BranchEntity> getBranchEntity();
}
