package com.ldb.truck.Repository.MachineHis;

import com.ldb.truck.Entity.MerchineHis.MachineToolHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineToolHisRepository extends JpaRepository<MachineToolHis, Long> {

    List<MachineToolHis> findByToolId(Long toolId);

}
