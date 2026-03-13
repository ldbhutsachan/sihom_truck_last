package com.ldb.truck.Service.StockService;

import com.ldb.truck.Dao.ItemList.ItemListModel;
import com.ldb.truck.Dao.ItemList.modelItemList;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Entity.Item.ItemEntity;
import com.ldb.truck.Entity.Item.ItemListView;
import com.ldb.truck.Entity.Item.listItemEntity;
import com.ldb.truck.Entity.Item.viewItemEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.ItemGroupHeader;
import com.ldb.truck.Model.ItemSearch.ViewItemInventoryReq;
import com.ldb.truck.Repository.ItemEntityRepository;
import com.ldb.truck.Repository.ViewItemEntityRepository;
import com.ldb.truck.Repository.testRepository.ItemListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ItemServiceImpl {
    @Autowired
    ViewItemEntityRepository viewItemEntityRepository;
    @Autowired
    ItemEntityRepository itemEntityRepository;
    @Autowired
    ItemListRepository itemListRepository;

    @Autowired
    ProfileDao profileDao;


    public DataResponse getViewItemInventory(
            ViewItemInventoryReq req,
            String userName,
            String role,
            String branchNo,
            String borNo) {

        DecimalFormat numfm = new DecimalFormat("###,###.###");
        double totalLak = 0, totalThb = 0, totalUsd = 0;

        DataResponse response = new DataResponse();
        List<viewItemEntity> rspList;

        try {
            if ("USERSTOCK".equals(role)
                    || "USER".equals(role)
                    || "AUTH".equals(role)) {

                rspList = viewItemEntityRepository.searchViewItems(
                        req.getItemId(),
                        borNo,
                        req.getKhId(),
                        req.getStartDate(),
                        req.getEndDate()
                );

            } else {
                rspList = viewItemEntityRepository.searchViewItems(
                        req.getItemId(),
                        req.getBorNo(),
                        req.getKhId(),
                        req.getStartDate(),
                        req.getEndDate()
                );
            }
            // ================= LOG RESULT SIZE =================
            log.info("Result size = {}", rspList.size());

            if (!rspList.isEmpty()) {
                for (viewItemEntity item : rspList) {
                    if ("LAK".equalsIgnoreCase(item.getCurrency())) {
                        totalLak += item.getTotalamt();
                    } else if ("THB".equalsIgnoreCase(item.getCurrency())) {
                        totalThb += item.getTotalamt();
                    } else if ("USD".equalsIgnoreCase(item.getCurrency())) {
                        totalUsd += item.getTotalamt();
                    }
                }

                ItemGroupHeader header = new ItemGroupHeader();
                header.setCalAmt(rspList.size());
                header.setCalLak(numfm.format(totalLak));
                header.setCalThb(numfm.format(totalThb));
                header.setCalUsd(numfm.format(totalUsd));

                response.setStatus("00");
                response.setMessage("Success");
                response.setGroupHeader(header);
                response.setDataResponse(rspList);
            } else {
                response.setStatus("05");
                response.setMessage("Data not Found !!");
            }
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error Data !!");
        }
        return response;
    }


//    public DataResponse getItemList(viewItemEntity viewItemEntity, String userName, String role, String branchNo, String borNo) {
//    log.info("role:" + role);
//    log.info("userName:" + userName);
//    log.info("branchNo:" + branchNo);
//    log.info("borNo:" + borNo);
//
//    DataResponse response = new DataResponse();
//    String khid = viewItemEntity.getKhid();
//
//    try {
//        List<viewItemEntity> items;
//
//        if ("PADMIN".equals(role)) {
//            if (viewItemEntity.getBorNo() != null && !viewItemEntity.getBorNo().trim().isEmpty()) {
//                items = viewItemEntityRepository.getAllViewItemsBorNo(viewItemEntity.getBorNo(), khid);
//            } else {
//                items = viewItemEntityRepository.getAllViewItemsAdmin();
//            }
//        } else {
//            items = viewItemEntityRepository.getAllViewItemsBranchNo(branchNo, borNo, khid);
//        }
//
//        List<ItemListModel> resultList = items.stream()
//                .map(item -> new ItemListModel(
//                        item.getItemId(),
//                        item.getItem_name(), // ✅ ใช้ camelCase
//                        item.getPrice(),
//                        item.getInqty(),
//                        item.getImage(),
//                        item.getBorNo(),
//                        item.getBorName(),
//                        item.getItemtype_Name(),
//                        item.getQty(),
//                        item.getUnit(),
//                        item.getSize(),
//                        item.getKhid(),
//                        item.getKhno(),
//                        item.getKhname()
//                ))
//                .collect(Collectors.toList());
//
//
//        response.setDataResponse(resultList);
//        response.setStatus("00");
//        response.setMessage("Success");
//
//    } catch (Exception e) {
//        response.setStatus("EE");
//        response.setMessage("Error Data !!");
//        e.printStackTrace();
//    }
//
//    return response;
//}
public DataResponse getItemList(listItemEntity listItemEntity, String userName, String role, String branchNo, String borNo) {
    log.info("role:" + role);
    log.info("userName:" + userName);
    log.info("branchNo:" + branchNo);
    log.info("borNo:" + borNo);

    DataResponse response = new DataResponse();
    String khid = listItemEntity.getKhid();

    try {
        List<ItemListView> items;

        if ("PADMIN".equals(role)) {
            if (listItemEntity.getBorNo() != null && !listItemEntity.getBorNo().trim().isEmpty()) {
                items = itemListRepository.getAllViewItemsBorNo(listItemEntity.getBorNo(), khid);
            } else {
                items = itemListRepository.getAllViewItemsAdmin();
            }
        } else {
            items = itemListRepository.getAllViewItemsBranchNo(branchNo, borNo, khid);
        }

        // map Projection → modelItemList
        List<modelItemList> resultList = items.stream()
                .map(item -> new modelItemList(
                        item.getItemId(),
                        item.getItemName(),
                        item.getUnit(),
                        item.getImage(),
                        item.getBorNo(),
                        item.getBorName(),
                        item.getSize(),
                        item.getQty(),
                        item.getKhid(),
                        item.getKhno(),
                        item.getKhname(),
                        item.getShopId(),
                        item.getShopName(),
                        item.getOrderType(),
                        item.getCurrency(),
                        item.getExchangeRate(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        response.setDataResponse(resultList);
        response.setStatus("00");
        response.setMessage("Success");

    } catch (Exception e) {
        response.setStatus("EE");
        response.setMessage("Error Data !!");
        e.printStackTrace();
    }

    return response;
}


    public DataResponse saveItem(ItemEntity viewItemEntity){
        log.info("show alert:"+viewItemEntity.getAlertqty());
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(itemEntityRepository.save(viewItemEntity));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("can't save data!!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");
        }
        return response;
 }

 public DataResponse updateItem(ItemEntity viewItemEntity){
        DataResponse response = new DataResponse();
        String url = "http://khounkham.com/images/image.jpg";
        try {
            //====check url path
                if(viewItemEntity.getImage().equals(url)){
                    response.setDataResponse(itemEntityRepository.updateItemNoImage(
                            viewItemEntity.getBrandId(),
                            viewItemEntity.getSupplierId(),
                            viewItemEntity.getItem_name(),
                            viewItemEntity.getUnit(),
                            viewItemEntity.getSize(),
                            viewItemEntity.getCurrency(),
                            viewItemEntity.getExchangeRate(),
                            viewItemEntity.getGalatyStartDate(),
                            viewItemEntity.getGalatyEndDate(),
                            viewItemEntity.getGalatyAmt(),
                            viewItemEntity.getQty(),
                            viewItemEntity.getPrice(),
                            viewItemEntity.getApproveBy(),
                            viewItemEntity.getApproveDate(),

                            viewItemEntity.getBarcode(),
                            viewItemEntity.getItemtypeid(),
                            viewItemEntity.getHouseid(),
                            viewItemEntity.getAlertqty(),
                            viewItemEntity.getOrderType(),
                            viewItemEntity.getItemId()));
                }else {
                    response.setDataResponse(itemEntityRepository.updateItem(
                            viewItemEntity.getBrandId(),
                            viewItemEntity.getSupplierId(),
                            viewItemEntity.getItem_name(),
                            viewItemEntity.getUnit(),
                            viewItemEntity.getSize(),
                            viewItemEntity.getCurrency(),
                            viewItemEntity.getExchangeRate(),
                            viewItemEntity.getGalatyStartDate(),
                            viewItemEntity.getGalatyEndDate(),
                            viewItemEntity.getGalatyAmt(),
                            viewItemEntity.getQty(),
                            viewItemEntity.getPrice(),
                            viewItemEntity.getImage(),
                            viewItemEntity.getApproveBy(),
                            viewItemEntity.getApproveDate(),

                            viewItemEntity.getBarcode(),
                            viewItemEntity.getItemtypeid(),
                            viewItemEntity.getHouseid(),
                            viewItemEntity.getAlertqty(),
                            viewItemEntity.getOrderType(),
                            viewItemEntity.getItemId()));
                }
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("can't save data!!");
            }s
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error updating item: " + e.getMessage(), e);
            response.setStatus("EE");
            response.setMessage("Error Data !! : " + e.getMessage());
        }
        return response;
            }
}
