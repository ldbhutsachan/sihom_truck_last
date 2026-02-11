package com.ldb.truck.Repository.testRepository;

import com.ldb.truck.Entity.Item.ItemListView;
import com.ldb.truck.Entity.Item.listItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemListRepository extends JpaRepository<listItemEntity, String> {
    @Query(value = "SELECT item_id AS itemId, " +
            "item_name AS itemName, " +
            "image, " +
            "bor_no AS borNo, " +
            "bor_name AS borName, " +
            "size, qty, " +
            "khid, " +
            "khno, " +
            "khname " +
            "FROM v_items_list " +
            "WHERE bor_no = :borNo AND khid = :khid " +
            "ORDER BY item_name ASC",
            nativeQuery = true)
    List<ItemListView> getAllViewItemsBorNo(@Param("borNo") String borNo, @Param("khid") String khid);

    // สำหรับ PADMIN ทั้งหมด
    @Query(value = "SELECT item_id AS itemId, " +
            "item_name AS itemName, " +
            "image, " +
            "bor_no AS borNo, " +
            "bor_name AS borName, " +
            "size, qty," +
            "khid, " +
            "khno, " +
            "khname " +
            "FROM v_items_list " +
            "ORDER BY item_name ASC",
            nativeQuery = true)
    List<ItemListView> getAllViewItemsAdmin();

    // สำหรับ Branch
    @Query(value = "SELECT item_id AS itemId, " +
            "item_name AS itemName, " +
            "image, " +
            "bor_no AS borNo, " +
            "bor_name AS borName, " +
            "size, qty," +
            "khid, " +
            "khno, " +
            "khname " +
            "FROM v_items_list " +
            "WHERE branch_no = :branchNo " +
            "AND bor_no = :borNo " +
            "AND (:khid IS NULL OR :khid = '' OR khid = :khid) " +
            "ORDER BY item_name ASC",
            nativeQuery = true)
    List<ItemListView> getAllViewItemsBranchNo(
            @Param("branchNo") String branchNo,
            @Param("borNo") String borNo,
            @Param("khid") String khid
    );
}
