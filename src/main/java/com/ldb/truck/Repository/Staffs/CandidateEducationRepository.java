package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Long> {
}
