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
public interface ItemTypeRepository extends CrudRepository<ItemTypeEntity,Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM item_type WHERE itemtypeid =:itemtypeid order by itemtypeid desc ", nativeQuery = true)
    public List<ItemTypeEntity> getItemTypeById(@Param("itemtypeid") String itemtypeid);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM item_type order by itemtypeid desc ", nativeQuery = true)
    public List<ItemTypeEntity> getItemTypeAll();

    @Transactional
    @Modifying
    @Query(value = "update  item_type set itemtype_Name=:itemtype_Name where itemtypeid=:itemtypeid  ", nativeQuery = true)
    int updateItemType(@Param("itemtype_Name") String itemtype_Name, @Param("itemtypeid") String itemtypeid);

    @Transactional
    @Modifying
    @Query(value = "delete from   item_type  where itemtypeid=:itemtypeid", nativeQuery = true)
    int delItemType(@Param("itemtypeid") String itemtypeid);

}
