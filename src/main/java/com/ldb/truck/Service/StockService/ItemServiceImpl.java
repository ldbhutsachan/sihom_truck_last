package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.Item.ItemEntity;
import com.ldb.truck.Entity.Item.viewItemEntity;
import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.ItemGroupHeader;
import com.ldb.truck.Repository.ItemEntityRepository;
import com.ldb.truck.Repository.ViewItemEntityRepository;
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
    public DataResponse getViewItemInventory(viewItemEntity viewItemEntity,String userName,String role,String branchNo){
        log.info("role:"+role);
        log.info("userName:"+userName);
        log.info("branchNo:"+branchNo);
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        double totalLak = 0;
        double totalThb = 0;
        double totalUsd = 0;

        DataResponse response = new DataResponse();
        List<viewItemEntity> rspList = new ArrayList<>();
        ItemGroupHeader groupHeader = new ItemGroupHeader();
        try {
            if("USERSTOCK".equals(role) || "USER".equals(role) ||  "AUTH".equals(role)){
               rspList = viewItemEntityRepository.getAllViewItemsBranchNo(branchNo);
            }
            else if("PADMIN".equals(role)){
                rspList = viewItemEntityRepository.getAllViewItemsAdmin();
            }
            else {
                rspList = viewItemEntityRepository.getAllViewItemsAdmin();
            }
            if(rspList.size() >= 1 ){
                response.setStatus("00");
                response.setMessage("Success");
                int count = rspList.size();
                for (viewItemEntity item : rspList) {
                    double price = item.getTotalamt(); // Replace with your actual amount field
                    String currency = item.getCurrency();
                    if ("LAK".equalsIgnoreCase(currency)) {
                        totalLak += price;
                    } else if ("THB".equalsIgnoreCase(currency)) {
                        totalThb += price;
                    } else if ("USD".equalsIgnoreCase(currency)) {
                        totalUsd += price;
                    }
                }
                groupHeader.setCalAmt(count);
                groupHeader.setCalLak(numfm.format(totalLak));
                groupHeader.setCalThb(numfm.format(totalThb));
                groupHeader.setCalUsd(numfm.format(totalUsd));

                response.setGroupHeader(groupHeader);

                response.setDataResponse(rspList);
            }else {
                response.setStatus("05");
                response.setMessage("Data not Found !!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");
        }
        return response;
}
public DataResponse getItemList(viewItemEntity viewItemEntity,String userName,String role,String branchNo){
        log.info("role:"+role);
        log.info("userName:"+userName);
        log.info("branchNo:"+branchNo);
        DataResponse response = new DataResponse();
        try {
            if("PADMIN".equals(role)){
                response.setDataResponse(viewItemEntityRepository.getAllViewItemsAdmin());
            }else {
                response.setDataResponse(viewItemEntityRepository.getAllViewItemsBranchNo(branchNo));
            }

            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not Found !!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");
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
                            viewItemEntity.getBranchNo(),
                            viewItemEntity.getBarcode(),
                            viewItemEntity.getItemtypeid(),
                            viewItemEntity.getHouseid(),
                            viewItemEntity.getAlertqty(),
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
                            viewItemEntity.getBranchNo(),
                            viewItemEntity.getBarcode(),
                            viewItemEntity.getItemtypeid(),
                            viewItemEntity.getHouseid(),
                            viewItemEntity.getAlertqty(),
                            viewItemEntity.getItemId()));
                }
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
}
