package com.ldb.truck.Repository;

import com.ldb.truck.Entity.Item.ItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface ItemEntityRepository extends CrudRepository<ItemEntity,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE item_inventory i SET " +
            "i.brand_id = :brandId, " +
            "i.supplier_id = :supplierId, " +
            "i.item_name = :itemName," +
            "i.unit = :unit, " +
            "i.size = :size, " +
            "i.currency = :currency, " +
            "i.exchange_rate = :exchangeRate," +
            "i.galaty_start_date = :galatyStartDate, " +
            "i.galaty_end_date = :galatyEndDate, " +
            "i.galaty_amt = :galatyAmt, " +
            "i.qty = :qty," +
            " i.price = :price," +
            " i.image = :image, " +
            "i.approve_by = :approveBy," +
            " i.approve_date = :approveDate," +
            " i.branch_no = :branchNo," +
            " i.barcode = :barcode, " +
            " i.itemtypeid = :itemtypeid, " +
            " i.houseid = :houseid " +
            "WHERE i.item_id = :itemId",nativeQuery = true)
    int updateItem(
            Integer brandId,
            Integer supplierId,
            String itemName,
            Integer unit,
            Integer size,
            String currency,
            Integer exchangeRate,
            String galatyStartDate,
            String galatyEndDate,
            Integer galatyAmt,
            Integer qty,
            Float price,
            String image,
            String approveBy,
            Date approveDate,
            Integer branchNo,
            String barcode,
            Integer itemtypeid,
            Integer houseid,
            Long itemId
    );
 @Modifying
    @Transactional
    @Query(value = "UPDATE item_inventory i SET " +
            "i.brand_id = :brandId, " +
            "i.supplier_id = :supplierId, " +
            "i.item_name = :itemName," +
            "i.unit = :unit, " +
            "i.size = :size, " +
            "i.currency = :currency, " +
            "i.exchange_rate = :exchangeRate," +
            "i.galaty_start_date = :galatyStartDate, " +
            "i.galaty_end_date = :galatyEndDate, " +
            "i.galaty_amt = :galatyAmt, " +
            "i.qty = :qty," +
            " i.price = :price," +
            "i.approve_by = :approveBy," +
            " i.approve_date = :approveDate," +
            " i.branch_no = :branchNo," +
            " i.barcode = :barcode, " +
            " i.itemtypeid = :itemtypeid, " +
            " i.houseid = :houseid " +
            "WHERE i.item_id = :itemId",nativeQuery = true)
    int updateItemNoImage(
            Integer brandId,
            Integer supplierId,
            String itemName,
            Integer unit,
            Integer size,
            String currency,
            Integer exchangeRate,
            String galatyStartDate,
            String galatyEndDate,
            Integer galatyAmt,
            Integer qty,
            Float price,
            String approveBy,
            Date approveDate,
            Integer branchNo,
            String barcode,
            Integer itemtypeid,
            Integer houseid,
            Long itemId
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE item_inventory i SET " +
           // "i.unit =i.unit + :unit, " +
            "i.qty =i.qty + :qty " +
            "WHERE i.item_id =:itemId ",nativeQuery = true)
    int updateStockInItem(
            Integer qty,
            Integer itemId
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE item_inventory i SET i.qty = i.qty - :qty " +
            "WHERE i.item_id = :itemId AND i.qty >= :qty ",
            nativeQuery = true)
    int updateStockInItemOut(Integer qty, Integer itemId);
}
