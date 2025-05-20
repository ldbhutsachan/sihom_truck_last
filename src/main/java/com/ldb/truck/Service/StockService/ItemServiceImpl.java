package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.Item.ItemEntity;
import com.ldb.truck.Entity.Item.viewItemEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.ItemEntityRepository;
import com.ldb.truck.Repository.ViewItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl {
    @Autowired
    ViewItemEntityRepository viewItemEntityRepository;
    @Autowired
    ItemEntityRepository itemEntityRepository;
    public DataResponse getViewItemInventory(viewItemEntity viewItemEntity){
        DataResponse response = new DataResponse();
        try {
            if(viewItemEntity.getItemId() != null){
                response.setDataResponse(viewItemEntityRepository.getItemByItemId(viewItemEntity.getItemId()));
            }else {
                response.setDataResponse(viewItemEntityRepository.getAllViewItems());
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
