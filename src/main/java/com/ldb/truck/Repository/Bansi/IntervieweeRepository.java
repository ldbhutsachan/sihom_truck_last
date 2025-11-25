package com.ldb.truck.Repository.Bansi;


import com.ldb.truck.Entity.Bansi.IntervieweeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervieweeRepository extends JpaRepository<IntervieweeEntity, Integer> {
}
