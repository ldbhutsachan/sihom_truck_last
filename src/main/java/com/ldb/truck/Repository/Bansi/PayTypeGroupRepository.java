package com.ldb.truck.Repository.Bansi;

import com.ldb.truck.Entity.Bansi.PayTypeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayTypeGroupRepository extends JpaRepository<PayTypeGroup, Integer> {

    List<PayTypeGroup> findByPid(Integer pid);

    List<PayTypeGroup> findAllByOrderByGidDesc();
}
