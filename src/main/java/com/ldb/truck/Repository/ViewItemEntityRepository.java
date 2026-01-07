package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Item.viewItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ViewItemEntityRepository extends CrudRepository<viewItemEntity,Long> {
    @Transactional
    @Query(value = "SELECT * FROM v_items WHERE item_id =:item_id and make_by_id=:make_by_id ORDER BY item_name asc", nativeQuery = true)
    List<viewItemEntity> getItemByItemId(@Param("item_id") String item_id,@Param("make_by_id") String make_by_id);

    @Transactional
    @Query(value = "SELECT * FROM v_items WHERE item_id =:item_id ORDER BY item_name asc", nativeQuery = true)
    List<viewItemEntity> getItemByItemIds(@Param("item_id") Integer item_id);

    @Transactional
    @Query(value = "SELECT * FROM v_items where make_by_id=:make_by_id ORDER BY item_name asc", nativeQuery = true)
    List<viewItemEntity> getAllViewItemsUserId(@Param("make_by_id") String make_by_id);

    @Transactional
    @Query(
            value = "SELECT * FROM v_items " +
                    "WHERE branch_no = :branchNo " +
                    "AND bor_no = :borNo " +
                    "AND (:khid IS NULL OR :khid = '' OR khid = :khid) " +
                    "ORDER BY item_name ASC",
            nativeQuery = true
    )
    List<viewItemEntity> getAllViewItemsBranchNo(
            @Param("branchNo") String branchNo,
            @Param("borNo") String borNo,
            @Param("khid") String khid
    );




    @Transactional
    @Query(value = "SELECT * FROM v_items ORDER BY item_name asc", nativeQuery = true)
    List<viewItemEntity> getAllViewItemsAdmin();

    @Transactional
    @Query(value =
            "SELECT * " +
                    "FROM v_items " +
                    "WHERE " +
                    "(:itemId IS NULL OR :itemId = '' OR item_id = :itemId) " +
                    "AND (:borNo IS NULL OR :borNo = '' OR bor_no = :borNo) " +
                    "AND (:khId IS NULL OR :khId = '' OR khid = :khId) " +
                    "AND ( " +
                    "     (:startDate IS NULL OR :startDate = '') " +
                    "     OR make_date >= STR_TO_DATE(:startDate,'%Y-%m-%d') " +
                    ") " +
                    "AND ( " +
                    "     (:endDate IS NULL OR :endDate = '') " +
                    "     OR make_date <= STR_TO_DATE(:endDate,'%Y-%m-%d') " +
                    ") " +
                    "ORDER BY item_name ASC",
            nativeQuery = true)
    List<viewItemEntity> searchViewItems(
            @Param("itemId") String itemId,
            @Param("borNo") String borNo,
            @Param("khId") String khId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );


    @Transactional
    @Query(value = "SELECT * FROM v_items WHERE bor_no = :borNo and khid =:khid ORDER BY item_name ASC", nativeQuery = true)
    List<viewItemEntity> getAllViewItemsBorNo(@Param("borNo") String borNo,@Param("khid") String khid);



    @Transactional
    @Query(value = "SELECT * FROM v_items WHERE item_id =:item_id ORDER BY item_name asc", nativeQuery = true)
    viewItemEntity getItemByItemId(@Param("item_id") Integer item_id);
}
