package com.ldb.truck.Repository;

import com.ldb.truck.Entity.EntityUser.UserBorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserBorRepository extends JpaRepository<UserBorEntity, Integer> {

    //insert userBor
    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO LOGIN (USER_LOGIN, PASSOWORD, ROLE, DATE_INSERT, USERID, STATUS, TOKEN, BRANCH, saveById, DEPARTMENT, SPRIT_ROLE, bor_no) " +
                    "VALUES (:userLogin, :password, :role, NOW(), :userId, :status, :token, :branch, :saveById, :department, :spritRole, :borNo)",
            nativeQuery = true
    )
    void insertUserNative(
            @Param("userLogin") String userLogin,
            @Param("password") String password,
            @Param("role") String role,
            @Param("userId") String userId,
            @Param("status") String status,
            @Param("token") String token,
            @Param("branch") String branch,
            @Param("saveById") String saveById,
            @Param("department") String department,
            @Param("spritRole") String spritRole,
            @Param("borNo") String borNo
    );

    //update userBor
    @Modifying
    @Transactional
    @Query(
            value = "UPDATE LOGIN SET USER_LOGIN = :userLogin, PASSOWORD = :password, ROLE = :role, STATUS = :status, TOKEN = :token, " +
                    "BRANCH = :branch, saveById = :saveById, DEPARTMENT = :department, SPRIT_ROLE = :spritRole, bor_no = :borNo " +
                    "WHERE KEY_ID = :keyId",
            nativeQuery = true
    )
    void updateUserNative(
            @Param("keyId") Integer keyId,
            @Param("userLogin") String userLogin,
            @Param("password") String password,
            @Param("role") String role,
            @Param("status") String status,
            @Param("token") String token,
            @Param("branch") String branch,
            @Param("saveById") String saveById,
            @Param("department") String department,
            @Param("spritRole") String spritRole,
            @Param("borNo") String borNo
    );

    //User showing

        // ดึง user ทั้งหมด
        @Query(value = "SELECT * FROM LOGIN", nativeQuery = true)
        List<UserBorEntity> findAllUsersNative();


}
