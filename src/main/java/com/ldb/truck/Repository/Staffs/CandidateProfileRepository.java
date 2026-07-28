package com.ldb.truck.Repository.Staffs;

import com.ldb.truck.Entity.Staff.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Long> {

    List<CandidateProfile> findByEmailIgnoreCaseAndAddrDistrictIgnoreCaseAndReligionIgnoreCase(String email, String addrDistrict, String religion);

    boolean existsByFullnameIgnoreCaseAndEmailIgnoreCase(String fullname, String email);

    List<CandidateProfile> findByFullnameIgnoreCaseAndEmailIgnoreCase(String fullname, String email);

    @Query("SELECT c FROM CandidateProfile c WHERE " +
           "(:status IS NULL OR LOWER(c.status) = LOWER(:status)) AND " +
           "(:startDate IS NULL OR c.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR c.createdAt <= :endDate) " +
           "ORDER BY c.createdAt DESC")
    List<CandidateProfile> filterCandidates(
            @Param("status") String status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    List<CandidateProfile> findAllByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime startDate, LocalDateTime endDate);

    List<CandidateProfile> findAllByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(LocalDateTime startDate);

    List<CandidateProfile> findAllByCreatedAtLessThanEqualOrderByCreatedAtDesc(LocalDateTime endDate);

    List<CandidateProfile> findAllByOrderByCreatedAtDesc();
}
