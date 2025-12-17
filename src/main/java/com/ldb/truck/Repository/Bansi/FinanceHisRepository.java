package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.FinanceHisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceHisRepository extends JpaRepository<FinanceHisEntity, Long> {
}
