package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.ItemType.ItemTypeEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ItemTypeServiceImpl {
    @Autowired
    ItemTypeRepository repository;
    public DataResponse getItemType(ItemTypeEntity itemTypeEntity, String role) {
        DataResponse response = new DataResponse();
        try {
            List<ItemTypeEntity> dataList;

            if (itemTypeEntity.getItemtypeid() != null) {
                // ค้นหาโดย id
                dataList = repository.getItemTypeById(itemTypeEntity.getItemtypeid());
            } else {
                // ตรวจ role เหมือน saveItemType
                // สร้าง Set ของ role ที่ต้องใช้ bansi_use = "1"
                Set<String> bansiRoles = Set.of("ACCOUNTANT", "ACCOUNTANTCHECK", "AUDITOR", "FOR_DOCUMENT_ADMIN", "FINANCE");

                if (bansiRoles.contains(role.toUpperCase())) {
                    // เฉพาะ role ในกลุ่มนี้ => bansi_use = "1"
                    dataList = repository.getItemTypeByBansiUse("1");
                } else {
                    // role อื่นๆ => bansi_use = "" หรือ null หรือ != "1"
                    dataList = repository.getItemTypeByNonBansiUse();
                }

            }

            response.setDataResponse(dataList);
            response.setStatus("00");
            response.setMessage("Success");
        } catch (Exception e) {
            e.printStackTrace();
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

    public DataResponse updateItemType(ItemTypeEntity itemTypeEntity) {
        DataResponse response = new DataResponse();
        try {
            Long itemTypeid = itemTypeEntity.getItemtypeid();
            String itemTypeName = itemTypeEntity.getItemTypeName();
            int updated = repository.updateItemType(itemTypeName, itemTypeid);
            response.setDataResponse(updated);
            if(updated > 0){
                response.setStatus("00");
                response.setMessage("Success");
            } else {
                response.setStatus("05");
                response.setMessage("No record updated!");
            }
        } catch (Exception e){
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error Data !!");
        }
        return response;
    }
    public DataResponse delItemType(ItemTypeEntity itemTypeEntity){
        DataResponse response = new DataResponse();
        try {
            Long itemTypeid = itemTypeEntity.getItemtypeid();
            int deleted = repository.delItemType(itemTypeid);

            response.setDataResponse(deleted);

            if(deleted > 0){
                response.setStatus("00");
                response.setMessage("Success");
            } else {
                response.setStatus("05");
                response.setMessage("Can't delete Data!!!");
            }

        } catch (Exception e){
            e.printStackTrace();
            response.setStatus("EE");
            response.setMessage("Error Data !!");
        }
        return response;
    }

}
