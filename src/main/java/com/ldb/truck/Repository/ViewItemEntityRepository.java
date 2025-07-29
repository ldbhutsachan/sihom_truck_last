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
    @Query(value = "SELECT * FROM v_items where branch_no=:branchNo ORDER BY item_name asc", nativeQuery = true)
    List<viewItemEntity> getAllViewItemsBranchNo(@Param("branchNo") String branchNo);


    @Transactional
    @Query(value = "SELECT * FROM v_items ORDER BY item_name asc", nativeQuery = true)
    List<viewItemEntity> getAllViewItemsAdmin();
}
