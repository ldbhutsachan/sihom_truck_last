package com.ldb.truck.Dao.Inventory;

import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilModel;
import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilReq;
import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilRes;
import com.ldb.truck.Model.Login.DocumentStorage.DataHoleModel;
import com.ldb.truck.Model.Login.DocumentStorage.DataHoleReq;
import com.ldb.truck.Model.Login.Inventory.CUR.ReportOfferPaperModel;
import com.ldb.truck.Model.Login.Inventory.CUR.ReportOfferPaperModelLAK;
import com.ldb.truck.Model.Login.Inventory.CUR.ReportOfferPaperModelTHB;
import com.ldb.truck.Model.Login.Inventory.Fix.FixModelFaso;
import com.ldb.truck.Model.Login.Inventory.Fix.FixReq;
import com.ldb.truck.Model.Login.Inventory.Fix.FixReqListProve.ReqListOfFixModel;
import com.ldb.truck.Model.Login.Inventory.Fix.ShowFixModel;
import com.ldb.truck.Model.Login.Inventory.Items.ItemHis;
import com.ldb.truck.Model.Login.Inventory.Items.ItemHisReq;
import com.ldb.truck.Model.Login.Inventory.Items.ItemReq;
import com.ldb.truck.Model.Login.Inventory.Items.Items;
import com.ldb.truck.Model.Login.Inventory.OfferPaper.*;
import com.ldb.truck.Model.Login.Inventory.Old_inventory.OldInventoryModel;
import com.ldb.truck.Model.Login.Inventory.Old_inventory.OldInventoryReq;
import com.ldb.truck.Model.Login.Inventory.Report_Stock.ReportstockModel;
import com.ldb.truck.Model.Login.Inventory.Report_Stock.ReportstockModel2;
import com.ldb.truck.Model.Login.Inventory.Report_Stock.ReportstockReq;
import com.ldb.truck.Model.Login.Inventory.Shops.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Repository
public class InventoryDao {
    private static final Logger log = LogManager.getLogger(InventoryDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    public List<Items> ListItems(ItemReq itemReq ) {
        String SQL;
        try{
            if (itemReq.getBranch_id()!=null) {
                SQL = "select * from TB_items a join LOGIN b on a.userId =b.KEY_ID join TB_BRANCH tb on a.branch_id=tb.KEY_ID  where a.branch_id='"+itemReq.getBranch_id()+"'";
                 log.info("SQL_notNull:"+SQL);
            }else {
                SQL = "select * from TB_items a join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH='" + itemReq.getBranch() + "' ";
                log.info("SQL_Null:"+SQL);
            }
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
                    tr.setSize(rs.getString("size"));
                    tr.setBrand(rs.getString("brand"));
                    tr.setBer(rs.getString("ber"));
                    tr.setLimitQty(rs.getString("limitQty"));
//                    tr.setBranch_name(rs.getString("B_NAME"));
//                    tr.setBranch_id(rs.getString("KEY_ID"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    Detail item DAOs
public List<Items> ItemsDtailItemDAOs(ItemReq itemReq ) {
    try{
//        String SQL ="select * from TB_items a inner join LOGIN b on a.userId =b.KEY_ID  where a.item_id='"+itemReq.getItem_id()+"' ";
        String SQL ="select * from TB_items where item_id='"+itemReq.getItem_id()+"' ";

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
                tr.setSize(rs.getString("size"));
                tr.setBrand(rs.getString("brand"));
                tr.setBer(rs.getString("ber"));
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
            String SQL;
            if (shopReq.getBranch_id()!=null)
            {
                SQL = "select * from TB_shop a join TB_BRANCH b on a.branch_id = b.KEY_ID where b.KEY_ID ='" + shopReq.getBranch_id() + "'";
                log.info("SQL:" + SQL);
            }
            else {
                SQL = "select * from TB_shop a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH='" + shopReq.getBranch() + "'and a.shop_id not in (8,26,27,57,142)";
                log.info("SQL:" + SQL);
            }

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
//    list of his fill
public List<FillOilModel> ListHisFillOillDao(FillOilReq fillOilReq) {
    try{
        String SQL ="select * from V_FILL_OIL where carId='"+fillOilReq.getCarId()+"' ";

        log.info("SQL:"+SQL);
        return EBankJdbcTemplate.query(SQL, new RowMapper<FillOilModel>() {
            @Override
            public FillOilModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                FillOilModel tr = new FillOilModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setCarId(rs.getString("carId"));
                tr.setPrice(rs.getDouble("price"));
                tr.setLekmai(rs.getString("lekmai"));
                tr.setDateFill(rs.getString("date_fill"));
                tr.setLekmai_dif(rs.getString("lekmai_difference"));
                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
    //report list shop must pay
    public List<ReportShops> ListShopsMustPayDao(ShopReq shopReq  ) {
        String SQL ="";
        try{
            if (shopReq.getBranch_id() != null)
            {
                if(shopReq.getStartDate()==null && shopReq.getEndDate()==null){
                    SQL ="select * from REPORT_SHOPS_MUST_PAY  where branch_id='"+shopReq.getBranch_id()+"' ";
                    log.info("SQL:"+SQL);
                }
                else {
                    SQL ="select * from REPORT_SHOPS_MUST_PAY  where branch_id='"+shopReq.getBranch_id()+"' AND DateCreatePO BETWEEN '"+shopReq.getStartDate()+"' and '"+shopReq.getEndDate()+"'";
                    log.info("SQL:"+SQL);
                }
            }
            else
            {
                if(shopReq.getStartDate()==null && shopReq.getEndDate()==null){
                    SQL ="select * from REPORT_SHOPS_MUST_PAY  where BRANCH='"+shopReq.getBranch()+"' ";
                    log.info("SQL:"+SQL);
                }
                else {
                    SQL ="select * from REPORT_SHOPS_MUST_PAY  where BRANCH='"+shopReq.getBranch()+"' AND DateCreatePO BETWEEN '"+shopReq.getStartDate()+"' and '"+shopReq.getEndDate()+"'";
                    log.info("SQL:"+SQL);
                }
            }



            return EBankJdbcTemplate.query(SQL, new RowMapper<ReportShops>() {
                @Override
                public ReportShops mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportShops tr = new ReportShops();
                    tr.setPocode(rs.getString("pocode"));
                    tr.setOFFER_CODE(rs.getString("OFFER_CODE"));
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setQty_offer(rs.getString("qty_offer"));
                    tr.setTotal(rs.getString("total"));
                    tr.setPaid(rs.getString("paid"));
                    tr.setTid(rs.getString("tid"));
                    tr.setCUR(rs.getString("CUR"));
                    tr.setShop_name(rs.getString("shop_name"));
                    tr.setDateCreatePO(rs.getString("DateCreatePO"));
                    tr.setTimeToPay(rs.getString("timeToPay"));
                    tr.setStatusNy(rs.getString("StatusNy"));
                    tr.setMoneyRate(rs.getFloat("moneyRate"));
                    tr.setOfferDate(rs.getString("offerDate"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //list show report stock
    public List<ReportStockModel> ListStock(MoveToStockReq moveToStockReq) {
        String SQL;
        String SQL1;
        try{
            if (moveToStockReq.getBranch_id() != null)
            {
                SQL ="select item_name,Qty,img,item_id,unit,size,brand,ber,unit_price,unit_price * Qty as total_price,cur,limitQty from TB_items where branch_id='"+moveToStockReq.getBranch_id()+"' ";
                log.info("SQL:"+SQL);
            }else
            {
                SQL ="select item_name,Qty,img,item_id,unit,size,brand,ber,unit_price,unit_price * Qty as total_price,cur,limitQty from TB_items a inner join LOGIN b on a.userId =b.KEY_ID  where b.BRANCH='"+moveToStockReq.getBranch()+"' ";
                log.info("SQL:"+SQL);
            }


            return EBankJdbcTemplate.query(SQL, new RowMapper<ReportStockModel>() {
                @Override
                public ReportStockModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStockModel tr = new ReportStockModel();
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setQty(rs.getString("Qty"));
                    tr.setImg(rs.getString("img"));
                    tr.setItem_id(rs.getString("item_id"));
                    tr.setUnit(rs.getString("unit"));
                    tr.setSize(rs.getString("size"));
                    tr.setBrand(rs.getString("brand"));
                    tr.setBer(rs.getString("ber"));
                    tr.setTotalValue(rs.getDouble("total_price"));
                    tr.setUnitPirce(rs.getString("unit_price"));
                    tr.setSumUnitWithPrice(rs.getString("total_price"));
                    tr.setCurrency(rs.getString("cur"));
                    tr.setLimitQty(rs.getString("limitQty"));

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // item his Dao
    public List<ItemHis> ItemHisDao(ItemHisReq itemHisReq) {
        String SQL;
        try{
            if (itemHisReq.getBranch_id() != null)
            {
                if (itemHisReq.getStartDate() == null && itemHisReq.getEndDate()==null)
                {
                    SQL ="select * from V_ITEM_HIS  where ITEM_ID ='"+itemHisReq.getItem_id()+"' and branch_id='"+itemHisReq.getBranch_id()+"' ";
                    log.info("SQL:"+SQL);
                }
                else
                {
                    SQL ="select * from V_ITEM_HIS  where ITEM_ID ='"+itemHisReq.getItem_id()+"' and branch_id='"+itemHisReq.getBranch_id()+"' and DATE_IMPORT between '"+itemHisReq.getStartDate()+"' and '"+itemHisReq.getEndDate()+"'";
                    log.info("SQL:"+SQL);
                }
            }else
            {
                if (itemHisReq.getStartDate() == null && itemHisReq.getEndDate()==null)
                {
                    SQL ="select * from V_ITEM_HIS  where ITEM_ID ='"+itemHisReq.getItem_id()+"' and BRANCH='"+itemHisReq.getBranch()+"' ";
                    log.info("SQL:"+SQL);
                }
                else
                {
                    SQL ="select * from V_ITEM_HIS  where ITEM_ID ='"+itemHisReq.getItem_id()+"' and BRANCH='"+itemHisReq.getBranch()+"' and DATE_IMPORT between '"+itemHisReq.getStartDate()+"' and '"+itemHisReq.getEndDate()+"'";
                    log.info("SQL:"+SQL);
                }
            }
            return EBankJdbcTemplate.query(SQL, new RowMapper<ItemHis>() {
                @Override
                public ItemHis mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ItemHis tr = new ItemHis();
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setItem_qty(rs.getDouble("ITEM_QTY"));
                    tr.setImg(rs.getString("img"));
                    tr.setDate_import(rs.getString("DATE_IMPORT"));
                    tr.setItem_id(rs.getString("ITEM_ID"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //list show report stock Detail
    public List<ReportStockModel> ListStockDetail(ReportStockDetailReq reportStockDetailReq) {
        String SQL=null;
        try{
            if (reportStockDetailReq.getBranch_id() != null)
            {
                if (reportStockDetailReq.getStartDate()== null && reportStockDetailReq.getEndDate() == null){
                    SQL ="select * from V_OFFER_PAPER where branch_id='"+reportStockDetailReq.getBranch_id()+"'  and item_id or item_id1 or item_id2 or item_id3 or item_id4 or item_id5 or item_id6 or item_id7 or item_id8 or item_id9 = '"+reportStockDetailReq.getItem_id()+"'";
                    log.info("SQL1:"+SQL);
                } else{
                    SQL ="select * from V_OFFER_PAPER where branch_id='"+reportStockDetailReq.getBranch_id()+"'  and dateCreate between '"+reportStockDetailReq.getStartDate()+"' and '"+reportStockDetailReq.getEndDate()+"'";
                    log.info("SQL2:"+SQL);
                }
            }
            else
            {
                if (reportStockDetailReq.getStartDate()== null && reportStockDetailReq.getEndDate() == null){
                    SQL ="select * from V_OFFER_PAPER where BRANCH='"+reportStockDetailReq.getBranch()+"'  and item_id or item_id1 or item_id2 or item_id3 or item_id4 or item_id5 or item_id6 or item_id7 or item_id8 or item_id9 = '"+reportStockDetailReq.getItem_id()+"'";
                    log.info("SQL1:"+SQL);
                } else{
                    SQL ="select * from V_OFFER_PAPER where BRANCH='"+reportStockDetailReq.getBranch()+"'  and dateCreate between '"+reportStockDetailReq.getStartDate()+"' and '"+reportStockDetailReq.getEndDate()+"'";
                    log.info("SQL2:"+SQL);
                }
            }
            return EBankJdbcTemplate.query(SQL, new RowMapper<ReportStockModel>() {
                @Override
                public ReportStockModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStockModel tr = new ReportStockModel();
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setImg(rs.getString("img"));
                    tr.setItem_id(rs.getString("item_id"));

                    tr.setDateBuy(rs.getString("dateCreate"));
                    tr.setShop_name(rs.getString("shop_name"));
                    tr.setImg1(rs.getString("img1"));
                    tr.setImg2(rs.getString("img2"));
                    tr.setImg3(rs.getString("img3"));
                    tr.setImg4(rs.getString("img4"));
                    tr.setImg5(rs.getString("img5"));
                    tr.setImg6(rs.getString("img6"));
                    tr.setImg7(rs.getString("img7"));
                    tr.setImg8(rs.getString("img8"));
                    tr.setImg9(rs.getString("img9"));
                    tr.setItem_name1(rs.getString("item_name1"));
                    tr.setItem_name2(rs.getString("item_name2"));
                    tr.setItem_name3(rs.getString("item_name3"));
                    tr.setItem_name4(rs.getString("item_name4"));
                    tr.setItem_name5(rs.getString("item_name5"));
                    tr.setItem_name6(rs.getString("item_name6"));
                    tr.setItem_name7(rs.getString("item_name7"));
                    tr.setItem_name8(rs.getString("item_name8"));
                    tr.setItem_name9(rs.getString("item_name9"));
                    tr.setUnit_price(rs.getString("unit_price"));
                    tr.setUnit_price1(rs.getString("unit_price1"));
                    tr.setUnit_price2(rs.getString("unit_price2"));
                    tr.setUnit_price3(rs.getString("unit_price3"));
                    tr.setUnit_price4(rs.getString("unit_price4"));
                    tr.setUnit_price5(rs.getString("unit_price5"));
                    tr.setUnit_price6(rs.getString("unit_price6"));
                    tr.setUnit_price7(rs.getString("unit_price7"));
                    tr.setUnit_price8(rs.getString("unit_price8"));
                    tr.setUnit_price9(rs.getString("unit_price9"));
                    tr.setQty_offer(rs.getString("qty_offer"));
                    tr.setQty_offer1(rs.getString("qty_offer1"));
                    tr.setQty_offer2(rs.getString("qty_offer2"));
                    tr.setQty_offer3(rs.getString("qty_offer3"));
                    tr.setQty_offer4(rs.getString("qty_offer4"));
                    tr.setQty_offer5(rs.getString("qty_offer5"));
                    tr.setQty_offer6(rs.getString("qty_offer6"));
                    tr.setQty_offer7(rs.getString("qty_offer7"));
                    tr.setQty_offer8(rs.getString("qty_offer8"));
                    tr.setQty_offer9(rs.getString("qty_offer9"));
                    tr.setTotalMoney(rs.getString("totalMoney"));
                    tr.setTotalMoney1(rs.getString("totalMoney1"));
                    tr.setTotalMoney2(rs.getString("totalMoney2"));
                    tr.setTotalMoney3(rs.getString("totalMoney3"));
                    tr.setTotalMoney4(rs.getString("totalMoney4"));
                    tr.setTotalMoney5(rs.getString("totalMoney5"));
                    tr.setTotalMoney6(rs.getString("totalMoney6"));
                    tr.setTotalMoney7(rs.getString("totalMoney7"));
                    tr.setTotalMoney8(rs.getString("totalMoney8"));
                    tr.setTotalMoney9(rs.getString("totalMoney9"));

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
            String SQL = "insert into TB_shop (shop_name,address,phone,country,currency,amount_money,userId,branch_id)values (?,?,?,?,?,?,'"+shopReq.getUserId()+"','"+shopReq.getBranch_id()+"')";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(shopReq.getShop_name());
            paramList.add(shopReq.getAddress());
            paramList.add(shopReq.getPhone());
            paramList.add(shopReq.getCountry());
            paramList.add(shopReq.getCurrency());
            paramList.add(shopReq.getAmount_money());
            paramList.add(shopReq.getUserId());
            paramList.add(shopReq.getBranch_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
//    insert fill oil
public int InsertFilloilDaos (FillOilReq fillOilReq) throws ParseException {
    List<FillOilModel> data = new ArrayList<>();
    try{
        String SQL = "insert into FILL_FUEL_HIS (price,lekmai,date_fill,carId)values (?,?,?,?)";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();

        paramList.add(fillOilReq.getPrice());
        paramList.add(fillOilReq.getLekmai());
        paramList.add(fillOilReq.getDateFill());
        paramList.add(fillOilReq.getKey_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
public int DeleteOfferpaperDaos (OfferPaperReq offerPaperReq) {
        String of = offerPaperReq.getOffer_CODE();
        String dl = offerPaperReq.getRealKey_id();
        int i =0;
        try {
            String SQL = "delete from OFFER_PAPER where OFFER_CODE = '" + of +"' and KEY_ID = '"+dl+"'";
            log.info("SQL:"+SQL);
            i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    // save offer paper DAOs
    public int saveOfferPaperDao(OfferPaperReq offerPaperReq) {
        String totalMoney = offerPaperReq.getTotalMoney().replace(",","");
        String unit_price = offerPaperReq.getUnit_price().replace(",","");

        String totalMoney1 = offerPaperReq.getTotalMoney1().replace(",","");
        String unit_price1 = offerPaperReq.getUnit_price1().replace(",","");

        String totalMoney2 = offerPaperReq.getTotalMoney2().replace(",","");
        String unit_price2 = offerPaperReq.getUnit_price2().replace(",","");

        String totalMoney3 = offerPaperReq.getTotalMoney3().replace(",","");
        String unit_price3 = offerPaperReq.getUnit_price3().replace(",","");

        String totalMoney4 = offerPaperReq.getTotalMoney4().replace(",","");
        String unit_price4 = offerPaperReq.getUnit_price4().replace(",","");

        String totalMoney5 = offerPaperReq.getTotalMoney5().replace(",","");
        String unit_price5 = offerPaperReq.getUnit_price5().replace(",","");

        String totalMoney6 = offerPaperReq.getTotalMoney6().replace(",","");
        String unit_price6 = offerPaperReq.getUnit_price6().replace(",","");

        String totalMoney7 = offerPaperReq.getTotalMoney7().replace(",","");
        String unit_price7 = offerPaperReq.getUnit_price7().replace(",","");

        String totalMoney8 = offerPaperReq.getTotalMoney8().replace(",","");
        String unit_price8 = offerPaperReq.getUnit_price8().replace(",","");

        String totalMoney9 = offerPaperReq.getTotalMoney9().replace(",","");
        String unit_price9 = offerPaperReq.getUnit_price9().replace(",","");
        String sql=null;
//  =============================================================================================================new create
        String currency = offerPaperReq.getCurrency();
        List<String> itemIds = Arrays.asList(offerPaperReq.getItem_id(), offerPaperReq.getItem_id1(), offerPaperReq.getItem_id2(),
                offerPaperReq.getItem_id3(), offerPaperReq.getItem_id4(), offerPaperReq.getItem_id5(),
                offerPaperReq.getItem_id6(), offerPaperReq.getItem_id7(), offerPaperReq.getItem_id8(),
                offerPaperReq.getItem_id9());
        List<String> unitPriceList = Arrays.asList(unit_price, unit_price1, unit_price2,unit_price3, unit_price4, unit_price5,unit_price6,
                unit_price7, unit_price8,
                unit_price9);
//  =============================================================================================================new create
        Double a = Double.parseDouble(totalMoney);
        Double b = Double.parseDouble(totalMoney1);
        Double c = Double.parseDouble(totalMoney2);
        Double d = Double.parseDouble(totalMoney3);
        Double e4 = Double.parseDouble(totalMoney4);
        Double f = Double.parseDouble(totalMoney5);
        Double g = Double.parseDouble(totalMoney6);
        Double h = Double.parseDouble(totalMoney7);
        Double i = Double.parseDouble(totalMoney8);
        Double j = Double.parseDouble(totalMoney9);
        Double all = (a+b+c+d+e4+f+g+h+i+j);
        List<Object> paramList = new ArrayList<Object>();
        try {
            if (offerPaperReq.getShop_id() != null && (offerPaperReq.getShop_id()==8 || offerPaperReq.getShop_id()==26 || offerPaperReq.getShop_id()==27 || offerPaperReq.getShop_id()==57 || offerPaperReq.getShop_id()==142)){
                sql ="insert into OFFER_PAPER (item_id,header_id,footer_id,shop_id,unit_price,qty_offer,totalMoney,description,offerManName,job,userId,OFFER_CODE,dateCreate,status,stock_status,statusPO,item_id1,unit_price1,qty_offer1,totalMoney1,item_id2,unit_price2,qty_offer2,totalMoney2,item_id3,unit_price3,qty_offer3,totalMoney3,item_id4,unit_price4,qty_offer4,totalMoney4,item_id5,unit_price5,qty_offer5,totalMoney5,item_id6,unit_price6,qty_offer6,totalMoney6,item_id7,unit_price7,qty_offer7,totalMoney7,item_id8,unit_price8,qty_offer8,totalMoney8,item_id9,unit_price9,qty_offer9,totalMoney9,Real_totalMoney,item_name1,item_name2,item_name3,item_name4,item_name5,item_name6,item_name7,item_name8,item_name9,img1,img2,img3,img4,img5,img6,img7,img8,img9,currency,moneyRate,STATUS_CREDITS,branch_id)" +
                        "values (?,?,?,?,?,?,?,?,?,?,?,?,'"+offerPaperReq.getDateCreate()+"','N','wait','NO','"+offerPaperReq.getItem_id1()+"', '"+unit_price1+"','"+offerPaperReq.getQty_offer1()+"','"+totalMoney1+"','"+offerPaperReq.getItem_id2()+"', '"+unit_price2+"','"+offerPaperReq.getQty_offer2()+"','"+totalMoney2+"', '"+offerPaperReq.getItem_id3()+"', '"+unit_price3+"','"+offerPaperReq.getQty_offer3()+"','"+totalMoney3+"', '"+offerPaperReq.getItem_id4()+"', '"+unit_price4+"','"+offerPaperReq.getQty_offer4()+"','"+totalMoney4+"', '"+offerPaperReq.getItem_id5()+"', '"+unit_price5+"','"+offerPaperReq.getQty_offer5()+"','"+totalMoney5+"', '"+offerPaperReq.getItem_id6()+"', '"+unit_price6+"','"+offerPaperReq.getQty_offer6()+"','"+totalMoney6+"', '"+offerPaperReq.getItem_id7()+"', '"+unit_price7+"','"+offerPaperReq.getQty_offer7()+"','"+totalMoney7+"', '"+offerPaperReq.getItem_id8()+"', '"+unit_price8+"','"+offerPaperReq.getQty_offer8()+"','"+totalMoney8+"', '"+offerPaperReq.getItem_id9()+"', '"+unit_price9+"','"+offerPaperReq.getQty_offer9()+"','"+totalMoney9+"','"+all+"','"+offerPaperReq.getItem_name1()+"','"+offerPaperReq.getItem_name2()+"','"+offerPaperReq.getItem_name3()+"','"+offerPaperReq.getItem_name4()+"','"+offerPaperReq.getItem_name5()+"','"+offerPaperReq.getItem_name6()+"','"+offerPaperReq.getItem_name7()+"','"+offerPaperReq.getItem_name8()+"','"+offerPaperReq.getItem_name9()+"','"+offerPaperReq.getImg1()+"','"+offerPaperReq.getImg2()+"','"+offerPaperReq.getImg3()+"','"+offerPaperReq.getImg4()+"','"+offerPaperReq.getImg5()+"','"+offerPaperReq.getImg6()+"','"+offerPaperReq.getImg7()+"','"+offerPaperReq.getImg8()+"','"+offerPaperReq.getImg9()+"','"+offerPaperReq.getCurrency()+"','"+offerPaperReq.getMoneyRate()+"','NO','"+offerPaperReq.getBranch_id()+"') ";
                log.info("SQL1:"+sql);
                paramList.add(offerPaperReq.getItem_id());
                paramList.add(offerPaperReq.getHeader_id());
                paramList.add(offerPaperReq.getFooter_id());
                paramList.add(offerPaperReq.getShop_id());
                paramList.add(unit_price);
                paramList.add(offerPaperReq.getQty_offer());
                paramList.add(totalMoney);
                paramList.add(offerPaperReq.getDescription());
                paramList.add(offerPaperReq.getOfferManName());
                paramList.add(offerPaperReq.getJob());
                paramList.add(offerPaperReq.getUserId());
                paramList.add(offerPaperReq.getOffer_CODE());
                paramList.add(offerPaperReq.getDateCreate());
                paramList.add(offerPaperReq.getStatus());
                paramList.add(offerPaperReq.getStock_status());
                paramList.add(offerPaperReq.getStatusPO());

                paramList.add(offerPaperReq.getItem_id1());
                paramList.add(unit_price1);
                paramList.add(offerPaperReq.getQty_offer1());
                paramList.add(totalMoney1);

                paramList.add(offerPaperReq.getItem_id2());
                paramList.add(unit_price2);
                paramList.add(offerPaperReq.getQty_offer2());
                paramList.add(totalMoney2);

                paramList.add(offerPaperReq.getItem_id3());
                paramList.add(unit_price3);
                paramList.add(offerPaperReq.getQty_offer3());
                paramList.add(totalMoney3);

                paramList.add(offerPaperReq.getItem_id4());
                paramList.add(unit_price4);
                paramList.add(offerPaperReq.getQty_offer4());
                paramList.add(totalMoney4);

                paramList.add(offerPaperReq.getItem_id5());
                paramList.add(unit_price5);
                paramList.add(offerPaperReq.getQty_offer5());
                paramList.add(totalMoney5);

                paramList.add(offerPaperReq.getItem_id6());
                paramList.add(unit_price6);
                paramList.add(offerPaperReq.getQty_offer6());
                paramList.add(totalMoney6);

                paramList.add(offerPaperReq.getItem_id7());
                paramList.add(unit_price7);
                paramList.add(offerPaperReq.getQty_offer7());
                paramList.add(totalMoney7);

                paramList.add(offerPaperReq.getItem_id8());
                paramList.add(unit_price8);
                paramList.add(offerPaperReq.getQty_offer8());
                paramList.add(totalMoney8);

                paramList.add(offerPaperReq.getItem_id9());
                paramList.add(unit_price9);
                paramList.add(offerPaperReq.getQty_offer9());
                paramList.add(totalMoney9);

//                paramList.add(all);

                paramList.add(offerPaperReq.getItem_name1());
                paramList.add(offerPaperReq.getItem_name2());
                paramList.add(offerPaperReq.getItem_name3());
                paramList.add(offerPaperReq.getItem_name4());
                paramList.add(offerPaperReq.getItem_name5());
                paramList.add(offerPaperReq.getItem_name6());
                paramList.add(offerPaperReq.getItem_name7());
                paramList.add(offerPaperReq.getItem_name8());
                paramList.add(offerPaperReq.getItem_name9());
                paramList.add(offerPaperReq.getImg1());
                paramList.add(offerPaperReq.getImg2());
                paramList.add(offerPaperReq.getImg3());
                paramList.add(offerPaperReq.getImg4());
                paramList.add(offerPaperReq.getImg5());
                paramList.add(offerPaperReq.getImg6());
                paramList.add(offerPaperReq.getImg7());
                paramList.add(offerPaperReq.getImg8());
                paramList.add(offerPaperReq.getImg9());
                paramList.add(offerPaperReq.getImg9());
                paramList.add(offerPaperReq.getCurrency());
                paramList.add(offerPaperReq.getMoneyRate());
                paramList.add(offerPaperReq.getBranch_id());

                EBankJdbcTemplate.update(sql, paramList.toArray());

//                for (String itemId : itemIds) {
//                    String udUnitPrice = "update TB_items set unit_price = ? where item_id = ?";
//                    log.info("SQL_update_unit_price for item {}: {}", itemId, udUnitPrice);
//                    EBankJdbcTemplate.update(udUnitPrice, new Object[]{unitPriceList, itemId});
//                }
                if (itemIds.size() == unitPriceList.size()) {
                    for (int o = 0; o < itemIds.size(); o++) {
                        String itemId = itemIds.get(o);
                        String unitPrice = unitPriceList.get(o);
                        String udUnitPrice = "update TB_items set unit_price = ? where item_id = ?";
                        log.info("SQL_update_unit_price for item {}: {}", itemId, udUnitPrice);
                        EBankJdbcTemplate.update(udUnitPrice, new Object[]{unitPrice, itemId});
                    }
                } else {
                    log.error("Error: The number of item IDs does not match the number of unit prices.");
                    // Handle this error appropriately, maybe throw an exception
                }

                for (String itemId : itemIds) {
                    String updateCUR = "update TB_items set cur = ? where item_id = ?";
                    log.info("SQL_update_currency for item {}: {}", itemId, updateCUR);
                    EBankJdbcTemplate.update(updateCUR, new Object[]{currency, itemId});
                }
            }
            else if(offerPaperReq.getShop_id() != null && (offerPaperReq.getShop_id()!=8 && offerPaperReq.getShop_id()!=26 && offerPaperReq.getShop_id()!=27 && offerPaperReq.getShop_id()!=57)){
                sql ="insert into OFFER_PAPER (item_id,header_id,footer_id,shop_id,unit_price,qty_offer,totalMoney,description,offerManName,job,userId,OFFER_CODE,dateCreate,status,stock_status,statusPO,item_id1,unit_price1,qty_offer1,totalMoney1,item_id2,unit_price2,qty_offer2,totalMoney2,item_id3,unit_price3,qty_offer3,totalMoney3,item_id4,unit_price4,qty_offer4,totalMoney4,item_id5,unit_price5,qty_offer5,totalMoney5,item_id6,unit_price6,qty_offer6,totalMoney6,item_id7,unit_price7,qty_offer7,totalMoney7,item_id8,unit_price8,qty_offer8,totalMoney8,item_id9,unit_price9,qty_offer9,totalMoney9,Real_totalMoney,item_name1,item_name2,item_name3,item_name4,item_name5,item_name6,item_name7,item_name8,item_name9,img1,img2,img3,img4,img5,img6,img7,img8,img9,currency,moneyRate,STATUS_CREDITS,branch_id)" +
                        "values (?,?,?,?,?,?,?,?,?,?,?,?,'"+offerPaperReq.getDateCreate()+"','Y','wait','NO','"+offerPaperReq.getItem_id1()+"', '"+unit_price1+"','"+offerPaperReq.getQty_offer1()+"','"+totalMoney1+"','"+offerPaperReq.getItem_id2()+"', '"+unit_price2+"','"+offerPaperReq.getQty_offer2()+"','"+totalMoney2+"', '"+offerPaperReq.getItem_id3()+"', '"+unit_price3+"','"+offerPaperReq.getQty_offer3()+"','"+totalMoney3+"', '"+offerPaperReq.getItem_id4()+"', '"+unit_price4+"','"+offerPaperReq.getQty_offer4()+"','"+totalMoney4+"', '"+offerPaperReq.getItem_id5()+"', '"+unit_price5+"','"+offerPaperReq.getQty_offer5()+"','"+totalMoney5+"', '"+offerPaperReq.getItem_id6()+"', '"+unit_price6+"','"+offerPaperReq.getQty_offer6()+"','"+totalMoney6+"', '"+offerPaperReq.getItem_id7()+"', '"+unit_price7+"','"+offerPaperReq.getQty_offer7()+"','"+totalMoney7+"', '"+offerPaperReq.getItem_id8()+"', '"+unit_price8+"','"+offerPaperReq.getQty_offer8()+"','"+totalMoney8+"', '"+offerPaperReq.getItem_id9()+"', '"+unit_price9+"','"+offerPaperReq.getQty_offer9()+"','"+totalMoney9+"','"+all+"','"+offerPaperReq.getItem_name1()+"','"+offerPaperReq.getItem_name2()+"','"+offerPaperReq.getItem_name3()+"','"+offerPaperReq.getItem_name4()+"','"+offerPaperReq.getItem_name5()+"','"+offerPaperReq.getItem_name6()+"','"+offerPaperReq.getItem_name7()+"','"+offerPaperReq.getItem_name8()+"','"+offerPaperReq.getItem_name9()+"','"+offerPaperReq.getImg1()+"','"+offerPaperReq.getImg2()+"','"+offerPaperReq.getImg3()+"','"+offerPaperReq.getImg4()+"','"+offerPaperReq.getImg5()+"','"+offerPaperReq.getImg6()+"','"+offerPaperReq.getImg7()+"','"+offerPaperReq.getImg8()+"','"+offerPaperReq.getImg9()+"','"+offerPaperReq.getCurrency()+"','"+offerPaperReq.getMoneyRate()+"','YES','"+offerPaperReq.getBranch_id()+"')";
                log.info("SQL2:"+sql);
                paramList.add(offerPaperReq.getItem_id());
                paramList.add(offerPaperReq.getHeader_id());
                paramList.add(offerPaperReq.getFooter_id());
                paramList.add(offerPaperReq.getShop_id());
                paramList.add(unit_price);
                paramList.add(offerPaperReq.getQty_offer());
                paramList.add(totalMoney);
                paramList.add(offerPaperReq.getDescription());
                paramList.add(offerPaperReq.getOfferManName());
                paramList.add(offerPaperReq.getJob());
                paramList.add(offerPaperReq.getUserId());
                paramList.add(offerPaperReq.getOffer_CODE());
                paramList.add(offerPaperReq.getDateCreate());
                paramList.add(offerPaperReq.getStatus());
                paramList.add(offerPaperReq.getStock_status());
                paramList.add(offerPaperReq.getStatusPO());

                paramList.add(offerPaperReq.getItem_id1());
                paramList.add(unit_price1);
                paramList.add(offerPaperReq.getQty_offer1());
                paramList.add(totalMoney1);

                paramList.add(offerPaperReq.getItem_id2());
                paramList.add(unit_price2);
                paramList.add(offerPaperReq.getQty_offer2());
                paramList.add(totalMoney2);

                paramList.add(offerPaperReq.getItem_id3());
                paramList.add(unit_price3);
                paramList.add(offerPaperReq.getQty_offer3());
                paramList.add(totalMoney3);

                paramList.add(offerPaperReq.getItem_id4());
                paramList.add(unit_price4);
                paramList.add(offerPaperReq.getQty_offer4());
                paramList.add(totalMoney4);

                paramList.add(offerPaperReq.getItem_id5());
                paramList.add(unit_price5);
                paramList.add(offerPaperReq.getQty_offer5());
                paramList.add(totalMoney5);

                paramList.add(offerPaperReq.getItem_id6());
                paramList.add(unit_price6);
                paramList.add(offerPaperReq.getQty_offer6());
                paramList.add(totalMoney6);

                paramList.add(offerPaperReq.getItem_id7());
                paramList.add(unit_price7);
                paramList.add(offerPaperReq.getQty_offer7());
                paramList.add(totalMoney7);

                paramList.add(offerPaperReq.getItem_id8());
                paramList.add(unit_price8);
                paramList.add(offerPaperReq.getQty_offer8());
                paramList.add(totalMoney8);

                paramList.add(offerPaperReq.getItem_id9());
                paramList.add(unit_price9);
                paramList.add(offerPaperReq.getQty_offer9());
                paramList.add(totalMoney9);

//                paramList.add(all);
                paramList.add(offerPaperReq.getItem_name1());
                paramList.add(offerPaperReq.getItem_name2());
                paramList.add(offerPaperReq.getItem_name3());
                paramList.add(offerPaperReq.getItem_name4());
                paramList.add(offerPaperReq.getItem_name5());
                paramList.add(offerPaperReq.getItem_name6());
                paramList.add(offerPaperReq.getItem_name7());
                paramList.add(offerPaperReq.getItem_name8());
                paramList.add(offerPaperReq.getItem_name9());
                paramList.add(offerPaperReq.getImg1());
                paramList.add(offerPaperReq.getImg2());
                paramList.add(offerPaperReq.getImg3());
                paramList.add(offerPaperReq.getImg4());
                paramList.add(offerPaperReq.getImg5());
                paramList.add(offerPaperReq.getImg6());
                paramList.add(offerPaperReq.getImg7());
                paramList.add(offerPaperReq.getImg8());
                paramList.add(offerPaperReq.getImg9());
                paramList.add(offerPaperReq.getCurrency());
                paramList.add(offerPaperReq.getMoneyRate());
                paramList.add(offerPaperReq.getBranch_id());

                EBankJdbcTemplate.update(sql, paramList.toArray());

//                for (String itemId : itemIds) {
//                    String udUnitPrice = "update TB_items set unit_price = ? where item_id = ?";
//                    log.info("SQL_update_unit_price for item {}: {}", itemId, udUnitPrice);
//                    EBankJdbcTemplate.update(udUnitPrice, new Object[]{unitPriceList, itemId});
//                }
                if (itemIds.size() == unitPriceList.size()) {
                    for (int o = 0; o < itemIds.size(); o++) {
                        String itemId = itemIds.get(o);
                        String unitPrice = unitPriceList.get(o);
                        String udUnitPrice = "update TB_items set unit_price = ? where item_id = ?";
                        log.info("SQL_update_unit_price for item {}: {}", itemId, udUnitPrice);
                        EBankJdbcTemplate.update(udUnitPrice, new Object[]{unitPrice, itemId});
                    }
                } else {
                    log.error("Error: The number of item IDs does not match the number of unit prices.");
                    // Handle this error appropriately, maybe throw an exception
                }

                for (String itemId : itemIds) {
                    String updateCUR = "update TB_items set cur = ? where item_id = ?";
                    log.info("SQL_update_currency for item {}: {}", itemId, updateCUR);
                    EBankJdbcTemplate.update(updateCUR, new Object[]{currency, itemId});
                }

            }
//            return EBankJdbcTemplate.update(sql, paramList.toArray());
        }catch (Exception e ){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    // fix DAOs OLD
//    public int FixDao (FixReq fixReq) {
//        String totalPrice1 = fixReq.getTotal_Price().replace(",","");
//        try {
//            List<Object> paramList = new ArrayList<Object>();
//            String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail) values (?,?,?,?,?,?,now(),'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"')";
//                log.info("SQL:"+sql1);
//                paramList.add(fixReq.getHeader_id());
//                paramList.add(fixReq.getFooter_id());
//                paramList.add(fixReq.getDescription());
//                paramList.add(fixReq.getItem_id());
//                paramList.add(fixReq.getQty_Fix());
//                paramList.add(totalPrice1);
//                paramList.add(fixReq.getDateFix());
//                paramList.add(fixReq.getUserId());
//                paramList.add(fixReq.getAdd_on());
//                paramList.add(fixReq.getLocation_fix());
//                paramList.add(fixReq.getFix_Detail());
//            EBankJdbcTemplate.update(sql1, paramList.toArray());
//
//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"' where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return -1;
//        }
//        return 0;
//    }
    // fIX DAO NEW Trans to another stock
    public int FixDao (FixReq fixReq) {
        String totalPrice1 = fixReq.getTotal_Price().replace(",","");
        try {
            if (fixReq.getBranch_inventory() == null){
                List<Object> paramList = new ArrayList<Object>();
                String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory,branch_id) values (?,?,?,?,?,?,?,'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"',0,'"+fixReq.getBranch_id()+"')";
                log.info("SQL:"+sql1);
                paramList.add(fixReq.getHeader_id());
                paramList.add(fixReq.getFooter_id());
                paramList.add(fixReq.getDescription());
                paramList.add(fixReq.getItem_id());
                paramList.add(fixReq.getQty_Fix());
                paramList.add(totalPrice1);
                paramList.add(fixReq.getDateFix());
                paramList.add(fixReq.getUserId());
                paramList.add(fixReq.getAdd_on());
                paramList.add(fixReq.getLocation_fix());
                paramList.add(fixReq.getFix_Detail());
//                paramList.add(fixReq.getFix_Detail());
                paramList.add(fixReq.getBranch_inventory());
                paramList.add(fixReq.getBranch_id());
                EBankJdbcTemplate.update(sql1, paramList.toArray());

//                String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"' where item_id = '"+fixReq.getItem_id()+"'";
//                paramList.add(fixReq.getQty_Fix());
//                log.info("SQL:"+sql2);
//                EBankJdbcTemplate.update(sql2, paramList.toArray());
            }
            else if (fixReq.getBranch_inventory() != null)
            {
                    List<Object> paramList = new ArrayList<Object>();
                    String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory,branch_id) values (?,?,?,?,?,?,now(),'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"','"+fixReq.getBranch_inventory()+"','"+fixReq.getBranch_id()+"')";
                    log.info("SQL1:"+sql1);
                    paramList.add(fixReq.getHeader_id());
                    paramList.add(fixReq.getFooter_id());
                    paramList.add(fixReq.getDescription());
                    paramList.add(fixReq.getItem_id());
                    paramList.add(fixReq.getQty_Fix());
                    paramList.add(totalPrice1);
                    paramList.add(fixReq.getDateFix());
                    paramList.add(fixReq.getUserId());
                    paramList.add(fixReq.getAdd_on());
                    paramList.add(fixReq.getLocation_fix());
                    paramList.add(fixReq.getFix_Detail());
                    paramList.add(fixReq.getBranch_inventory());
                    paramList.add(fixReq.getBranch_id());
                    EBankJdbcTemplate.update(sql1, paramList.toArray());
//ตัดสะต๋อกออก
//                    String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"'  where item_id = '"+fixReq.getItem_id()+"'";
//                    paramList.add(fixReq.getQty_Fix());
//                    log.info("SQL2:"+sql2);
//                    EBankJdbcTemplate.update(sql2, paramList.toArray());
//เพี่มสะต๋อก
                    String sql3 = "update TB_items set Qty = Qty + '"+fixReq.getQty_Fix()+"'  where item_name = '"+fixReq.getItem_name()+"' and item_id != '"+fixReq.getItem_id()+"' and '"+fixReq.getBranch_inventory()+"'";
                    paramList.add(fixReq.getQty_Fix());
                    log.info("SQL3:"+sql3);
                    EBankJdbcTemplate.update(sql3, paramList.toArray());
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
//    approve b4 fix
public int ApproveFixDao (FixReq fixReq) {
    String totalPrice1 = fixReq.getTotal_Price().replace(",","");
    try {
            List<Object> paramList = new ArrayList<Object>();
            String sql1 ="insert into APPROVE_FIX (header_id,item_name,branch_inventory,item_id,footer_id,qty_Fix,total_Price,description,location_fix,fix_Detail,dateFix,approve_status,userId,branch_id) values (?,?,?,?,?,?,?,?,?,?,?,'YES',?,?)";
            log.info("SQL1:"+sql1);
            paramList.add(fixReq.getHeader_id());
            paramList.add(fixReq.getItem_name());
            paramList.add(fixReq.getBranch_inventory());
            paramList.add(fixReq.getItem_id());
            paramList.add(fixReq.getFooter_id());
            paramList.add(fixReq.getQty_Fix());
            paramList.add(totalPrice1);
            paramList.add(fixReq.getDescription());
            paramList.add(fixReq.getLocation_fix());
            paramList.add(fixReq.getFix_Detail());
            paramList.add(fixReq.getDateFix());
            paramList.add(fixReq.getUserId());
            paramList.add(fixReq.getBranch_id());
            EBankJdbcTemplate.update(sql1, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
    return 0;
}
//proff fix req
public int proofFixReqDao (FixReq fixReq) {
    try {
        List<Object> paramList = new ArrayList<Object>();
        String sql1,sql2,sql_update;

        if (fixReq.getStatus().equals("YES")){
            sql1   ="update APPROVE_FIX set approve_status='"+fixReq.getStatus()+"',new_status='GO' where KEY_ID='"+fixReq.getKey_id()+"'  ";
            log.info("SQL1:"+sql1);
            paramList.add(fixReq.getStatus());
            paramList.add(fixReq.getNew_status());
            paramList.add(fixReq.getKey_id());
            EBankJdbcTemplate.update(sql1, paramList.toArray());

            sql_update ="UPDATE TB_items SET Qty = Qty - (SELECT qty_Fix FROM APPROVE_FIX WHERE KEY_ID = '"+fixReq.getKey_id()+"') WHERE item_id = (SELECT item_id FROM APPROVE_FIX WHERE KEY_ID = '"+fixReq.getKey_id()+"')";
            log.info("sql_update:"+sql_update);
            paramList.add(fixReq.getKey_id());
            EBankJdbcTemplate.update(sql_update, paramList.toArray());
        }else {
            sql2   ="update APPROVE_FIX set approve_status='"+fixReq.getStatus()+"' where KEY_ID='"+fixReq.getKey_id()+"'";
            log.info("SQL1:"+sql2);
            paramList.add(fixReq.getStatus());
            paramList.add(fixReq.getKey_id());
            EBankJdbcTemplate.update(sql2, paramList.toArray());
        }
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
    return 0;
}
//insert old inventory dao
public int InsertOldinventoryDAOs (OldInventoryReq oldInventoryReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = oldInventoryReq.getImage_Oldwarehouse();
    log.info("path:"+path+fileName);
    List<OldInventoryModel> data = new ArrayList<>();
    try{
        String SQL = "insert into OLD_INVENTORY (item_name,header_truck_id,footer_truck_id,amount,picture,detail,date_in,type,price,branch_id,branchNo,cur) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(oldInventoryReq.getItemName_Oldwarehouse());
        paramList.add(oldInventoryReq.getVehicle_Oldwarehouse());
        paramList.add(oldInventoryReq.getVehiclefooter_Oldwarehouse());
        paramList.add(oldInventoryReq.getQty_Oldwarehouse());
        paramList.add(path + fileName);
        paramList.add(oldInventoryReq.getDescription_Oldwarehouse());
        paramList.add(oldInventoryReq.getImportExpirationDate_Oldwarehouse());
        paramList.add(oldInventoryReq.getSelectedType_Oldwarehouse());
        paramList.add(oldInventoryReq.getPrice_Oldwarehouse());
        paramList.add(oldInventoryReq.getBranch_id());
        paramList.add(oldInventoryReq.getBranch());
        paramList.add(oldInventoryReq.getCur());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//delete old inventory
public int DeleteinventoryDAOs (OldInventoryReq oldInventoryReq) throws ParseException {
    List<OldInventoryModel> data = new ArrayList<>();
    try{
        String SQL = "delete from OLD_INVENTORY where KEY_ID=?";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(oldInventoryReq.getKey_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//update old inventory
public int UpdateOldinventoryDAOs (OldInventoryReq oldInventoryReq) throws ParseException {
    String path="http://khounkham.com/images/car/";
    String fileName = oldInventoryReq.getImage_Oldwarehouse();
    log.info("path:"+path+fileName);
    List<DataHoleModel> data = new ArrayList<>();
    try{
        String SQL = "update OLD_INVENTORY set item_name=?,header_truck_id=?,footer_truck_id=?,amount=?,picture=?,detail=?,date_in=?,type=?,price=?,branch_id=?,branchNo=?,cur =? where KEY_ID='"+oldInventoryReq.getKey_id()+"'";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(oldInventoryReq.getItemName_Oldwarehouse());
        paramList.add(oldInventoryReq.getVehicle_Oldwarehouse());
        paramList.add(oldInventoryReq.getVehiclefooter_Oldwarehouse());
        paramList.add(oldInventoryReq.getQty_Oldwarehouse());
        paramList.add(path + fileName);
        paramList.add(oldInventoryReq.getDescription_Oldwarehouse());
        paramList.add(oldInventoryReq.getImportExpirationDate_Oldwarehouse());
        paramList.add(oldInventoryReq.getSelectedType_Oldwarehouse());
        paramList.add(oldInventoryReq.getPrice_Oldwarehouse());
        paramList.add(oldInventoryReq.getBranch_id());
        paramList.add(oldInventoryReq.getBranch());
        paramList.add(oldInventoryReq.getCur());
        paramList.add(oldInventoryReq.getKey_id());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
//    for head and tail truck is null
public int FixDaoIftruckNull (FixReq fixReq) {
    String totalPrice1 = fixReq.getTotal_Price().replace(",","");
    try {
        if (fixReq.getBranch_inventory() == null){
            List<Object> paramList = new ArrayList<Object>();
            String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('299',?,?,?,?,?,?,'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"',0)";
            log.info("SQL:"+sql1);
            paramList.add(fixReq.getHeader_id());
            paramList.add(fixReq.getFooter_id());
            paramList.add(fixReq.getDescription());
            paramList.add(fixReq.getItem_id());
            paramList.add(fixReq.getQty_Fix());
            paramList.add(totalPrice1);
            paramList.add(fixReq.getDateFix());
            paramList.add(fixReq.getUserId());
            paramList.add(fixReq.getAdd_on());
            paramList.add(fixReq.getLocation_fix());
            paramList.add(fixReq.getFix_Detail());
            paramList.add(fixReq.getBranch_inventory());
            EBankJdbcTemplate.update(sql1, paramList.toArray());

//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"' where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
        }
        else if (fixReq.getBranch_inventory() != null)
        {
            List<Object> paramList = new ArrayList<Object>();
            String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('299',?,?,?,?,?,now(),'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"','"+fixReq.getBranch_inventory()+"')";
            log.info("SQL1:"+sql1);
            paramList.add(fixReq.getHeader_id());
            paramList.add(fixReq.getFooter_id());
            paramList.add(fixReq.getDescription());
            paramList.add(fixReq.getItem_id());
            paramList.add(fixReq.getQty_Fix());
            paramList.add(totalPrice1);
            paramList.add(fixReq.getDateFix());
            paramList.add(fixReq.getUserId());
            paramList.add(fixReq.getAdd_on());
            paramList.add(fixReq.getLocation_fix());
            paramList.add(fixReq.getFix_Detail());
            paramList.add(fixReq.getBranch_inventory());
            EBankJdbcTemplate.update(sql1, paramList.toArray());

//ตัดสะต๋อกออก
//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"'  where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL2:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
//เพี่มสะต๋อก
            String sql3 = "update TB_items set Qty = Qty + '"+fixReq.getQty_Fix()+"'  where item_name = '"+fixReq.getItem_name()+"' and item_id != '"+fixReq.getItem_id()+"' and '"+fixReq.getBranch_inventory()+"'";
            paramList.add(fixReq.getQty_Fix());
            log.info("SQL3:"+sql3);
            EBankJdbcTemplate.update(sql3, paramList.toArray());

        }
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
    return 0;
}
//if truck null savan oil
    public int FixDaoIftruckNullSavannakhetOil (FixReq fixReq) {
        String totalPrice1 = fixReq.getTotal_Price().replace(",","");
        try {
            if (fixReq.getBranch_inventory() == null){
                List<Object> paramList = new ArrayList<Object>();
                String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('393',?,?,?,?,?,?,'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"',0)";
                log.info("SQL:"+sql1);
                paramList.add(fixReq.getHeader_id());
                paramList.add(fixReq.getFooter_id());
                paramList.add(fixReq.getDescription());
                paramList.add(fixReq.getItem_id());
                paramList.add(fixReq.getQty_Fix());
                paramList.add(totalPrice1);
                paramList.add(fixReq.getDateFix());
                paramList.add(fixReq.getUserId());
                paramList.add(fixReq.getAdd_on());
                paramList.add(fixReq.getLocation_fix());
                paramList.add(fixReq.getFix_Detail());
                paramList.add(fixReq.getBranch_inventory());
                EBankJdbcTemplate.update(sql1, paramList.toArray());

//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"' where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
            }
            else if (fixReq.getBranch_inventory() != null)
            {
                List<Object> paramList = new ArrayList<Object>();
                String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('393',?,?,?,?,?,now(),'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"','"+fixReq.getBranch_inventory()+"')";
                log.info("SQL1:"+sql1);
                paramList.add(fixReq.getHeader_id());
                paramList.add(fixReq.getFooter_id());
                paramList.add(fixReq.getDescription());
                paramList.add(fixReq.getItem_id());
                paramList.add(fixReq.getQty_Fix());
                paramList.add(totalPrice1);
                paramList.add(fixReq.getDateFix());
                paramList.add(fixReq.getUserId());
                paramList.add(fixReq.getAdd_on());
                paramList.add(fixReq.getLocation_fix());
                paramList.add(fixReq.getFix_Detail());
                paramList.add(fixReq.getBranch_inventory());
                EBankJdbcTemplate.update(sql1, paramList.toArray());

//ตัดสะต๋อกออก
//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"'  where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL2:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
//เพี่มสะต๋อก
                String sql3 = "update TB_items set Qty = Qty + '"+fixReq.getQty_Fix()+"'  where item_name = '"+fixReq.getItem_name()+"' and item_id != '"+fixReq.getItem_id()+"' and '"+fixReq.getBranch_inventory()+"'";
                paramList.add(fixReq.getQty_Fix());
                log.info("SQL3:"+sql3);
                EBankJdbcTemplate.update(sql3, paramList.toArray());

            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    //if truck null savan tools
    public int FixDaoIftruckNullSavannakhetTools (FixReq fixReq) {
        String totalPrice1 = fixReq.getTotal_Price().replace(",","");
        try {
            if (fixReq.getBranch_inventory() == null){
                List<Object> paramList = new ArrayList<Object>();
                String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('394',?,?,?,?,?,?,'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"',0)";
                log.info("SQL:"+sql1);
                paramList.add(fixReq.getHeader_id());
                paramList.add(fixReq.getFooter_id());
                paramList.add(fixReq.getDescription());
                paramList.add(fixReq.getItem_id());
                paramList.add(fixReq.getQty_Fix());
                paramList.add(totalPrice1);
                paramList.add(fixReq.getDateFix());
                paramList.add(fixReq.getUserId());
                paramList.add(fixReq.getAdd_on());
                paramList.add(fixReq.getLocation_fix());
                paramList.add(fixReq.getFix_Detail());
                paramList.add(fixReq.getBranch_inventory());
                EBankJdbcTemplate.update(sql1, paramList.toArray());

//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"' where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
            }
            else if (fixReq.getBranch_inventory() != null)
            {
                List<Object> paramList = new ArrayList<Object>();
                String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('394',?,?,?,?,?,now(),'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"','"+fixReq.getBranch_inventory()+"')";
                log.info("SQL1:"+sql1);
                paramList.add(fixReq.getHeader_id());
                paramList.add(fixReq.getFooter_id());
                paramList.add(fixReq.getDescription());
                paramList.add(fixReq.getItem_id());
                paramList.add(fixReq.getQty_Fix());
                paramList.add(totalPrice1);
                paramList.add(fixReq.getDateFix());
                paramList.add(fixReq.getUserId());
                paramList.add(fixReq.getAdd_on());
                paramList.add(fixReq.getLocation_fix());
                paramList.add(fixReq.getFix_Detail());
                paramList.add(fixReq.getBranch_inventory());
                EBankJdbcTemplate.update(sql1, paramList.toArray());

//ตัดสะต๋อกออก
//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"'  where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL2:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
//เพี่มสะต๋อก
                String sql3 = "update TB_items set Qty = Qty + '"+fixReq.getQty_Fix()+"'  where item_name = '"+fixReq.getItem_name()+"' and item_id != '"+fixReq.getItem_id()+"' and '"+fixReq.getBranch_inventory()+"'";
                paramList.add(fixReq.getQty_Fix());
                log.info("SQL3:"+sql3);
                EBankJdbcTemplate.update(sql3, paramList.toArray());

            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
//if truck head null and tail null xiengkhuang
public int FixDaoIftruckNullXiengKhouang (FixReq fixReq) {
    String totalPrice1 = fixReq.getTotal_Price().replace(",","");
    try {
        if (fixReq.getBranch_inventory() == null){
            List<Object> paramList = new ArrayList<Object>();
            String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('300',?,?,?,?,?,?,'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"',0)";
            log.info("SQL:"+sql1);
            paramList.add(fixReq.getHeader_id());
            paramList.add(fixReq.getFooter_id());
            paramList.add(fixReq.getDescription());
            paramList.add(fixReq.getItem_id());
            paramList.add(fixReq.getQty_Fix());
            paramList.add(totalPrice1);
            paramList.add(fixReq.getDateFix());
            paramList.add(fixReq.getUserId());
            paramList.add(fixReq.getAdd_on());
            paramList.add(fixReq.getLocation_fix());
            paramList.add(fixReq.getFix_Detail());
            paramList.add(fixReq.getBranch_inventory());
            EBankJdbcTemplate.update(sql1, paramList.toArray());

//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"' where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
        }
        else if (fixReq.getBranch_inventory() != null)
        {
            List<Object> paramList = new ArrayList<Object>();
            String sql1 ="insert into FIX (header_id,footer_id,description,item_id,Qty_Fix,total_Price,DateFix,userId,add_on,location_fix,fix_Detail,branch_inventory) values ('300',?,?,?,?,?,now(),'"+fixReq.getUserId()+"',0,'"+fixReq.getLocation_fix()+"','"+fixReq.getFix_Detail()+"','"+fixReq.getBranch_inventory()+"')";
            log.info("SQL1:"+sql1);
            paramList.add(fixReq.getHeader_id());
            paramList.add(fixReq.getFooter_id());
            paramList.add(fixReq.getDescription());
            paramList.add(fixReq.getItem_id());
            paramList.add(fixReq.getQty_Fix());
            paramList.add(totalPrice1);
            paramList.add(fixReq.getDateFix());
            paramList.add(fixReq.getUserId());
            paramList.add(fixReq.getAdd_on());
            paramList.add(fixReq.getLocation_fix());
            paramList.add(fixReq.getFix_Detail());
            paramList.add(fixReq.getBranch_inventory());
            EBankJdbcTemplate.update(sql1, paramList.toArray());

//ตัดสะต๋อกออก
//            String sql2 = "update TB_items set Qty = Qty - '"+fixReq.getQty_Fix()+"'  where item_id = '"+fixReq.getItem_id()+"'";
//            paramList.add(fixReq.getQty_Fix());
//            log.info("SQL2:"+sql2);
//            EBankJdbcTemplate.update(sql2, paramList.toArray());
//เพี่มสะต๋อก
            String sql3 = "update TB_items set Qty = Qty + '"+fixReq.getQty_Fix()+"'  where item_name = '"+fixReq.getItem_name()+"' and item_id != '"+fixReq.getItem_id()+"' and '"+fixReq.getBranch_inventory()+"'";
            paramList.add(fixReq.getQty_Fix());
            log.info("SQL3:"+sql3);
            EBankJdbcTemplate.update(sql3, paramList.toArray());
        }
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
    return 0;
}
    // update for move item to stock
    public int MoveItemToStockDao (MoveToStockReq moveToStockReq) {
        try {
            String sqlOfferPaper = "update OFFER_PAPER set stock_status='in' where OFFER_CODE='"+moveToStockReq.getOffer_CODE()+"'";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(moveToStockReq.getStock_status());
            paramList.add(moveToStockReq.getOffer_CODE());
            log.info("SQL:"+sqlOfferPaper);
            EBankJdbcTemplate.update(sqlOfferPaper, paramList.toArray());

            String sqlStock = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer()+"' ,size = '"+moveToStockReq.getSize()+"',brand='"+moveToStockReq.getBrand()+"',ber='"+moveToStockReq.getBer()+"' where item_id = '"+moveToStockReq.getItem_id()+"'";
            paramList.add(moveToStockReq.getQty_offer());
            paramList.add(moveToStockReq.getItem_id());
            paramList.add(moveToStockReq.getSize());
            paramList.add(moveToStockReq.getBrand());
            paramList.add(moveToStockReq.getBer());
            log.info("SQL:"+sqlStock);
            EBankJdbcTemplate.update(sqlStock, paramList.toArray());

            String sqlStock1 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer1()+"',size = '"+moveToStockReq.getSize1()+"',brand='"+moveToStockReq.getBrand1()+"',ber='"+moveToStockReq.getBer1()+"' where item_id = '"+moveToStockReq.getItem_id1()+"'";
            paramList.add(moveToStockReq.getQty_offer1());
            paramList.add(moveToStockReq.getItem_id1());
            paramList.add(moveToStockReq.getSize());
            paramList.add(moveToStockReq.getBrand());
            paramList.add(moveToStockReq.getBer());
            log.info("SQL:"+sqlStock1);
            EBankJdbcTemplate.update(sqlStock1, paramList.toArray());

            String sqlStock2 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer2()+"',size = '"+moveToStockReq.getSize2()+"',brand='"+moveToStockReq.getBrand2()+"',ber='"+moveToStockReq.getBer2()+"' where item_id = '"+moveToStockReq.getItem_id2()+"'";
            paramList.add(moveToStockReq.getQty_offer2());
            paramList.add(moveToStockReq.getItem_id2());
            paramList.add(moveToStockReq.getSize2());
            paramList.add(moveToStockReq.getBrand2());
            paramList.add(moveToStockReq.getBer2());
            log.info("SQL:"+sqlStock2);
            EBankJdbcTemplate.update(sqlStock2, paramList.toArray());

            String sqlStock3 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer3()+"',size = '"+moveToStockReq.getSize3()+"',brand='"+moveToStockReq.getBrand3()+"',ber='"+moveToStockReq.getBer3()+"' where item_id = '"+moveToStockReq.getItem_id3()+"'";
            paramList.add(moveToStockReq.getQty_offer3());
            paramList.add(moveToStockReq.getItem_id3());
            paramList.add(moveToStockReq.getSize3());
            paramList.add(moveToStockReq.getBrand3());
            paramList.add(moveToStockReq.getBer3());
            log.info("SQL:"+sqlStock3);
            EBankJdbcTemplate.update(sqlStock3, paramList.toArray());

            String sqlStock4 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer4()+"',size = '"+moveToStockReq.getSize4()+"',brand='"+moveToStockReq.getBrand4()+"',ber='"+moveToStockReq.getBer4()+"' where item_id = '"+moveToStockReq.getItem_id4()+"'";
            paramList.add(moveToStockReq.getQty_offer4());
            paramList.add(moveToStockReq.getItem_id4());
            paramList.add(moveToStockReq.getSize4());
            paramList.add(moveToStockReq.getBrand4());
            paramList.add(moveToStockReq.getBer4());
            log.info("SQL:"+sqlStock4);
            EBankJdbcTemplate.update(sqlStock4, paramList.toArray());

            String sqlStock5 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer5()+"',size = '"+moveToStockReq.getSize5()+"',brand='"+moveToStockReq.getBrand5()+"',ber='"+moveToStockReq.getBer5()+"' where item_id = '"+moveToStockReq.getItem_id5()+"'";
            paramList.add(moveToStockReq.getQty_offer5());
            paramList.add(moveToStockReq.getItem_id5());
            paramList.add(moveToStockReq.getSize5());
            paramList.add(moveToStockReq.getBrand5());
            paramList.add(moveToStockReq.getBer5());
            log.info("SQL:"+sqlStock5);
            EBankJdbcTemplate.update(sqlStock5, paramList.toArray());

            String sqlStock6 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer6()+"',size = '"+moveToStockReq.getSize6()+"',brand='"+moveToStockReq.getBrand6()+"',ber='"+moveToStockReq.getBer6()+"' where item_id = '"+moveToStockReq.getItem_id6()+"'";
            paramList.add(moveToStockReq.getQty_offer6());
            paramList.add(moveToStockReq.getItem_id6());
            paramList.add(moveToStockReq.getSize6());
            paramList.add(moveToStockReq.getBrand6());
            paramList.add(moveToStockReq.getBer6());
            log.info("SQL:"+sqlStock6);
            EBankJdbcTemplate.update(sqlStock6, paramList.toArray());

            String sqlStock7 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer7()+"',size = '"+moveToStockReq.getSize7()+"',brand='"+moveToStockReq.getBrand7()+"',ber='"+moveToStockReq.getBer7()+"' where item_id = '"+moveToStockReq.getItem_id7()+"'";
            paramList.add(moveToStockReq.getQty_offer7());
            paramList.add(moveToStockReq.getItem_id7());
            paramList.add(moveToStockReq.getSize7());
            paramList.add(moveToStockReq.getBrand7());
            paramList.add(moveToStockReq.getBer7());
            log.info("SQL:"+sqlStock7);
            EBankJdbcTemplate.update(sqlStock7, paramList.toArray());

            String sqlStock8 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer8()+"',size = '"+moveToStockReq.getSize8()+"',brand='"+moveToStockReq.getBrand8()+"',ber='"+moveToStockReq.getBer8()+"' where item_id = '"+moveToStockReq.getItem_id8()+"'";
            paramList.add(moveToStockReq.getQty_offer8());
            paramList.add(moveToStockReq.getItem_id8());
            paramList.add(moveToStockReq.getSize8());
            paramList.add(moveToStockReq.getBrand8());
            paramList.add(moveToStockReq.getBer8());
            log.info("SQL:"+sqlStock8);
            EBankJdbcTemplate.update(sqlStock8, paramList.toArray());

            String sqlStock9 = "update TB_items set Qty = Qty + '"+moveToStockReq.getQty_offer9()+"',size = '"+moveToStockReq.getSize9()+"',brand='"+moveToStockReq.getBrand9()+"',ber='"+moveToStockReq.getBer9()+"' where item_id = '"+moveToStockReq.getItem_id9()+"'";
            paramList.add(moveToStockReq.getQty_offer9());
            paramList.add(moveToStockReq.getItem_id9());
            paramList.add(moveToStockReq.getSize9());
            paramList.add(moveToStockReq.getBrand9());
            paramList.add(moveToStockReq.getBer9());
            log.info("SQL:"+sqlStock9);
            EBankJdbcTemplate.update(sqlStock9, paramList.toArray());

// update offer paper
            String sqlop = "update OFFER_PAPER set size = '"+moveToStockReq.getSize()+"',brand='"+moveToStockReq.getBrand()+"',ber='"+moveToStockReq.getBer()+"' where item_id = '"+moveToStockReq.getItem_id()+"'";
            paramList.add(moveToStockReq.getItem_id());
            paramList.add(moveToStockReq.getSize());
            paramList.add(moveToStockReq.getBrand());
            paramList.add(moveToStockReq.getBer());
            log.info("SQL:"+sqlop);
            EBankJdbcTemplate.update(sqlop, paramList.toArray());

            String sqlop1 = "update OFFER_PAPER set size1 = '"+moveToStockReq.getSize1()+"',brand1='"+moveToStockReq.getBrand1()+"',ber1='"+moveToStockReq.getBer1()+"' where item_id1 = '"+moveToStockReq.getItem_id1()+"'";
            paramList.add(moveToStockReq.getItem_id1());
            paramList.add(moveToStockReq.getSize());
            paramList.add(moveToStockReq.getBrand());
            paramList.add(moveToStockReq.getBer());
            log.info("SQL:"+sqlop1);
            EBankJdbcTemplate.update(sqlop1, paramList.toArray());

            String sqlop2 = "update OFFER_PAPER set size2 = '"+moveToStockReq.getSize2()+"',brand2='"+moveToStockReq.getBrand2()+"',ber2='"+moveToStockReq.getBer2()+"' where item_id2 = '"+moveToStockReq.getItem_id2()+"'";
            paramList.add(moveToStockReq.getItem_id2());
            paramList.add(moveToStockReq.getSize2());
            paramList.add(moveToStockReq.getBrand2());
            paramList.add(moveToStockReq.getBer2());
            log.info("SQL:"+sqlop2);
            EBankJdbcTemplate.update(sqlop2, paramList.toArray());

            String sqlop3 = "update OFFER_PAPER set size3 = '"+moveToStockReq.getSize3()+"',brand3='"+moveToStockReq.getBrand3()+"',ber3='"+moveToStockReq.getBer3()+"' where item_id3 = '"+moveToStockReq.getItem_id3()+"'";
            paramList.add(moveToStockReq.getItem_id3());
            paramList.add(moveToStockReq.getSize3());
            paramList.add(moveToStockReq.getBrand3());
            paramList.add(moveToStockReq.getBer3());
            log.info("SQL:"+sqlop3);
            EBankJdbcTemplate.update(sqlop3, paramList.toArray());

            String sqlop4 = "update OFFER_PAPER set size4 = '"+moveToStockReq.getSize4()+"',brand4='"+moveToStockReq.getBrand4()+"',ber4='"+moveToStockReq.getBer4()+"' where item_id4 = '"+moveToStockReq.getItem_id4()+"'";
            paramList.add(moveToStockReq.getItem_id4());
            paramList.add(moveToStockReq.getSize4());
            paramList.add(moveToStockReq.getBrand4());
            paramList.add(moveToStockReq.getBer4());
            log.info("SQL:"+sqlop4);
            EBankJdbcTemplate.update(sqlop4, paramList.toArray());

            String sqlop5 = "update OFFER_PAPER set size5 = '"+moveToStockReq.getSize5()+"',brand5='"+moveToStockReq.getBrand5()+"',ber5='"+moveToStockReq.getBer5()+"' where item_id5 = '"+moveToStockReq.getItem_id5()+"'";
            paramList.add(moveToStockReq.getItem_id5());
            paramList.add(moveToStockReq.getSize5());
            paramList.add(moveToStockReq.getBrand5());
            paramList.add(moveToStockReq.getBer5());
            log.info("SQL:"+sqlop5);
            EBankJdbcTemplate.update(sqlop5, paramList.toArray());

            String sqlop6 = "update OFFER_PAPER set size6 = '"+moveToStockReq.getSize6()+"',brand6='"+moveToStockReq.getBrand6()+"',ber6='"+moveToStockReq.getBer6()+"' where item_id6 = '"+moveToStockReq.getItem_id6()+"'";
            paramList.add(moveToStockReq.getItem_id6());
            paramList.add(moveToStockReq.getSize6());
            paramList.add(moveToStockReq.getBrand6());
            paramList.add(moveToStockReq.getBer6());
            log.info("SQL:"+sqlop6);
            EBankJdbcTemplate.update(sqlop6, paramList.toArray());

            String sqlop7 = "update OFFER_PAPER set size7 = '"+moveToStockReq.getSize7()+"',brand7='"+moveToStockReq.getBrand7()+"',ber7='"+moveToStockReq.getBer7()+"' where item_id7 = '"+moveToStockReq.getItem_id7()+"'";
            paramList.add(moveToStockReq.getItem_id7());
            paramList.add(moveToStockReq.getSize7());
            paramList.add(moveToStockReq.getBrand7());
            paramList.add(moveToStockReq.getBer7());
            log.info("SQL:"+sqlop7);
            EBankJdbcTemplate.update(sqlop7, paramList.toArray());

            String sqlop8 = "update OFFER_PAPER set size8 = '"+moveToStockReq.getSize8()+"',brand8='"+moveToStockReq.getBrand8()+"',ber8='"+moveToStockReq.getBer8()+"' where item_id8 = '"+moveToStockReq.getItem_id8()+"'";
            paramList.add(moveToStockReq.getItem_id8());
            paramList.add(moveToStockReq.getSize8());
            paramList.add(moveToStockReq.getBrand8());
            paramList.add(moveToStockReq.getBer8());
            log.info("SQL:"+sqlop8);
            EBankJdbcTemplate.update(sqlop8, paramList.toArray());

            String sqlop9 = "update OFFER_PAPER set size9 = '"+moveToStockReq.getSize9()+"',brand9='"+moveToStockReq.getBrand9()+"',ber9='"+moveToStockReq.getBer9()+"' where item_id9 = '"+moveToStockReq.getItem_id9()+"'";
            paramList.add(moveToStockReq.getItem_id9());
            paramList.add(moveToStockReq.getSize9());
            paramList.add(moveToStockReq.getBrand9());
            paramList.add(moveToStockReq.getBer9());
            log.info("SQL:"+sqlop9);
            EBankJdbcTemplate.update(sqlop9, paramList.toArray());
// update offer paper

            String sqlPO = "update PURCHASE_ORDER set stockSatus='in' where OFFER_CODE='"+moveToStockReq.getOffer_CODE()+"'";

            paramList.add(moveToStockReq.getStockStatus());
            paramList.add(moveToStockReq.getOffer_CODE());
            log.info("SQL:"+sqlOfferPaper);
            EBankJdbcTemplate.update(sqlPO, paramList.toArray());


        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    //save history item
    public int  MoveItemToStockDaoAndSaveItemHistory (MoveToStockReq moveToStockReq){
        try {
            String sql = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer()+"',now(),'"+moveToStockReq.getItem_id()+"','"+moveToStockReq.getUserId()+"'',"+moveToStockReq.getBranch_id()+"')";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(moveToStockReq.getQty_offer());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql);
            EBankJdbcTemplate.update(sql, paramList.toArray());

            String sql1 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer1()+"',now(),'"+moveToStockReq.getItem_id1()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer1());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id1());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql1);
            EBankJdbcTemplate.update(sql1, paramList.toArray());

            String sql2 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer2()+"',now(),'"+moveToStockReq.getItem_id2()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer2());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id2());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql2);
            EBankJdbcTemplate.update(sql2, paramList.toArray());

            String sql3 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer3()+"',now(),'"+moveToStockReq.getItem_id3()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer3());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id3());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql3);
            EBankJdbcTemplate.update(sql3, paramList.toArray());

            String sql4 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer4()+"',now(),'"+moveToStockReq.getItem_id4()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer4());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id4());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql4);
            EBankJdbcTemplate.update(sql4, paramList.toArray());

            String sql5 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer5()+"',now(),'"+moveToStockReq.getItem_id5()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer5());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id5());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql5);
            EBankJdbcTemplate.update(sql5, paramList.toArray());

            String sql6 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer6()+"',now(),'"+moveToStockReq.getItem_id6()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer6());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id6());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql6);
            EBankJdbcTemplate.update(sql6, paramList.toArray());

            String sql7 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer7()+"',now(),'"+moveToStockReq.getItem_id7()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer7());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id7());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql7);
            EBankJdbcTemplate.update(sql7, paramList.toArray());

            String sql8 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer8()+"',now(),'"+moveToStockReq.getItem_id8()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer8());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id8());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql8);
            EBankJdbcTemplate.update(sql8, paramList.toArray());

            String sql9 = "insert into ITEM_IMPORT_HIS (ITEM_QTY,DATE_IMPORT,ITEM_ID,userId,branch_id) value('"+moveToStockReq.getQty_offer9()+"',now(),'"+moveToStockReq.getItem_id9()+"','"+moveToStockReq.getUserId()+"','"+moveToStockReq.getBranch_id()+"')";
            paramList.add(moveToStockReq.getQty_offer9());
            paramList.add(moveToStockReq.getDateImport());
            paramList.add(moveToStockReq.getItem_id9());
            paramList.add(moveToStockReq.getUserId());
            paramList.add(moveToStockReq.getBranch_id());
            log.info("SQL:"+sql8);
            EBankJdbcTemplate.update(sql9, paramList.toArray());

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    // update price to pay to shop
//    public int PayToShopDao (List<PayToShopReq> payToShopReq) {
//        try {
//            String sql = "update PURCHASE_ORDER set paid = paid + '"+payToShopReq.get(0)+"', tid = total - paid, DateCreatePO = now() where pocode = '"+payToShopReq.get(0)+"'";
////            String sql = "update PURCHASE_ORDER set paid= paid + '"+payToShopReq.getPaid()+"',tid= total - paid ,DateCreatePO=now() where pocode='"+payToShopReq.getPocode()+"'";
//            log.info("SQL:"+sql);
//            List<Object[]> paramList = new ArrayList<Object[]>();
//            for(PayToShopReq resList : payToShopReq) {
//
//                Object[] objectArray = {
//                        resList.getPaid(),
//                        resList.getTid()
//                };
//                paramList.add(objectArray);
//            }
//            EBankJdbcTemplate.batchUpdate(sql, paramList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//        return 0;
//    }
//    pay to shop
    public int PayToShopDao(List<PayToShopReq> payToShopReq) {
        if (payToShopReq == null || payToShopReq.isEmpty()) {
            return -1; // Handle empty input
        }

//        String sql = "UPDATE PURCHASE_ORDER SET paid = paid + ?, tid = total - paid, DateCreatePO = NOW() WHERE pocode = ?";
        String sql = "UPDATE PURCHASE_ORDER SET paid = paid + ?, tid = total - paid, DateCreatePO = NOW() WHERE pocode = ?";
//        String sql2 = "UPDATE OFFER_PAPER SET moneyRate2 = ? ,totalMoney = moneyRate2 * unit_price * qty_offer WHERE offer_CODE = ? ";
        String sql2 = "UPDATE OFFER_PAPER SET totalMoney = (moneyRate * unit_price * qty_offer),\n" +
                "totalMoney1 = (moneyRate * unit_price1 * qty_offer1),\n" +
                "totalMoney2 = (moneyRate * unit_price2 * qty_offer2),\n" +
                "totalMoney3 = (moneyRate * unit_price3 * qty_offer3),\n" +
                "totalMoney4 = (moneyRate * unit_price4 * qty_offer4),\n" +
                "totalMoney5 = (moneyRate * unit_price5 * qty_offer5),\n" +
                "totalMoney6 = (moneyRate * unit_price6 * qty_offer6),\n" +
                "totalMoney7 = (moneyRate * unit_price7 * qty_offer7),\n" +
                "totalMoney8 = (moneyRate * unit_price8 * qty_offer8),\n" +
                "totalMoney9 = (moneyRate * unit_price9 * qty_offer9),\n " +
                "Real_totalMoney = (moneyRate * unit_price * qty_offer )+\n" +
                "(moneyRate * unit_price1 * qty_offer1 )+\n" +
                "(moneyRate * unit_price2 * qty_offer2 )+\n" +
                "(moneyRate * unit_price3 * qty_offer3 )+\n" +
                "(moneyRate * unit_price4 * qty_offer4 )+\n" +
                "(moneyRate * unit_price5 * qty_offer5 )+\n" +
                "(moneyRate * unit_price6 * qty_offer6 )+\n" +
                "(moneyRate * unit_price7 * qty_offer7 )+\n" +
                "(moneyRate * unit_price8 * qty_offer8 )+\n" +
                "(moneyRate * unit_price9 * qty_offer9) , datePay =? ,STATUS_CREDITS='NO' WHERE offer_CODE = ?";
        String sql3 = "INSERT INTO currency_in_kip (OFFER_CODE,TOTAL_MONEY,DATE,CUR,userId,branch_id) SELECT OFFER_CODE,Real_totalMoney,datePay,currency,userId,branch_id from OFFER_PAPER WHERE offer_CODE = ?";

        log.info(sql);
        log.info(sql2);
        log.info(sql3);
        try {
            // Prepare parameter lists for each query
            List<Object[]> paramList1 = new ArrayList<>();
            List<Object[]> paramList2 = new ArrayList<>();
            List<Object[]> paramList3 = new ArrayList<>();

            for (PayToShopReq req : payToShopReq) {
                // First query parameters
                Object[] objectArray1 = {
                        req.getPaid(),
                        req.getPocode()
                };
                paramList1.add(objectArray1);

                // Second query parameters
                Object[] objectArray2 = {
//                        req.getMoneyRate(),
                        req.getDatePay(),
                        req.getOffer_CODE()
                };
                paramList2.add(objectArray2);

                Object[] objectArray3 = {
                        req.getOffer_CODE(),
                        req.getRealTotalMoney(),
                        req.getDatePay(),
                        req.getCur(),
                        req.getUserId(),
                        req.getOffer_CODE(),
                };
                paramList3.add(objectArray3);
            }
            // Execute batch updates
            int[] updateCounts1 = EBankJdbcTemplate.batchUpdate(sql, paramList1);
            int[] updateCounts2 = EBankJdbcTemplate.batchUpdate(sql2, paramList2);
            int[] updateCounts3 = EBankJdbcTemplate.batchUpdate(sql3, paramList3);

        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Indicate failure
        }
        return 0; // Indicate success
    }




    // save offer paper DAOs
    public int savePurchaseorderDao (PurchaseOrderReq purchaseOrderReq) {
        String total = purchaseOrderReq.getTotal().replace(",","");
        String tid = purchaseOrderReq.getTid().replace(",","");
        String paid = purchaseOrderReq.getPaid().replace(",","");
        try {
            String sql ="insert into PURCHASE_ORDER(pocode,OFFER_CODE,total,paid,tid,CUR,userId,DateCreatePO,statusPO,stockSatus,branch_id)" +
                    "values (?,?,?,?,?,?,?,now(),'YES','wait','"+purchaseOrderReq.getBranch_id()+"')";
            log.info("SQL:"+sql);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(purchaseOrderReq.getPocode());
            paramList.add(purchaseOrderReq.getOffer_CODE());
            paramList.add(total);
            paramList.add(paid);
            paramList.add(tid);
            paramList.add(purchaseOrderReq.getCur());
            paramList.add(purchaseOrderReq.getUserId());
            paramList.add(purchaseOrderReq.getDateCreatePO());
            paramList.add(purchaseOrderReq.getStatusPO());
            paramList.add(purchaseOrderReq.getStatusStock());
            paramList.add(purchaseOrderReq.getBranch_id());
            EBankJdbcTemplate.update(sql, paramList.toArray());

            String sqlUpdate = "update OFFER_PAPER set statusPO = 'YES' where offer_CODE = '"+purchaseOrderReq.getOffer_CODE()+"'";
            paramList.add(purchaseOrderReq.getStatusPO());
            log.info("SQL:"+sqlUpdate);
            EBankJdbcTemplate.update(sqlUpdate, paramList.toArray());
        }catch (Exception e ){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    // Show offer paper DAOs
    public List<OfferPaperModelFaso> ShowofferpaperDAOs(OfferPaperReq offerPaperReq) {
        String sql;
        try {
            if (offerPaperReq.getBranch_id() != null) {

                if (offerPaperReq.getStartDate() == null && offerPaperReq.getEndDate() == null) {
                    sql = "SELECT * FROM V_OFFER_PAPER where branch_id='" + offerPaperReq.getBranch_id() + "'";
                    log.info("SQL_show_normal_1:" + sql);
                } else {
                    sql = "SELECT * FROM V_OFFER_PAPER where branch_id='" + offerPaperReq.getBranch_id() + "' and dateCreate between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "' ";
                    log.info("SQL_show_by_date:" + sql);
                }
            } else
            {
//            removed STATUS='N' AND
            if (offerPaperReq.getStartDate() == null && offerPaperReq.getEndDate() == null) {
                sql = "SELECT * FROM V_OFFER_PAPER where BRANCH='" + offerPaperReq.getBranch() + "'";
                log.info("SQL_show_normal_1:" + sql);
            } else {
                sql = "SELECT * FROM V_OFFER_PAPER where BRANCH='" + offerPaperReq.getBranch() + "' and dateCreate between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "' ";
                log.info("SQL_show_by_date:" + sql);
            }
            }
            return EBankJdbcTemplate.query(sql, new RowMapper<OfferPaperModelFaso>() {
                @Override
                public OfferPaperModelFaso mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OfferPaperModelFaso tr = new OfferPaperModelFaso();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setUnit_price(rs.getString("unit_price"));
                    tr.setUnit_price1(rs.getString("unit_price1"));
                    tr.setUnit_price2(rs.getString("unit_price2"));
                    tr.setUnit_price3(rs.getString("unit_price3"));
                    tr.setUnit_price4(rs.getString("unit_price4"));
                    tr.setUnit_price5(rs.getString("unit_price5"));
                    tr.setUnit_price6(rs.getString("unit_price6"));
                    tr.setUnit_price7(rs.getString("unit_price7"));
                    tr.setUnit_price8(rs.getString("unit_price8"));
                    tr.setUnit_price9(rs.getString("unit_price9"));
                    tr.setQty_offer(rs.getString("qty_offer"));
                    tr.setQty_offer1(rs.getString("qty_offer1"));
                    tr.setQty_offer2(rs.getString("qty_offer2"));
                    tr.setQty_offer3(rs.getString("qty_offer3"));
                    tr.setQty_offer4(rs.getString("qty_offer4"));
                    tr.setQty_offer5(rs.getString("qty_offer5"));
                    tr.setQty_offer6(rs.getString("qty_offer6"));
                    tr.setQty_offer7(rs.getString("qty_offer7"));
                    tr.setQty_offer8(rs.getString("qty_offer8"));
                    tr.setQty_offer9(rs.getString("qty_offer9"));
                    tr.setTotalMoney(rs.getDouble("totalMoney"));
                    tr.setTotalMoney1(rs.getDouble("totalMoney1"));
                    tr.setTotalMoney2(rs.getDouble("totalMoney2"));
                    tr.setTotalMoney3(rs.getDouble("totalMoney3"));
                    tr.setTotalMoney4(rs.getDouble("totalMoney4"));
                    tr.setTotalMoney5(rs.getDouble("totalMoney5"));
                    tr.setTotalMoney6(rs.getDouble("totalMoney6"));
                    tr.setTotalMoney7(rs.getDouble("totalMoney7"));
                    tr.setTotalMoney8(rs.getDouble("totalMoney8"));
                    tr.setTotalMoney9(rs.getDouble("totalMoney9"));
                    tr.setDescription(rs.getString("description"));
                    tr.setOfferManName(rs.getString("offerManName"));
                    tr.setJob(rs.getString("job"));
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setItem_name1(rs.getString("item_name1"));
                    tr.setItem_name2(rs.getString("item_name2"));
                    tr.setItem_name3(rs.getString("item_name3"));
                    tr.setItem_name4(rs.getString("item_name4"));
                    tr.setItem_name5(rs.getString("item_name5"));
                    tr.setItem_name6(rs.getString("item_name6"));
                    tr.setItem_name7(rs.getString("item_name7"));
                    tr.setItem_name8(rs.getString("item_name8"));
                    tr.setItem_name9(rs.getString("item_name9"));
                    tr.setImg(rs.getString("img"));
                    tr.setImg1(rs.getString("img1"));
                    tr.setImg2(rs.getString("img2"));
                    tr.setImg3(rs.getString("img3"));
                    tr.setImg4(rs.getString("img4"));
                    tr.setImg5(rs.getString("img5"));
                    tr.setImg6(rs.getString("img6"));
                    tr.setImg7(rs.getString("img7"));
                    tr.setImg8(rs.getString("img8"));
                    tr.setImg9(rs.getString("img9"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                    tr.setDateCreate(rs.getString("dateCreate"));
                    tr.setOFFER_CODE(rs.getString("OFFER_CODE"));
                    tr.setStatus(rs.getString("status"));
//                    tr.setItem_id(rs.getString("item_id"));
                    tr.setStock_status(rs.getString("stock_status"));
                    tr.setStatusPO(rs.getString("statusPO"));
                    tr.setShopName(rs.getString("shop_name"));
                    tr.setItem_id(rs.getString("item_id"));
                    tr.setItem_id1(rs.getString("item_id1"));
                    tr.setItem_id2(rs.getString("item_id2"));
                    tr.setItem_id3(rs.getString("item_id3"));
                    tr.setItem_id4(rs.getString("item_id4"));
                    tr.setItem_id5(rs.getString("item_id5"));
                    tr.setItem_id6(rs.getString("item_id6"));
                    tr.setItem_id7(rs.getString("item_id7"));
                    tr.setItem_id8(rs.getString("item_id8"));
                    tr.setItem_id9(rs.getString("item_id9"));

                    tr.setSize(rs.getString("size"));
                    tr.setBrand(rs.getString("brand"));
                    tr.setBer(rs.getString("ber"));

                    tr.setSize1(rs.getString("size1"));
                    tr.setBrand1(rs.getString("brand1"));
                    tr.setBer1(rs.getString("ber1"));

                    tr.setSize2(rs.getString("size2"));
                    tr.setBrand2(rs.getString("brand2"));
                    tr.setBer2(rs.getString("ber2"));

                    tr.setSize3(rs.getString("size3"));
                    tr.setBrand3(rs.getString("brand3"));
                    tr.setBer3(rs.getString("ber3"));

                    tr.setSize4(rs.getString("size4"));
                    tr.setBrand4(rs.getString("brand4"));
                    tr.setBer4(rs.getString("ber4"));

                    tr.setSize5(rs.getString("size5"));
                    tr.setBrand5(rs.getString("brand5"));
                    tr.setBer5(rs.getString("ber5"));

                    tr.setSize6(rs.getString("size6"));
                    tr.setBrand6(rs.getString("brand6"));
                    tr.setBer6(rs.getString("ber6"));

                    tr.setSize7(rs.getString("size7"));
                    tr.setBrand7(rs.getString("brand7"));
                    tr.setBer7(rs.getString("ber7"));

                    tr.setSize8(rs.getString("size8"));
                    tr.setBrand8(rs.getString("brand8"));
                    tr.setBer8(rs.getString("ber8"));

                    tr.setSize9(rs.getString("size9"));
                    tr.setBrand9(rs.getString("brand9"));
                    tr.setBer9(rs.getString("ber9"));

                    tr.setReal_totalMoney(rs.getDouble("Real_totalMoney"));
                    tr.setMoneyRate(rs.getFloat("moneyRate"));
                    tr.setCurrency(rs.getString("currency"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    to show above to sum
public List<OfferPaperModelFaso> ToShowofferpaperDAOs (OfferPaperReq offerPaperReq) {
    String sql;
    try {
//            removed STATUS='N' AND
        if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
            sql = "SELECT * FROM V_OFFER_PAPER  where  BRANCH='" + offerPaperReq.getBranch() + "'";
            log.info("SQL_show_normal_1:" + sql);
        } else
        {
            sql = "SELECT * FROM V_OFFER_PAPER  where BRANCH='" + offerPaperReq.getBranch() + "' and dateCreate between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "' ";
            log.info("SQL_show_normal_3:" + sql);
        }
        return EBankJdbcTemplate.query(sql, new RowMapper<OfferPaperModelFaso>() {
            @Override
            public OfferPaperModelFaso mapRow(ResultSet rs, int rowNum) throws SQLException {
                OfferPaperModelFaso tr = new OfferPaperModelFaso();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setUnit_price(rs.getString("unit_price"));
                tr.setUnit_price1(rs.getString("unit_price1"));
                tr.setUnit_price2(rs.getString("unit_price2"));
                tr.setUnit_price3(rs.getString("unit_price3"));
                tr.setUnit_price4(rs.getString("unit_price4"));
                tr.setUnit_price5(rs.getString("unit_price5"));
                tr.setUnit_price6(rs.getString("unit_price6"));
                tr.setUnit_price7(rs.getString("unit_price7"));
                tr.setUnit_price8(rs.getString("unit_price8"));
                tr.setUnit_price9(rs.getString("unit_price9"));
                tr.setQty_offer(rs.getString("qty_offer"));
                tr.setQty_offer1(rs.getString("qty_offer1"));
                tr.setQty_offer2(rs.getString("qty_offer2"));
                tr.setQty_offer3(rs.getString("qty_offer3"));
                tr.setQty_offer4(rs.getString("qty_offer4"));
                tr.setQty_offer5(rs.getString("qty_offer5"));
                tr.setQty_offer6(rs.getString("qty_offer6"));
                tr.setQty_offer7(rs.getString("qty_offer7"));
                tr.setQty_offer8(rs.getString("qty_offer8"));
                tr.setQty_offer9(rs.getString("qty_offer9"));
                tr.setTotalMoney(rs.getDouble("totalMoney"));
                tr.setTotalMoney1(rs.getDouble("totalMoney1"));
                tr.setTotalMoney2(rs.getDouble("totalMoney2"));
                tr.setTotalMoney3(rs.getDouble("totalMoney3"));
                tr.setTotalMoney4(rs.getDouble("totalMoney4"));
                tr.setTotalMoney5(rs.getDouble("totalMoney5"));
                tr.setTotalMoney6(rs.getDouble("totalMoney6"));
                tr.setTotalMoney7(rs.getDouble("totalMoney7"));
                tr.setTotalMoney8(rs.getDouble("totalMoney8"));
                tr.setTotalMoney9(rs.getDouble("totalMoney9"));
                tr.setDescription(rs.getString("description"));
                tr.setOfferManName(rs.getString("offerManName"));
                tr.setJob(rs.getString("job"));
                tr.setItem_name(rs.getString("item_name"));
                tr.setItem_name1(rs.getString("item_name1"));
                tr.setItem_name2(rs.getString("item_name2"));
                tr.setItem_name3(rs.getString("item_name3"));
                tr.setItem_name4(rs.getString("item_name4"));
                tr.setItem_name5(rs.getString("item_name5"));
                tr.setItem_name6(rs.getString("item_name6"));
                tr.setItem_name7(rs.getString("item_name7"));
                tr.setItem_name8(rs.getString("item_name8"));
                tr.setItem_name9(rs.getString("item_name9"));
                tr.setImg(rs.getString("img"));
                tr.setImg1(rs.getString("img1"));
                tr.setImg2(rs.getString("img2"));
                tr.setImg3(rs.getString("img3"));
                tr.setImg4(rs.getString("img4"));
                tr.setImg5(rs.getString("img5"));
                tr.setImg6(rs.getString("img6"));
                tr.setImg7(rs.getString("img7"));
                tr.setImg8(rs.getString("img8"));
                tr.setImg9(rs.getString("img9"));
                tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                tr.setDateCreate(rs.getString("dateCreate"));
                tr.setOFFER_CODE(rs.getString("OFFER_CODE"));
                tr.setStatus(rs.getString("status"));
//                    tr.setItem_id(rs.getString("item_id"));
                tr.setStock_status(rs.getString("stock_status"));
                tr.setStatusPO(rs.getString("statusPO"));
                tr.setShopName(rs.getString("shop_name"));
                tr.setItem_id(rs.getString("item_id"));
                tr.setItem_id1(rs.getString("item_id1"));
                tr.setItem_id2(rs.getString("item_id2"));
                tr.setItem_id3(rs.getString("item_id3"));
                tr.setItem_id4(rs.getString("item_id4"));
                tr.setItem_id5(rs.getString("item_id5"));
                tr.setItem_id6(rs.getString("item_id6"));
                tr.setItem_id7(rs.getString("item_id7"));
                tr.setItem_id8(rs.getString("item_id8"));
                tr.setItem_id9(rs.getString("item_id9"));

                tr.setSize(rs.getString("size"));
                tr.setBrand(rs.getString("brand"));
                tr.setBer(rs.getString("ber"));

                tr.setSize1(rs.getString("size1"));
                tr.setBrand1(rs.getString("brand1"));
                tr.setBer1(rs.getString("ber1"));

                tr.setSize2(rs.getString("size2"));
                tr.setBrand2(rs.getString("brand2"));
                tr.setBer2(rs.getString("ber2"));

                tr.setSize3(rs.getString("size3"));
                tr.setBrand3(rs.getString("brand3"));
                tr.setBer3(rs.getString("ber3"));

                tr.setSize4(rs.getString("size4"));
                tr.setBrand4(rs.getString("brand4"));
                tr.setBer4(rs.getString("ber4"));

                tr.setSize5(rs.getString("size5"));
                tr.setBrand5(rs.getString("brand5"));
                tr.setBer5(rs.getString("ber5"));

                tr.setSize6(rs.getString("size6"));
                tr.setBrand6(rs.getString("brand6"));
                tr.setBer6(rs.getString("ber6"));

                tr.setSize7(rs.getString("size7"));
                tr.setBrand7(rs.getString("brand7"));
                tr.setBer7(rs.getString("ber7"));

                tr.setSize8(rs.getString("size8"));
                tr.setBrand8(rs.getString("brand8"));
                tr.setBer8(rs.getString("ber8"));

                tr.setSize9(rs.getString("size9"));
                tr.setBrand9(rs.getString("brand9"));
                tr.setBer9(rs.getString("ber9"));

                tr.setReal_totalMoney(rs.getDouble("Real_totalMoney"));
                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//    report offer paper
public List<OfferPaperModelFaso> ReportShowofferpaperDAOs(OfferPaperReq offerPaperReq) {
    String sql;
    try {
        if (offerPaperReq.getBranch_id() != null)
        {
            if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                sql = "SELECT * FROM V_OFFER_PAPER  where  STATUS='N' AND branch_id='" + offerPaperReq.getBranch_id() + "'";
                log.info("SQL_show_normal_1:" + sql);
            } else
            {
                sql = "SELECT * FROM V_OFFER_PAPER  where STATUS='N' AND branch_id='" + offerPaperReq.getBranch() + "' and dateCreate between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "' ";
                log.info("SQL_show_normal_3:" + sql);
            }
        }
        else
        {
//            removed STATUS='N' AND
            if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                sql = "SELECT * FROM V_OFFER_PAPER  where  STATUS='N' AND BRANCH='" + offerPaperReq.getBranch() + "'";
                log.info("SQL_show_normal_1:" + sql);
            } else
            {
                sql = "SELECT * FROM V_OFFER_PAPER  where STATUS='N' AND BRANCH='" + offerPaperReq.getBranch() + "' and dateCreate between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "' ";
                log.info("SQL_show_normal_3:" + sql);
            }
        }
        return EBankJdbcTemplate.query(sql, new RowMapper<OfferPaperModelFaso>() {
            @Override
            public OfferPaperModelFaso mapRow(ResultSet rs, int rowNum) throws SQLException {
                OfferPaperModelFaso tr = new OfferPaperModelFaso();
//                tr.setUnit_price(rs.getString("unit_price"));
//                tr.setUnit_price1(rs.getString("unit_price1"));
//                tr.setUnit_price2(rs.getString("unit_price2"));
//                tr.setUnit_price3(rs.getString("unit_price3"));
//                tr.setUnit_price4(rs.getString("unit_price4"));
//                tr.setUnit_price5(rs.getString("unit_price5"));
//                tr.setUnit_price6(rs.getString("unit_price6"));
//                tr.setUnit_price7(rs.getString("unit_price7"));
//                tr.setUnit_price8(rs.getString("unit_price8"));
//                tr.setUnit_price9(rs.getString("unit_price9"));
//                tr.setQty_offer(rs.getString("qty_offer"));
//                tr.setQty_offer1(rs.getString("qty_offer1"));
//                tr.setQty_offer2(rs.getString("qty_offer2"));
//                tr.setQty_offer3(rs.getString("qty_offer3"));
//                tr.setQty_offer4(rs.getString("qty_offer4"));
//                tr.setQty_offer5(rs.getString("qty_offer5"));
//                tr.setQty_offer6(rs.getString("qty_offer6"));
//                tr.setQty_offer7(rs.getString("qty_offer7"));
//                tr.setQty_offer8(rs.getString("qty_offer8"));
//                tr.setQty_offer9(rs.getString("qty_offer9"));
//                tr.setTotalMoney(rs.getDouble("totalMoney"));
//                tr.setTotalMoney1(rs.getDouble("totalMoney1"));
//                tr.setTotalMoney2(rs.getDouble("totalMoney2"));
//                tr.setTotalMoney3(rs.getDouble("totalMoney3"));
//                tr.setTotalMoney4(rs.getDouble("totalMoney4"));
//                tr.setTotalMoney5(rs.getDouble("totalMoney5"));
//                tr.setTotalMoney6(rs.getDouble("totalMoney6"));
//                tr.setTotalMoney7(rs.getDouble("totalMoney7"));
//                tr.setTotalMoney8(rs.getDouble("totalMoney8"));
//                tr.setTotalMoney9(rs.getDouble("totalMoney9"));
//                tr.setDescription(rs.getString("description"));
//                tr.setOfferManName(rs.getString("offerManName"));
//                tr.setJob(rs.getString("job"));
//                tr.setItem_name(rs.getString("item_name"));
//                tr.setItem_name1(rs.getString("item_name1"));
//                tr.setItem_name2(rs.getString("item_name2"));
//                tr.setItem_name3(rs.getString("item_name3"));
//                tr.setItem_name4(rs.getString("item_name4"));
//                tr.setItem_name5(rs.getString("item_name5"));
//                tr.setItem_name6(rs.getString("item_name6"));
//                tr.setItem_name7(rs.getString("item_name7"));
//                tr.setItem_name8(rs.getString("item_name8"));
//                tr.setItem_name9(rs.getString("item_name9"));
//                tr.setImg(rs.getString("img"));
//                tr.setImg1(rs.getString("img1"));
//                tr.setImg2(rs.getString("img2"));
//                tr.setImg3(rs.getString("img3"));
//                tr.setImg4(rs.getString("img4"));
//                tr.setImg5(rs.getString("img5"));
//                tr.setImg6(rs.getString("img6"));
//                tr.setImg7(rs.getString("img7"));
//                tr.setImg8(rs.getString("img8"));
//                tr.setImg9(rs.getString("img9"));
//                tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
//                tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
//                tr.setDateCreate(rs.getString("dateCreate"));
//                tr.setOFFER_CODE(rs.getString("OFFER_CODE"));
//                tr.setStatus(rs.getString("status"));
////                    tr.setItem_id(rs.getString("item_id"));
//                tr.setStock_status(rs.getString("stock_status"));
//                tr.setStatusPO(rs.getString("statusPO"));
//                tr.setShopName(rs.getString("shop_name"));
//                tr.setItem_id(rs.getString("item_id"));
//                tr.setItem_id1(rs.getString("item_id1"));
//                tr.setItem_id2(rs.getString("item_id2"));
//                tr.setItem_id3(rs.getString("item_id3"));
//                tr.setItem_id4(rs.getString("item_id4"));
//                tr.setItem_id5(rs.getString("item_id5"));
//                tr.setItem_id6(rs.getString("item_id6"));
//                tr.setItem_id7(rs.getString("item_id7"));
//                tr.setItem_id8(rs.getString("item_id8"));
//                tr.setItem_id9(rs.getString("item_id9"));
//
//                tr.setSize(rs.getString("size"));
//                tr.setBrand(rs.getString("brand"));
//                tr.setBer(rs.getString("ber"));
//
//                tr.setSize1(rs.getString("size1"));
//                tr.setBrand1(rs.getString("brand1"));
//                tr.setBer1(rs.getString("ber1"));
//
//                tr.setSize2(rs.getString("size2"));
//                tr.setBrand2(rs.getString("brand2"));
//                tr.setBer2(rs.getString("ber2"));
//
//                tr.setSize3(rs.getString("size3"));
//                tr.setBrand3(rs.getString("brand3"));
//                tr.setBer3(rs.getString("ber3"));
//
//                tr.setSize4(rs.getString("size4"));
//                tr.setBrand4(rs.getString("brand4"));
//                tr.setBer4(rs.getString("ber4"));
//
//                tr.setSize5(rs.getString("size5"));
//                tr.setBrand5(rs.getString("brand5"));
//                tr.setBer5(rs.getString("ber5"));
//
//                tr.setSize6(rs.getString("size6"));
//                tr.setBrand6(rs.getString("brand6"));
//                tr.setBer6(rs.getString("ber6"));
//
//                tr.setSize7(rs.getString("size7"));
//                tr.setBrand7(rs.getString("brand7"));
//                tr.setBer7(rs.getString("ber7"));
//
//                tr.setSize8(rs.getString("size8"));
//                tr.setBrand8(rs.getString("brand8"));
//                tr.setBer8(rs.getString("ber8"));
//
//                tr.setSize9(rs.getString("size9"));
//                tr.setBrand9(rs.getString("brand9"));
//                tr.setBer9(rs.getString("ber9"));

                tr.setReal_totalMoney(rs.getDouble("Real_totalMoney"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
//                tr.setMoneyCreditWithRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//    report currency offerpaper DAOs
public List<ReportOfferPaperModelTHB> ShowReportSumofferpaperTHB(OfferPaperReq offerPaperReq) {
    String sql;
    try {
        if (offerPaperReq.getBranch_id() != null)
        {
            //            removed STATUS='N' AND
            if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                sql = "SELECT SUM(totalPriceCur)*moneyRate AS Real_totalMoney,SUM(totalPriceCur) AS totalPriceCur FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() + "'";
                log.info("SQL_THB:" + sql);
            }else {
//            removed STATUS='Y' AND
                sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND branch_id= '"+offerPaperReq.getBranch_id()+"' AND datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                log.info("SQL_THB:" + sql);
            }
        }
        else
        {
            //            removed STATUS='N' AND
            if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "'";
                log.info("SQL_THB:" + sql);
            }else {
//            removed STATUS='Y' AND
                sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND BRANCH= '"+offerPaperReq.getBranch()+"' AND datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                log.info("SQL_THB:" + sql);
            }
        }

        return EBankJdbcTemplate.query(sql, new RowMapper<ReportOfferPaperModelTHB>() {
            @Override
            public ReportOfferPaperModelTHB mapRow(ResultSet rs, int rowNum) throws SQLException {
                ReportOfferPaperModelTHB tr = new ReportOfferPaperModelTHB();

                tr.setSumMoneyCurrencyTHB(rs.getDouble("Real_totalMoney"));
                tr.setTotalPriceCur(rs.getDouble("totalPriceCur"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
    public List<ReportOfferPaperModel> ShowReportSumofferpaperUSD(OfferPaperReq offerPaperReq) {
        String sql;
        try {
            if (offerPaperReq.getBranch_id() != null)
            {
                //            removed STATUS='N' AND
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }
            else
            {
                //            removed STATUS='N' AND
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }

            return EBankJdbcTemplate.query(sql, new RowMapper<ReportOfferPaperModel>() {
                @Override
                public ReportOfferPaperModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportOfferPaperModel tr = new ReportOfferPaperModel();

                    tr.setSumMoneycurrencyUSD(rs.getDouble("Real_totalMoney"));
                    tr.setTotalPriceCur(rs.getDouble("totalPriceCur"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ReportOfferPaperModel> CurrencyUSDinKip(OfferPaperReq offerPaperReq) {
        String sql;
        try {
            if (offerPaperReq.getBranch_id() != null)
            {
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }else
            {
                //            removed STATUS='N' AND
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'USD' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }

            return EBankJdbcTemplate.query(sql, new RowMapper<ReportOfferPaperModel>() {
                @Override
                public ReportOfferPaperModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportOfferPaperModel tr = new ReportOfferPaperModel();

                    tr.setSumMoneycurrencyUSD(rs.getDouble("Real_totalMoney"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ReportOfferPaperModelTHB> CurrencyTHBinKip(OfferPaperReq offerPaperReq) {
        String sql;
        try {
            if (offerPaperReq.getBranch_id() != null)
            {
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }else
            {
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'THB' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }


            return EBankJdbcTemplate.query(sql, new RowMapper<ReportOfferPaperModelTHB>() {
                @Override
                public ReportOfferPaperModelTHB mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportOfferPaperModelTHB tr = new ReportOfferPaperModelTHB();

                    tr.setSumMoneyCurrencyTHB(rs.getDouble("Real_totalMoney"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ReportOfferPaperModelLAK> CurrencyLAKinKip(OfferPaperReq offerPaperReq) {
        String sql;
        try {
            if (offerPaperReq.getBranch_id() != null)
            {
                //            removed STATUS='N' AND
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }else {
                //            removed STATUS='N' AND
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() +"'";
                    log.info("SQL_USD:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT Real_totalMoney FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_USD:" + sql);
                }
            }

            return EBankJdbcTemplate.query(sql, new RowMapper<ReportOfferPaperModelLAK>() {
                @Override
                public ReportOfferPaperModelLAK mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportOfferPaperModelLAK tr = new ReportOfferPaperModelLAK();

                    tr.setSumMoneycurrencyLAK(rs.getDouble("Real_totalMoney"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ReportOfferPaperModelLAK> ShowReportSumofferpaperLAK(OfferPaperReq offerPaperReq) {
        String sql;
        try {
            if (offerPaperReq.getBranch_id() != null)
            {
                //            removed STATUS='N' AND
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() + "'";
                    log.info("SQL_LAK:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT * FROM V_OFFER_PAPER where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND branch_id='" + offerPaperReq.getBranch_id() + "' and datePay between '" + offerPaperReq.getStartDate() + "' AND '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_LAK-Date:" + sql);
                }
            }
            else
            {
                //            removed STATUS='N' AND
                if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
                    sql = "SELECT * FROM V_OFFER_PAPER  where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "'";
                    log.info("SQL_LAK:" + sql);
                }else {
//            removed STATUS='Y' AND
                    sql = "SELECT * FROM V_OFFER_PAPER where STATUS_CREDITS='NO' AND currency = 'LAK' AND STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "' and datePay between '" + offerPaperReq.getStartDate() + "' AND '" + offerPaperReq.getEndDate() + "'";
                    log.info("SQL_LAK-Date:" + sql);
                }
            }

            return EBankJdbcTemplate.query(sql, new RowMapper<ReportOfferPaperModelLAK>() {
                @Override
                public ReportOfferPaperModelLAK mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportOfferPaperModelLAK tr = new ReportOfferPaperModelLAK();

                    tr.setSumMoneycurrencyLAK(rs.getDouble("Real_totalMoney"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    pay credit
public List<OfferPaperModelFaso> ShowofferpaperDAOspayCredit (OfferPaperReq offerPaperReq) {
    String sql;
    try {
        if (offerPaperReq.getBranch_id() != null)
        {
            //        removed STATUS='Y' AND
//        STATUS='Y' แม่นร้านเครดิด N แม่นจ่ายสด
            if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
//            sql = "SELECT * FROM V_OFFER_PAPER2  where  STATUS='Y' AND StatusNy='notjaiy' AND BRANCH='" + offerPaperReq.getBranch() + "'";
                sql = "SELECT * FROM V_OFFER_PAPER2 where STATUS='Y' AND branch_id='" + offerPaperReq.getBranch() + "'";
                log.info("report-SQL_pay_credit1:" + sql);
            }else {
//            removed STATUS='Y' AND
//            sql = "SELECT * FROM V_OFFER_PAPER2  where  STATUS='Y' AND StatusNy='notjaiy' AND BRANCH='" + offerPaperReq.getBranch() + "' and dateCreate between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                sql = "SELECT * FROM V_OFFER_PAPER2 where STATUS='Y' AND branch_id='" + offerPaperReq.getBranch() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                log.info("report-SQL_pay_credit2:" + sql);
            }
        }
        else
        {
            //        removed STATUS='Y' AND
//        STATUS='Y' แม่นร้านเครดิด N แม่นจ่ายสด
            if(offerPaperReq.getStartDate()==null && offerPaperReq.getEndDate() ==null){
//            sql = "SELECT * FROM V_OFFER_PAPER2  where  STATUS='Y' AND StatusNy='notjaiy' AND BRANCH='" + offerPaperReq.getBranch() + "'";
                sql = "SELECT * FROM V_OFFER_PAPER2 where STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "'";
                log.info("report-SQL_pay_credit1:" + sql);
            }else {
//            removed STATUS='Y' AND
//            sql = "SELECT * FROM V_OFFER_PAPER2  where  STATUS='Y' AND StatusNy='notjaiy' AND BRANCH='" + offerPaperReq.getBranch() + "' and dateCreate between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                sql = "SELECT * FROM V_OFFER_PAPER2 where STATUS='Y' AND BRANCH='" + offerPaperReq.getBranch() + "' and datePay between '" + offerPaperReq.getStartDate() + "' and '" + offerPaperReq.getEndDate() + "'";
                log.info("report-SQL_pay_credit2:" + sql);
            }
        }

        return EBankJdbcTemplate.query(sql, new RowMapper<OfferPaperModelFaso>() {
            @Override
            public OfferPaperModelFaso mapRow(ResultSet rs, int rowNum) throws SQLException {
                OfferPaperModelFaso tr = new OfferPaperModelFaso();
//                tr.setUnit_price(rs.getString("unit_price"));
//                tr.setUnit_price1(rs.getString("unit_price1"));
//                tr.setUnit_price2(rs.getString("unit_price2"));
//                tr.setUnit_price3(rs.getString("unit_price3"));
//                tr.setUnit_price4(rs.getString("unit_price4"));
//                tr.setUnit_price5(rs.getString("unit_price5"));
//                tr.setUnit_price6(rs.getString("unit_price6"));
//                tr.setUnit_price7(rs.getString("unit_price7"));
//                tr.setUnit_price8(rs.getString("unit_price8"));
//                tr.setUnit_price9(rs.getString("unit_price9"));
//                tr.setQty_offer(rs.getString("qty_offer"));
//                tr.setQty_offer1(rs.getString("qty_offer1"));
//                tr.setQty_offer2(rs.getString("qty_offer2"));
//                tr.setQty_offer3(rs.getString("qty_offer3"));
//                tr.setQty_offer4(rs.getString("qty_offer4"));
//                tr.setQty_offer5(rs.getString("qty_offer5"));
//                tr.setQty_offer6(rs.getString("qty_offer6"));
//                tr.setQty_offer7(rs.getString("qty_offer7"));
//                tr.setQty_offer8(rs.getString("qty_offer8"));
//                tr.setQty_offer9(rs.getString("qty_offer9"));
//                tr.setTotalMoney(rs.getDouble("totalMoney"));
//                tr.setTotalMoney1(rs.getDouble("totalMoney1"));
//                tr.setTotalMoney2(rs.getDouble("totalMoney2"));
//                tr.setTotalMoney3(rs.getDouble("totalMoney3"));
//                tr.setTotalMoney4(rs.getDouble("totalMoney4"));
//                tr.setTotalMoney5(rs.getDouble("totalMoney5"));
//                tr.setTotalMoney6(rs.getDouble("totalMoney6"));
//                tr.setTotalMoney7(rs.getDouble("totalMoney7"));
//                tr.setTotalMoney8(rs.getDouble("totalMoney8"));
//                tr.setTotalMoney9(rs.getDouble("totalMoney9"));
//                tr.setDescription(rs.getString("description"));
//                tr.setOfferManName(rs.getString("offerManName"));
//                tr.setJob(rs.getString("job"));
//                tr.setItem_name(rs.getString("item_name"));
//                tr.setItem_name1(rs.getString("item_name1"));
//                tr.setItem_name2(rs.getString("item_name2"));
//                tr.setItem_name3(rs.getString("item_name3"));
//                tr.setItem_name4(rs.getString("item_name4"));
//                tr.setItem_name5(rs.getString("item_name5"));
//                tr.setItem_name6(rs.getString("item_name6"));
//                tr.setItem_name7(rs.getString("item_name7"));
//                tr.setItem_name8(rs.getString("item_name8"));
//                tr.setItem_name9(rs.getString("item_name9"));
//                tr.setImg(rs.getString("img"));
//                tr.setImg1(rs.getString("img1"));
//                tr.setImg2(rs.getString("img2"));
//                tr.setImg3(rs.getString("img3"));
//                tr.setImg4(rs.getString("img4"));
//                tr.setImg5(rs.getString("img5"));
//                tr.setImg6(rs.getString("img6"));
//                tr.setImg7(rs.getString("img7"));
//                tr.setImg8(rs.getString("img8"));
//                tr.setImg9(rs.getString("img9"));
//                tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
//                tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
//                tr.setDateCreate(rs.getString("dateCreate"));
//                tr.setOFFER_CODE(rs.getString("OFFER_CODE"));
//                tr.setStatus(rs.getString("status"));
////                    tr.setItem_id(rs.getString("item_id"));
//                tr.setStock_status(rs.getString("stock_status"));
//                tr.setStatusPO(rs.getString("statusPO"));
//                tr.setShopName(rs.getString("shop_name"));
//                tr.setItem_id(rs.getString("item_id"));
//                tr.setItem_id1(rs.getString("item_id1"));
//                tr.setItem_id2(rs.getString("item_id2"));
//                tr.setItem_id3(rs.getString("item_id3"));
//                tr.setItem_id4(rs.getString("item_id4"));
//                tr.setItem_id5(rs.getString("item_id5"));
//                tr.setItem_id6(rs.getString("item_id6"));
//                tr.setItem_id7(rs.getString("item_id7"));
//                tr.setItem_id8(rs.getString("item_id8"));
//                tr.setItem_id9(rs.getString("item_id9"));
//
//                tr.setSize(rs.getString("size"));
//                tr.setBrand(rs.getString("brand"));
//                tr.setBer(rs.getString("ber"));
//
//                tr.setSize1(rs.getString("size1"));
//                tr.setBrand1(rs.getString("brand1"));
//                tr.setBer1(rs.getString("ber1"));
//
//                tr.setSize2(rs.getString("size2"));
//                tr.setBrand2(rs.getString("brand2"));
//                tr.setBer2(rs.getString("ber2"));
//
//                tr.setSize3(rs.getString("size3"));
//                tr.setBrand3(rs.getString("brand3"));
//                tr.setBer3(rs.getString("ber3"));
//
//                tr.setSize4(rs.getString("size4"));
//                tr.setBrand4(rs.getString("brand4"));
//                tr.setBer4(rs.getString("ber4"));
//
//                tr.setSize5(rs.getString("size5"));
//                tr.setBrand5(rs.getString("brand5"));
//                tr.setBer5(rs.getString("ber5"));
//
//                tr.setSize6(rs.getString("size6"));
//                tr.setBrand6(rs.getString("brand6"));
//                tr.setBer6(rs.getString("ber6"));
//
//                tr.setSize7(rs.getString("size7"));
//                tr.setBrand7(rs.getString("brand7"));
//                tr.setBer7(rs.getString("ber7"));
//
//                tr.setSize8(rs.getString("size8"));
//                tr.setBrand8(rs.getString("brand8"));
//                tr.setBer8(rs.getString("ber8"));
//
//                tr.setSize9(rs.getString("size9"));
//                tr.setBrand9(rs.getString("brand9"));
//                tr.setBer9(rs.getString("ber9"));

                tr.setReal_totalMoneyCredit(rs.getDouble("Real_totalMoney"));
//                tr.setCurrency(rs.getString("currency"));
//                tr.setMoneyRate(rs.getFloat("moneyRate"));
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ new +++++++++++++++++++++++++++++++++++++++++

                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
    // show fix list DAOs
    public List<ShowFixModel> ShowFixListDAOs(FixReq fixReq) {
        try {
            String sql;
            if (fixReq.getBranch_id() != null)
            {
                 sql ="SELECT * FROM V_FIX_4SHOW  where branch_id='"+fixReq.getBranch_id()+"'";
//            String sql ="SELECT * FROM V_APPROVE_FIX  where BRANCH='"+fixReq.getBranch()+"'";
                log.info("SQL:"+sql);
            }else
            {
                 sql ="SELECT * FROM V_FIX_4SHOW  where BRANCH='"+fixReq.getBranch()+"'";
//            String sql ="SELECT * FROM V_APPROVE_FIX  where BRANCH='"+fixReq.getBranch()+"'";
                log.info("SQL:"+sql);
            }
            return EBankJdbcTemplate.query(sql, new RowMapper<ShowFixModel>() {
                @Override
                public ShowFixModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ShowFixModel tr = new ShowFixModel();
                    tr.setFixId(rs.getString("fixId"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setF_BRANCH(rs.getString("F_BRANCH"));
                    tr.setQty_Fix(rs.getString("Qty_Fix"));
                    tr.setTotal_Price(rs.getString("total_Price"));
                    tr.setAdd_on(rs.getString("add_on"));
                    tr.setDescription(rs.getString("description"));
                    tr.setDateFix(rs.getString("DateFix"));
                    tr.setLocation_fix(rs.getString("location_fix"));
                    tr.setFix_Detail(rs.getString("fix_Detail"));
                    tr.setBranch_inventory(rs.getString("branch_inventory"));
                    tr.setImg(rs.getString("img"));
                    tr.setFinalTotalPrice(rs.getDouble("finalTotalPrice"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    dao prove list fix
public List<ReqListOfFixModel> ShowProveFixListDAOs (FixReq fixReq) {
        String sql="";
    try {
        if (fixReq.getBranch_id() != null)
        {
            if (fixReq.getToKen().equals("2514f6e2995b6e796d197c673d48375a271157a5e01f164d44ea7df278f7a377"))
            {
                sql ="SELECT * FROM V_APPROVE_FIX";
                log.info("SQL admin:"+sql);
            }
            else
            {
                sql ="SELECT * FROM V_APPROVE_FIX WHERE branch_id ='"+fixReq.getBranch_id()+"'";
                log.info("SQL branch:"+sql);
            }
        }
        else
        {
            if (fixReq.getToKen().equals("2514f6e2995b6e796d197c673d48375a271157a5e01f164d44ea7df278f7a377"))
            {
                sql ="SELECT * FROM V_APPROVE_FIX";
                log.info("SQL admin:"+sql);
            }
            else
            {
                sql ="SELECT * FROM V_APPROVE_FIX WHERE BRANCH_Inventory ='"+fixReq.getBranch()+"'";
                log.info("SQL branch:"+sql);
            }
        }
        return EBankJdbcTemplate.query(sql, new RowMapper<ReqListOfFixModel>() {
            @Override
            public ReqListOfFixModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ReqListOfFixModel tr = new ReqListOfFixModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setItem_name(rs.getString("item_name"));
                tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                tr.setF_BRANCH(rs.getString("F_BRANCH"));
                tr.setQty_Fix(rs.getString("qty_Fix"));
                tr.setTotal_Price(rs.getString("total_Price"));
                tr.setDescription(rs.getString("description"));
                tr.setDateFix(rs.getString("dateFix"));
                tr.setLocation_fix(rs.getString("location_fix"));
                tr.setFix_Detail(rs.getString("fix_Detail"));
                tr.setApprove_status(rs.getString("approve_status"));
                tr.setNew_status(rs.getString("new_status"));
                tr.setBranch_inventory(rs.getString("BRANCH_Inventory"));
                tr.setItem_id(rs.getString("item_id"));
                tr.setHeader_id(rs.getString("header_id"));
                tr.setFooter_id(rs.getString("footer_id"));
                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
//old inventory dao
public List<OldInventoryModel> ShowOldInventoryDAOs (OldInventoryReq oldInventoryReq) {
    String sql;
    String branchId = oldInventoryReq.getBranch_id();
    try {
        if (branchId != null && !branchId.isEmpty())
        {
             sql ="SELECT * FROM OLD_INVENTORY WHERE branch_id = '"+oldInventoryReq.getBranch_id()+"'";
            log.info("SQL is bor :"+sql);
        }
        else {
             sql ="SELECT * FROM OLD_INVENTORY WHERE branchNo = '"+oldInventoryReq.getBranch()+"'";
            log.info("SQL not bor:"+sql);
        }
        return EBankJdbcTemplate.query(sql, new RowMapper<OldInventoryModel>() {
            @Override
            public OldInventoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                OldInventoryModel tr = new OldInventoryModel();
                tr.setKey_id(rs.getString("KEY_ID"));
                tr.setItemName_Oldwarehouse(rs.getString("item_name"));
                tr.setVehicle_Oldwarehouse(rs.getString("header_truck_id"));
                tr.setVehiclefooter_Oldwarehouse(rs.getString("footer_truck_id"));
                tr.setQty_Oldwarehouse(rs.getString("amount"));
                tr.setImage_Oldwarehouse(rs.getString("picture"));
                tr.setDescription_Oldwarehouse(rs.getString("detail"));
                tr.setImportExpirationDate_Oldwarehouse(rs.getString("date_in"));
                tr.setSelectedType_Oldwarehouse(rs.getString("type"));
                tr.setPrice_Oldwarehouse(rs.getString("price"));
                tr.setCur(rs.getString("cur"));
                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
    //report stock day week DAOs
    public List<ReportstockModel> reportStockDayWeekDaos(ReportstockReq reportstockReq) {
        String sql;
        try {
            if (reportstockReq.getItem_id() == null)
            {
                if (reportstockReq.getStartDate() == null){
                    sql = "SELECT * FROM V_REPORT_STOCK  WHERE branch_inventory ='"+reportstockReq.getBranch()+"'";
                    log.info("SQL1:" + sql);
                }else
                {
                    sql = "SELECT * FROM V_REPORT_STOCK  WHERE branch_inventory ='"+reportstockReq.getBranch()+"' and dateOut between '" + reportstockReq.getStartDate() + "' and '" + reportstockReq.getEndDate() + "'\n" +
                            "or dateIn BETWEEN '" + reportstockReq.getStartDate() + "' and '"+ reportstockReq.getEndDate() +"'";
                    log.info("SQL2:" + sql);
                }
            }
            else {
                if (reportstockReq.getStartDate() == null){
                    sql = "SELECT * FROM V_REPORT_STOCK  WHERE item_id = '" + reportstockReq.getItem_id() +"'";
                    log.info("SQL3:" + sql);
                }
                else
                {
                    sql = "SELECT * FROM V_REPORT_STOCK  WHERE item_id = '" + reportstockReq.getItem_id() + "' and dateOut between '" + reportstockReq.getStartDate() + "' and '" + reportstockReq.getEndDate() + "'\n" +
                            "or dateIn BETWEEN '" + reportstockReq.getStartDate() + "' and '"+ reportstockReq.getEndDate() +"'";
                    log.info("SQL4:" + sql);
                }
            }
            return EBankJdbcTemplate.query(sql, new RowMapper<ReportstockModel>() {
                @Override
                public ReportstockModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportstockModel tr = new ReportstockModel();
                    tr.setItem_id(rs.getString("item_id"));
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setUnit(rs.getString("unit"));
                    tr.setImg(rs.getString("img"));
                    tr.setDateOut(rs.getString("dateOut"));
                    tr.setDateIn(rs.getString("dateIn"));
                    tr.setQty_stock(rs.getDouble("QTY_stock"));
                    tr.setQty_out(rs.getDouble("QTY_out"));
                    tr.setQty_in(rs.getDouble("QTY_in"));
                    tr.setYordyokma(rs.getDouble("yordyokma"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // for inventory object
    public List<ReportstockModel2>inventoryalaireportStockDayWeekDaos (ReportstockReq reportstockReq) {
        StringBuilder sb = new StringBuilder();
        String conItemId = "";
        try {
            if(!"".equals(reportstockReq.getBranch())){
                conItemId = " AND branch_inventory = '"+reportstockReq.getBranch()+"' ";
            }else {
                conItemId = " AND item_id = '"+reportstockReq.getItem_id()+"' ";
            }
            sb.append("SELECT item_name,Qty,img FROM TB_items  where 1=1 ");
            sb.append(conItemId);
            String sql = sb.toString();
            return EBankJdbcTemplate.query(sql, new RowMapper<ReportstockModel2>() {
                @Override
                public ReportstockModel2 mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportstockModel2 tr = new ReportstockModel2();
                    tr.setItem_name2(rs.getString("item_name"));
                    tr.setImg2(rs.getString("img"));
                    tr.setQty_stock2(rs.getDouble("Qty"));
                    tr.setYodyokma2(rs.getDouble("Qty"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // show fix Detail DAOs
    public List<ShowFixModel> ShowFixListDAOsDetail(FixReq fixReq) {
        try {
            String sql;
            if (fixReq.getBranch_id() != null)
            {
                 sql ="SELECT * FROM V_FIX_4SHOW  where branch_id='"+fixReq.getBranch_id()+"' and fixId = '"+fixReq.getFixId()+"'";
//            String sql ="SELECT * FROM V_APPROVE_FIX  where BRANCH='"+fixReq.getBranch()+"' and fixId = '"+fixReq.getFixId()+"'";
                log.info("SQL:"+sql);
            }
            else
            {
                 sql ="SELECT * FROM V_FIX_4SHOW  where BRANCH='"+fixReq.getBranch()+"' and fixId = '"+fixReq.getFixId()+"'";
                log.info("SQL:"+sql);
            }
            return EBankJdbcTemplate.query(sql, new RowMapper<ShowFixModel>() {
                @Override
                public ShowFixModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ShowFixModel tr = new ShowFixModel();
                    tr.setFixId(rs.getString("fixId"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setF_BRANCH(rs.getString("F_BRANCH"));
                    tr.setQty_Fix(rs.getString("Qty_Fix"));
                    tr.setTotal_Price(rs.getString("total_Price"));
                    tr.setAdd_on(rs.getString("add_on"));
                    tr.setDescription(rs.getString("description"));
                    tr.setDateFix(rs.getString("DateFix"));
                    tr.setLocation_fix(rs.getString("location_fix"));
                    tr.setFix_Detail(rs.getString("fix_Detail"));
                    tr.setBranch_inventory(rs.getString("branch_inventory"));
                    tr.setDetail_A_lai(rs.getString("item_name"));
                    tr.setImg(rs.getString("img"));
                    tr.setFinalTotalPrice(rs.getDouble("finalTotalPrice"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // update fix cost
    public List<ShowFixModel> UpdateFixCostDao (FixReq fixReq) {
        try {
            String sql ="update FIX set add_on = '"+fixReq.getAdd_on()+"' where fixId ='"+fixReq.getFixId()+"'";
            log.info("SQL:"+sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<ShowFixModel>() {
                @Override
                public ShowFixModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ShowFixModel tr = new ShowFixModel();
                    tr.setAdd_on(rs.getString("add_on"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // fix report DAOs
    public List<FixModelFaso> FixReportDAOs (FixReq fixReq) {
        String sql;
        try {
            if (fixReq.getBranch_id() != null)
            {
                if (fixReq.getStartDate() == null){
                    sql ="SELECT * FROM V_FIX  where branch_id='"+fixReq.getBranch_id()+"'  ";
// new                sql ="SELECT * FROM V_APPROVE_FIX  where BRANCH='"+fixReq.getBranch()+"'  ";
                    log.info("SQL:"+sql);
                }
                else{
                    sql ="SELECT * FROM V_FIX  where branch_id='"+fixReq.getBranch_id()+"' and DateFix between '"+fixReq.getStartDate()+"' and '"+fixReq.getEndDate()+"' ";
// new                sql ="SELECT * FROM V_APPROVE_FIX  where BRANCH='"+fixReq.getBranch()+"' and DateFix between '"+fixReq.getStartDate()+"' and '"+fixReq.getEndDate()+"' ";
                    log.info("SQL:"+sql);
                }
            }
            else
            {
                if (fixReq.getStartDate() == null){
                    sql ="SELECT * FROM V_FIX  where BRANCH='"+fixReq.getBranch()+"'  ";
// new                sql ="SELECT * FROM V_APPROVE_FIX  where BRANCH='"+fixReq.getBranch()+"'  ";
                    log.info("SQL:"+sql);
                }
                else{
                    sql ="SELECT * FROM V_FIX  where BRANCH='"+fixReq.getBranch()+"' and DateFix between '"+fixReq.getStartDate()+"' and '"+fixReq.getEndDate()+"' ";
// new                sql ="SELECT * FROM V_APPROVE_FIX  where BRANCH='"+fixReq.getBranch()+"' and DateFix between '"+fixReq.getStartDate()+"' and '"+fixReq.getEndDate()+"' ";
                    log.info("SQL:"+sql);
                }
            }

            return EBankJdbcTemplate.query(sql, new RowMapper<FixModelFaso>() {
                @Override
                public FixModelFaso mapRow(ResultSet rs, int rowNum) throws SQLException {
                    FixModelFaso tr = new FixModelFaso();
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_BRANCH(rs.getString("F_CARD_NO"));
                    tr.setTotal_Price(rs.getString("total_Price"));
                    tr.setDateFix(rs.getString("DateFix"));
                    tr.setTotalTimeFix(rs.getString("totalTimeFix"));
                    tr.setTotalFixCost(rs.getDouble("totalFixCost"));
                    return tr;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Show offer paper Details DAOs
    public List<OfferPaperModelFaso> ShowofferpaperDetails (OfferPaperReq offerPaperReq) {
        try {
            String sql ="SELECT * FROM V_OFFER_PAPER  where OFFER_CODE='"+offerPaperReq.getOffer_CODE()+"'";
            log.info("SQL:"+sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<OfferPaperModelFaso>() {
                @Override
                public OfferPaperModelFaso mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OfferPaperModelFaso tr = new OfferPaperModelFaso();
                    tr.setUnit_price(rs.getString("unit_price"));
                    tr.setUnit_price1(rs.getString("unit_price1"));
                    tr.setUnit_price2(rs.getString("unit_price2"));
                    tr.setUnit_price3(rs.getString("unit_price3"));
                    tr.setUnit_price4(rs.getString("unit_price4"));
                    tr.setUnit_price5(rs.getString("unit_price5"));
                    tr.setUnit_price6(rs.getString("unit_price6"));
                    tr.setUnit_price7(rs.getString("unit_price7"));
                    tr.setUnit_price8(rs.getString("unit_price8"));
                    tr.setUnit_price9(rs.getString("unit_price9"));
                    tr.setQty_offer(rs.getString("qty_offer"));
                    tr.setQty_offer1(rs.getString("qty_offer1"));
                    tr.setQty_offer2(rs.getString("qty_offer2"));
                    tr.setQty_offer3(rs.getString("qty_offer3"));
                    tr.setQty_offer4(rs.getString("qty_offer4"));
                    tr.setQty_offer5(rs.getString("qty_offer5"));
                    tr.setQty_offer6(rs.getString("qty_offer6"));
                    tr.setQty_offer7(rs.getString("qty_offer7"));
                    tr.setQty_offer8(rs.getString("qty_offer8"));
                    tr.setQty_offer9(rs.getString("qty_offer9"));
                    tr.setTotalMoney(rs.getDouble("totalMoney"));
                    tr.setTotalMoney1(rs.getDouble("totalMoney1"));
                    tr.setTotalMoney2(rs.getDouble("totalMoney2"));
                    tr.setTotalMoney3(rs.getDouble("totalMoney3"));
                    tr.setTotalMoney4(rs.getDouble("totalMoney4"));
                    tr.setTotalMoney5(rs.getDouble("totalMoney5"));
                    tr.setTotalMoney6(rs.getDouble("totalMoney6"));
                    tr.setTotalMoney7(rs.getDouble("totalMoney7"));
                    tr.setTotalMoney8(rs.getDouble("totalMoney8"));
                    tr.setTotalMoney9(rs.getDouble("totalMoney9"));
                    tr.setDescription(rs.getString("description"));
                    tr.setOfferManName(rs.getString("offerManName"));
                    tr.setJob(rs.getString("job"));
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setItem_name1(rs.getString("item_name1"));
                    tr.setItem_name2(rs.getString("item_name2"));
                    tr.setItem_name3(rs.getString("item_name3"));
                    tr.setItem_name4(rs.getString("item_name4"));
                    tr.setItem_name5(rs.getString("item_name5"));
                    tr.setItem_name6(rs.getString("item_name6"));
                    tr.setItem_name7(rs.getString("item_name7"));
                    tr.setItem_name8(rs.getString("item_name8"));
                    tr.setItem_name9(rs.getString("item_name9"));
                    tr.setImg(rs.getString("img"));
                    tr.setImg1(rs.getString("img1"));
                    tr.setImg2(rs.getString("img2"));
                    tr.setImg3(rs.getString("img3"));
                    tr.setImg4(rs.getString("img4"));
                    tr.setImg5(rs.getString("img5"));
                    tr.setImg6(rs.getString("img6"));
                    tr.setImg7(rs.getString("img7"));
                    tr.setImg8(rs.getString("img8"));
                    tr.setImg9(rs.getString("img9"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setF_CARD_NO(rs.getString("F_CARD_NO"));
                    tr.setDateCreate(rs.getString("dateCreate"));
                    tr.setOFFER_CODE(rs.getString("OFFER_CODE"));
                    tr.setStatus(rs.getString("status"));
//                    tr.setItem_id(rs.getString("item_id"));
                    tr.setStock_status(rs.getString("stock_status"));
                    tr.setStatusPO(rs.getString("statusPO"));
                    tr.setShopName(rs.getString("shop_name"));
                    tr.setItem_id(rs.getString("item_id"));
                    tr.setItem_id1(rs.getString("item_id1"));
                    tr.setItem_id2(rs.getString("item_id2"));
                    tr.setItem_id3(rs.getString("item_id3"));
                    tr.setItem_id4(rs.getString("item_id4"));
                    tr.setItem_id5(rs.getString("item_id5"));
                    tr.setItem_id6(rs.getString("item_id6"));
                    tr.setItem_id7(rs.getString("item_id7"));
                    tr.setItem_id8(rs.getString("item_id8"));
                    tr.setItem_id9(rs.getString("item_id9"));
                    tr.setSize(rs.getString("size"));
                    tr.setBrand(rs.getString("brand"));
                    tr.setBer(rs.getString("ber"));

                    tr.setSize1(rs.getString("size1"));
                    tr.setBrand1(rs.getString("brand1"));
                    tr.setBer1(rs.getString("ber1"));

                    tr.setSize2(rs.getString("size2"));
                    tr.setBrand2(rs.getString("brand2"));
                    tr.setBer2(rs.getString("ber2"));

                    tr.setSize3(rs.getString("size3"));
                    tr.setBrand3(rs.getString("brand3"));
                    tr.setBer3(rs.getString("ber3"));

                    tr.setSize4(rs.getString("size4"));
                    tr.setBrand4(rs.getString("brand4"));
                    tr.setBer4(rs.getString("ber4"));

                    tr.setSize5(rs.getString("size5"));
                    tr.setBrand5(rs.getString("brand5"));
                    tr.setBer5(rs.getString("ber5"));

                    tr.setSize6(rs.getString("size6"));
                    tr.setBrand6(rs.getString("brand6"));
                    tr.setBer6(rs.getString("ber6"));

                    tr.setSize7(rs.getString("size7"));
                    tr.setBrand7(rs.getString("brand7"));
                    tr.setBer7(rs.getString("ber7"));

                    tr.setSize8(rs.getString("size8"));
                    tr.setBrand8(rs.getString("brand8"));
                    tr.setBer8(rs.getString("ber8"));

                    tr.setSize9(rs.getString("size9"));
                    tr.setBrand9(rs.getString("brand9"));
                    tr.setBer9(rs.getString("ber9"));
                    tr.setReal_totalMoney(rs.getDouble("Real_totalMoney"));
                    tr.setCurrency(rs.getString("currency"));
                    tr.setMoneyRate(rs.getFloat("moneyRate"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // paid to shop detail DAOs
    public List<PaidToShopDetailModel> PaidToShopDetailsDaos (PaidToShopDetailReq paidToShopDetailReq) {
        String sql;
        try {
            if (paidToShopDetailReq.getBranch_id() !=null)
            {
                sql ="SELECT * FROM REPORT_SHOPS_MUST_PAY where pocode = '"+paidToShopDetailReq.getPo_code()+"' and OFFER_CODE = '"+paidToShopDetailReq.getOffer_code()+"' and branch_id ='"+paidToShopDetailReq.getBranch_id()+"'  ";
                log.info("SQL:"+sql);
            }else
            {
                sql ="SELECT * FROM REPORT_SHOPS_MUST_PAY where pocode = '"+paidToShopDetailReq.getPo_code()+"' and OFFER_CODE = '"+paidToShopDetailReq.getOffer_code()+"' and BRANCH ='"+paidToShopDetailReq.getBranch()+"'  ";
                log.info("SQL:"+sql);
            }

            return EBankJdbcTemplate.query(sql, new RowMapper<PaidToShopDetailModel>() {
                @Override
                public PaidToShopDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PaidToShopDetailModel tr = new PaidToShopDetailModel();
                    tr.setPocode(rs.getString("pocode"));
                    tr.setOffcode(rs.getString("OFFER_CODE"));
                    tr.setItem_name(rs.getString("item_name"));
                    tr.setQty_offer(rs.getString("qty_offer"));
                    tr.setTotal(rs.getString("total"));
                    tr.setPaid(rs.getString("paid"));
                    tr.setTid(rs.getString("tid"));
                    tr.setCur(rs.getString("CUR"));
                    tr.setShop_name(rs.getString("shop_name"));
                    tr.setDateCreatePO(rs.getString("DateCreatePO"));
                    tr.setMoneyRate(rs.getFloat("moneyRate"));

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // gen offer code daos
    public List<OfferCodeModel> showOFFER_CODE() {
        try {
            String sql = "SELECT * FROM GEN_OFFER_NO ";
            return EBankJdbcTemplate.query(sql, new RowMapper<OfferCodeModel>() {
                @Override
                public OfferCodeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OfferCodeModel tr = new OfferCodeModel();
                    tr.setOFFER_CODE(rs.getString("OFFER_CODE"));
                    return tr;
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    // gen PO code daos
    public List<PoCodeModel> showPO_CODE() {
        try {
            String sql = "SELECT * FROM GEN_PO ";
            return EBankJdbcTemplate.query(sql, new RowMapper<PoCodeModel>() {
                @Override
                public PoCodeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PoCodeModel tr = new PoCodeModel();
                    tr.setPO_CODE(rs.getString("PO_CODE"));
                    return tr;
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    // update Shops
    public int UpdateShops(ShopReq shopReq) throws ParseException {
        List<Shops> data = new ArrayList<>();
        try{
            String SQL = "update TB_shop set shop_name=?,address=?,phone=?,country=?,currency=?,amount_money=?,userId='"+shopReq.getUserId()+"',branch_id='"+shopReq.getBranch_id()+"' where shop_id='"+shopReq.getShop_id()+"'";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(shopReq.getShop_name());
            paramList.add(shopReq.getAddress());
            paramList.add(shopReq.getPhone());
            paramList.add(shopReq.getCountry());
            paramList.add(shopReq.getCurrency());
            paramList.add(shopReq.getAmount_money());
            paramList.add(shopReq.getUserId());
            paramList.add(shopReq.getBranch_id());
            paramList.add(shopReq.getShop_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    // LimitStock
    public int LimitStockDao(ItemReq itemReq) throws ParseException {
        List<Shops> data = new ArrayList<>();
        try{
            String SQL = "update TB_items set limitQty=? where item_id=?";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(itemReq.getLimitQty());
            paramList.add(itemReq.getItem_id());
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
//    delete fill oill his
public int delfillOill(FillOilReq fillOilReq) {
    int i =0;
    try {
        String SQL = "delete from FILL_FUEL_HIS where carId='"+fillOilReq.getCarId()+"'";
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
            String SQL = "insert into TB_items (item_name,unit,unit_price,Qty,img,userId,branch_inventory,branch_id,limitQty)values (?,?,?,?,?,'"+itemReq.getUserId()+"','"+itemReq.getBranch()+"','"+itemReq.getBranch_id()+"','"+itemReq.getLimitQty()+"')";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(itemReq.getItemName());
            paramList.add(itemReq.getUnit());
            paramList.add(itemReq.getUnit_price());
            paramList.add(itemReq.getQty());
            paramList.add(path+fileName);
            paramList.add(itemReq.getUserId());
            paramList.add(itemReq.getBranch());
            paramList.add(itemReq.getBranch_id());
            paramList.add(itemReq.getLimitQty());
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
           String SQL = "Update TB_items set item_name=?,unit=?,unit_price=?,userId=?,Qty=?,branch_inventory=?,branch_id=? where item_id =?";
           log.info("SQL:"+SQL);
           List<Object> paramList = new ArrayList<Object>();
           paramList.add(itemReq.getItemName());
           paramList.add(itemReq.getUnit());
           paramList.add(itemReq.getUnit_price());
           paramList.add(itemReq.getUserId());
           paramList.add(itemReq.getQty());
           paramList.add(itemReq.getBranch());
           paramList.add(itemReq.getBranch_id());
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
            String SQL ="update TB_items set item_name=?,unit=?,unit_price=?,Qty=?,img=?,branch_inventory='"+itemReq.getBranch()+"',branch_id=? where  item_id=?";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(itemReq.getItemName());
            paramList.add(itemReq.getUnit());
            paramList.add(itemReq.getUnit_price());
            paramList.add(itemReq.getQty());
            paramList.add(path+fileName);
            paramList.add(itemReq.getBranch());
            paramList.add(itemReq.getBranch_id());
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
