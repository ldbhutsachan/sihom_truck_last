package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.ItemType.ItemTypeEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeServiceImpl {
    @Autowired
    ItemTypeRepository repository;
    public DataResponse getItemType(ItemTypeEntity itemTypeEntity){
        DataResponse response= new DataResponse();
        try {
            if(!"".equals(itemTypeEntity.getItemtypeid())){
                response.setDataResponse(repository.getItemTypeById(itemTypeEntity.getItemtypeid()));
            }else {
                response.setDataResponse(repository.getItemTypeAll());
            }
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data Not Found !!!");
            }

        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");

        }
        return response;
    }

    public DataResponse saveItemType(ItemTypeEntity itemTypeEntity){
        DataResponse response= new DataResponse();
        try {
                response.setDataResponse(repository.save(itemTypeEntity));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save Data!!!");
            }

        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");

        }
        return response;
    }

    public DataResponse updateItemType(ItemTypeEntity itemTypeEntity){
        DataResponse response= new DataResponse();
        try {
            String itemType_Name = itemTypeEntity.getItemTypeName();
            String itemTypeid = itemTypeEntity.getItemtypeid();
            response.setDataResponse(repository.updateItemType(itemType_Name,itemTypeid));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save Data!!!");
            }

        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");

        }
        return response;
    }
    public DataResponse delItemType(ItemTypeEntity itemTypeEntity){
        DataResponse response= new DataResponse();
        try {
            String itemType_Name = itemTypeEntity.getItemTypeName();
            String itemTypeid = itemTypeEntity.getItemtypeid();
            response.setDataResponse(repository.delItemType(itemTypeid));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't del Data!!!");
            }

        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");

        }
        return response;
    }
}
