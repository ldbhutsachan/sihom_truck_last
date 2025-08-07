package com.ldb.truck.Repository;

import com.ldb.truck.Entity.User.UserHisEntity;
import com.ldb.truck.Entity.User.VUserHisEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserHisRepository extends CrudRepository<UserHisEntity,String> {
    @Query(value = "select * from user_stock_his where user_id = ? ",nativeQuery = true)
    List<UserHisEntity> getDetailsUserHis (String userId);


    @Query(value = "select * from V_USER_HIS_STOCK where details_id = ? ",nativeQuery = true)
    List<VUserHisEntity> getDetailViewIEWUserHis (String detailId);


}
