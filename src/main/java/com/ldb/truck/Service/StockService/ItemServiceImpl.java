package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.Item.ItemEntity;
import com.ldb.truck.Entity.Item.viewItemEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.ItemEntityRepository;
import com.ldb.truck.Repository.ViewItemEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        DataResponse response = new DataResponse();
        try {
            if("USER".equals(role)){
                response.setDataResponse(viewItemEntityRepository.getAllViewItemsUserId(userName));
            }
            else if("AUTH".equals(role)){
                response.setDataResponse(viewItemEntityRepository.getAllViewItemsBranchNo(branchNo));
            }
            else if("PADMIN".equals(role)){
                response.setDataResponse(viewItemEntityRepository.getAllViewItemsAdmin());
            }
            else {
                response.setDataResponse(null);
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
public DataResponse getItemList(viewItemEntity viewItemEntity,String userName,String role,String branchNo){
        log.info("role:"+role);
        log.info("userName:"+userName);
        log.info("branchNo:"+branchNo);
        DataResponse response = new DataResponse();
        try {
                response.setDataResponse(viewItemEntityRepository.getAllViewItemsBranchNo(branchNo));
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
