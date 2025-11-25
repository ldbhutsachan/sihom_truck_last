package com.ldb.truck.Repository;

import com.ldb.truck.Entity.ItemType.ItemTypeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemTypeRepository extends CrudRepository<ItemTypeEntity, Long> {

    @Query(value = "SELECT * FROM item_type WHERE itemtypeid = :itemtypeid ORDER BY itemtypeid DESC", nativeQuery = true)
    List<ItemTypeEntity> getItemTypeById(@Param("itemtypeid") Long itemtypeid);

//    @Query(value = "SELECT * FROM item_type ORDER BY itemtypeid DESC", nativeQuery = true)
//    List<ItemTypeEntity> getItemTypeAll();
    @Query(value = "SELECT * FROM item_type WHERE bansi_use = '1' ORDER BY itemtypeid DESC", nativeQuery = true)
    List<ItemTypeEntity> getItemTypeByBansiUse(@Param("bansiUse") String bansiUse);

    @Query(value = "SELECT * FROM item_type WHERE bansi_use IS NULL OR bansi_use = '' OR bansi_use != '1' ORDER BY itemtypeid DESC", nativeQuery = true)
    List<ItemTypeEntity> getItemTypeByNonBansiUse();


    @Modifying
    @Transactional
    @Query(value = "UPDATE item_type SET itemtype_Name = :itemtype_Name WHERE itemtypeid = :itemtypeid", nativeQuery = true)
    int updateItemType(@Param("itemtype_Name") String itemtype_Name,
                       @Param("itemtypeid") Long itemtypeid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM item_type WHERE itemtypeid = :itemtypeid", nativeQuery = true)
    int delItemType(@Param("itemtypeid") Long itemtypeid);
}


