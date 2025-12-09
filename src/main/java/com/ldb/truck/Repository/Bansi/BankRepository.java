package com.ldb.truck.Repository.Bansi;


import com.ldb.truck.Entity.Bansi.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {
}
