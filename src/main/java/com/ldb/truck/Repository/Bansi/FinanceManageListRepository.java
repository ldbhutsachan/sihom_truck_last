package com.ldb.truck.Repository.Bansi;


import com.ldb.truck.Entity.Bansi.FinanceManageListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceManageListRepository extends JpaRepository<FinanceManageListEntity, Long> {

}
