package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.SignatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SignatureRepository extends JpaRepository<SignatureEntity, Long> {
}
