package com.ldb.truck.Dao.Inventory;

import com.ldb.truck.Model.Login.Inventory.Items.ItemReq;
import com.ldb.truck.Model.Login.Inventory.Items.Items;
import com.ldb.truck.Model.Login.Inventory.Shops.ShopReq;
import com.ldb.truck.Model.Login.Inventory.Shops.Shops;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportHeader;
import com.ldb.truck.Model.Login.Report.ReportHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Repository
public class InventoryDao {
    private static final Logger log = LogManager.getLogger(InventoryDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    public List<Items> ListItems(ItemReq itemReq ) {
        try{
            String SQL ="select * from TB_items a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH='"+itemReq.getBranch()+"' ";

            log.info("SQL:"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<Items>() {
                @Override
                public Items mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Items tr = new Items();
                    tr.setItem_id(rs.getString("item_id"));
                    tr.setItemName(rs.getString("item_name"));
                    tr.setUnit(rs.getString("unit"));
                    tr.setUnit_price(rs.getString("unit_price"));
                    tr.setQty(rs.getInt("qty"));
                    tr.setImg(rs.getString("img"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // List Shop
    public List<Shops> ListShops(ShopReq shopReq  ) {
        try{
            String SQL ="select * from TB_shop a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH='"+shopReq.getBranch()+"' ";

            log.info("SQL:"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<Shops>() {
                @Override
                public Shops mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Shops tr = new Shops();
                    tr.setShop_id(rs.getInt("shop_id"));
                    tr.setShop_name(rs.getString("shop_name"));
                    tr.setAddress(rs.getString("address"));
                    tr.setPhone(rs.getString("phone"));
                    tr.setCountry(rs.getString("country"));
                    tr.setCurrency(rs.getString("currency"));
                    tr.setAmount_money(rs.getString("amount_money"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // insert shops
    public int InsertShops(ShopReq shopReq) throws ParseException {
        List<Shops> data = new ArrayList<>();
        try{
            String SQL = "insert into TB_shop (shop_name,address,phone,country,currency,amount_money,userId)values (?,?,?,?,?,?,'"+shopReq.getUserId()+"')";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(shopReq.getShop_name());
            paramList.add(shopReq.getAddress());
            paramList.add(shopReq.getPhone());
            paramList.add(shopReq.getCountry());
            paramList.add(shopReq.getCurrency());
            paramList.add(shopReq.getAmount_money());
            paramList.add(shopReq.getUserId());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    // update Shops
    public int UpdateShops(ShopReq shopReq) throws ParseException {
        List<Shops> data = new ArrayList<>();
        try{
            String SQL = "update TB_shop set shop_name=?,address=?,phone=?,country=?,currency=?,amount_money=?,userId='"+shopReq.getUserId()+"' where shop_id='"+shopReq.getShop_id()+"'";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(shopReq.getShop_name());
            paramList.add(shopReq.getAddress());
            paramList.add(shopReq.getPhone());
            paramList.add(shopReq.getCountry());
            paramList.add(shopReq.getCurrency());
            paramList.add(shopReq.getAmount_money());
            paramList.add(shopReq.getUserId());
            paramList.add(shopReq.getShop_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    // delete shops
    public int delShops(ShopReq shopReq) {
        int i =0;
        try {
            String SQL = "delete from TB_shop where shop_id='"+shopReq.getShop_id()+"'";
            i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    // insert Items
    public int InsertItemDao(ItemReq itemReq ) throws ParseException {
        String path="http://khounkham.com/images/car/";
        String fileName = itemReq.getImg();
        log.info("path:"+path+fileName);

        List<Items> data = new ArrayList<>();
        try{
            String SQL = "insert into TB_items (item_name,unit,unit_price,Qty,img,userId)values (?,?,?,?,?,'"+itemReq.getUserId()+"')";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(itemReq.getItemName());
            paramList.add(itemReq.getUnit());
            paramList.add(itemReq.getUnit_price());
            paramList.add(itemReq.getQty());
            paramList.add(path+fileName);
            paramList.add(itemReq.getUserId());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

   // update items not notmal
   public int UpdateItemDaoHaveData(ItemReq itemReq ) throws ParseException {
       String path="http://khounkham.com/images/car/";
       String fileName = itemReq.getImg();
       log.info("path:"+path+fileName);
       List<Items> data = new ArrayList<>();
       try{
           String SQL = "Update TB_items set item_name=?,unit=?,unit_price=?,Qty=?,userId=? where item_id =?";
           List<Object> paramList = new ArrayList<Object>();
           paramList.add(itemReq.getItemName());
           paramList.add(itemReq.getUnit());
           paramList.add(itemReq.getUnit_price());
           paramList.add(itemReq.getQty());
           paramList.add(itemReq.getUserId());
           paramList.add(itemReq.getItem_id());
           return EBankJdbcTemplate.update(SQL, paramList.toArray());
       }catch (Exception e){
           e.printStackTrace();
           return -1;
       }
   }
   // update item normal
    public int UpdateItem(ItemReq itemReq) throws ParseException {
        String path="http://khounkham.com/images/car/";
        String fileName = itemReq.getImg();
        try {
            String SQL ="update TB_items set item_name=?,unit=?,unit_price=?,Qty=?,img=?,userId=? where  item_id=?";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(itemReq.getItemName());
            paramList.add(itemReq.getUnit());
            paramList.add(itemReq.getUnit_price());
            paramList.add(itemReq.getQty());
            paramList.add(path+fileName);
            paramList.add(itemReq.getItem_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    // delete item
    public int delItem(ItemReq itemReq ) {
        int i =0;
        try {
            String SQL = "delete from TB_items where item_id='"+itemReq.getItem_id()+"'";
           i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

}
