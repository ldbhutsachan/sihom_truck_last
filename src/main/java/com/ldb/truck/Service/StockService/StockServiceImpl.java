package com.ldb.truck.Service.StockService;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Entity.Item.ItemEntity;
import com.ldb.truck.Entity.Stock.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Pay.PrintBillPayment;
import com.ldb.truck.Model.Login.Performance.v_performance;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class StockServiceImpl {
    @Autowired
    ItemEntityRepository itemEntityRepository;
    @Autowired
    StockAlertRepository stockAlertRepository;
    @Autowired
    StockRepository repository;
    @Autowired
    StockDetailsRepository stockDetailsRepository;
    @Autowired
    ItemKeyRepository itemKeyRepository;
    @Autowired
    StockTxnEntityRepository stockTxnEntityRepository;

    public DataResponse saveStockIn(StockItemDetailsEntity stockItemDetailsEntity,String userId){
        DataResponse response = new DataResponse();
        try {
            StockItemDetailsEntity entity = getStockItemDetailsEntity(stockItemDetailsEntity, userId);
            response.setDataResponse(stockDetailsRepository.save(entity));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save stock Details");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Stock Data");
        }
        return response;
    }

    private static StockItemDetailsEntity getStockItemDetailsEntity(StockItemDetailsEntity stockItemDetailsEntity, String userId) {
        StockItemDetailsEntity entity = new StockItemDetailsEntity();
        entity.setBillNo(stockItemDetailsEntity.getBillNo());
        entity.setBarcode(stockItemDetailsEntity.getBarcode());
        entity.setItemId(stockItemDetailsEntity.getItemId());
        entity.setUnit(stockItemDetailsEntity.getUnit());
        entity.setSize(stockItemDetailsEntity.getSize());
        entity.setCurrency(stockItemDetailsEntity.getCurrency());
        entity.setExchangeRate(stockItemDetailsEntity.getExchangeRate());
        entity.setQty(stockItemDetailsEntity.getQty());
        entity.setPrice(stockItemDetailsEntity.getPrice());
        entity.setSaveBy(userId);
        entity.setSaveDate(new Date());
        entity.setToKen(stockItemDetailsEntity.getToKen());
        entity.setStatus("wait");
        return entity;
    }

    //=====edit txn
    public DataResponse updateStockIn(StockItemDetailsEntity stockItemDetailsEntity,String userId){
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(stockDetailsRepository.updateStockItemDetails(
                    stockItemDetailsEntity.getBillNo(),
                    stockItemDetailsEntity.getBarcode(),
                    stockItemDetailsEntity.getItemId(),
                    stockItemDetailsEntity.getUnit(),
                    stockItemDetailsEntity.getSize(),
                    stockItemDetailsEntity.getCurrency(),
                    stockItemDetailsEntity.getExchangeRate(),
                    stockItemDetailsEntity.getQty(),
                    stockItemDetailsEntity.getPrice(),
                    userId,
                    new Date(),
                    "wait",
                    stockItemDetailsEntity.getDetailId()
            ));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save stock Details");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Stock Data");
        }
        return response;
    }
    @Transactional
    public DataResponse approveStockItemDetails(StockItemDetailsReq stockItemDetailsReq) {
        DataResponse response = new DataResponse();
        String detailIdsStr = stockItemDetailsReq.getDetailId().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        try {
            int updatedRows = stockDetailsRepository.approveStockItemDetails(
                    stockItemDetailsReq.getUserId(),
                    new Date(),
                    "ok",
                    detailIdsStr
            );
            if (updatedRows > 0) {
                updateItemAndUpTotal(detailIdsStr);
                response.setStatus("00");
                response.setMessage("Stock items approve successfully.");
            } else {
                response.setStatus("05");
                response.setMessage("No stock items were approved.");
            }
        } catch (Exception e) {
            log.error("Error updating stock details: ", e);
            response.setStatus("EE");
            response.setMessage("Error while updating stock details.");
        }
        return response;
    }
    public int updateItemAndUpTotal(String itemId){
        log.info("show log:"+itemId);
        List<Long> itemIdList = Arrays.stream(itemId.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        List<StockItemDetailsEntity> item = stockDetailsRepository.findByItemId(itemIdList);
        if(item.isEmpty() || item.equals("") || item.equals(null)){
            return 0;
        }else {
            for(StockItemDetailsEntity stock : item){
                Float unit = stock.getUnit();
                Integer qty = stock.getQty();
                Float price = stock.getPrice();
                Integer itemNo = stock.getItemId();
                log.info("show unit:"+unit);
                    itemEntityRepository.updateStockInItem(unit, qty, price, itemNo);
            }
        }
        return 1;
    }
    public StockItemDetailsRes getVStockV2(String billNo, String role, String userName){
        StockItemDetailsRes response = new StockItemDetailsRes();
        List<GroupStockItemHeader> groupStockItemHeaders = new ArrayList<>();
        StockTxnEntity resDataGroup = new StockTxnEntity();
        List<StockTxnEntity> listData = new ArrayList<>();
        GroupStockItemHeader groupHeader = new GroupStockItemHeader();
        try {
            if(!"".equals(billNo)){
                listData = stockTxnEntityRepository.getStockByBillNo(userName,billNo);
            }else {
                listData = stockTxnEntityRepository.getStockBySaveby(userName);
            }
            List<String> billNoList = listData.stream()
                    .map(StockTxnEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new GroupStockItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getBillNo).findFirst().orElse(""));
                groupHeader.setTxnDate(String.valueOf(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getSaveDate).findFirst()));
                groupHeader.setQty(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getQty).collect(Collectors.summingDouble(Double::doubleValue)));
                groupHeader.setAmount(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getPrice).collect(Collectors.summingDouble(Double::doubleValue)));
                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);

                List<StockTxnEntity> groupListData = new ArrayList<>();
                for(StockTxnEntity listStockTxn :  listData){
                    if(listStockTxn.getBillNo().equals(bill)){
                        groupListData.add(listStockTxn);
                    }
                    groupHeader.setDetails(groupListData);
                }
            }
            response.setDataResponse(groupStockItemHeaders);
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not found");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data");
        }
        return response;
    }
    public StockItemDetailsRes getVStockAuth(String billNo, String role, String userName){
        StockItemDetailsRes response = new StockItemDetailsRes();
        List<GroupStockItemHeader> groupStockItemHeaders = new ArrayList<>();
        StockTxnEntity resDataGroup = new StockTxnEntity();
        List<StockTxnEntity> listData = new ArrayList<>();
        GroupStockItemHeader groupHeader = new GroupStockItemHeader();
        try {
                listData = stockTxnEntityRepository.getStockByBillNoAdmin();
            List<String> billNoList = listData.stream()
                    .map(StockTxnEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new GroupStockItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getBillNo).findFirst().orElse(""));
                groupHeader.setTxnDate(String.valueOf(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getSaveDate).findFirst()));
                groupHeader.setQty(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getQty).collect(Collectors.summingDouble(Double::doubleValue)));
                groupHeader.setAmount(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getPrice).collect(Collectors.summingDouble(Double::doubleValue)));
                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);

                List<StockTxnEntity> groupListData = new ArrayList<>();
                for(StockTxnEntity listStockTxn :  listData){
                    if(listStockTxn.getBillNo().equals(bill)){
                        groupListData.add(listStockTxn);
                    }
                    groupHeader.setDetails(groupListData);
                }
            }
            response.setDataResponse(groupStockItemHeaders);
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not found");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data");
        }
        return response;
    }

    public StockItemDetailsRes getVStockReport(StockRequest stockRequest, String userName){
        StockItemDetailsRes response = new StockItemDetailsRes();
        List<GroupStockItemHeader> groupStockItemHeaders = new ArrayList<>();
        StockTxnEntity resDataGroup = new StockTxnEntity();
        List<StockTxnEntity> listData = new ArrayList<>();
        GroupStockItemHeader groupHeader = new GroupStockItemHeader();
        try {
            String startDate = stockRequest.getStartDate();
            String endDate = stockRequest.getEndDate();
            String status = stockRequest.getStatus();
                if(!"ALL".equals(status)) {
                    listData = stockTxnEntityRepository.getStockReport(startDate, endDate, status);
                }else {
                    listData = stockTxnEntityRepository.getStockReportNoStatus(startDate, endDate);
                }
            List<String> billNoList = listData.stream()
                    .map(StockTxnEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new GroupStockItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getBillNo).findFirst().orElse(""));
                groupHeader.setTxnDate(String.valueOf(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getSaveDate).findFirst()));
                groupHeader.setQty(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getQty).collect(Collectors.summingDouble(Double::doubleValue)));
                groupHeader.setAmount(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getPrice).collect(Collectors.summingDouble(Double::doubleValue)));
                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);

                List<StockTxnEntity> groupListData = new ArrayList<>();
                for(StockTxnEntity listStockTxn :  listData){
                    if(listStockTxn.getBillNo().equals(bill)){
                        groupListData.add(listStockTxn);
                    }
                    groupHeader.setDetails(groupListData);
                }
            }
            response.setDataResponse(groupStockItemHeaders);
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not found");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data");
        }
        return response;
    }
    public DataResponse getAlertStock (){
        DataResponse dataResponse = new DataResponse();
        try {
            dataResponse.setDataResponse(stockAlertRepository.findAll());
            if(dataResponse.getDataResponse() != null){
                dataResponse.setStatus("00");
                dataResponse.setMessage("Success");
            }else {
                dataResponse.setStatus("05");
                dataResponse.setMessage("Data not found");
            }
        }catch (Exception e){
            dataResponse.setStatus("EE");
            dataResponse.setMessage("Error Data");
        }
        return dataResponse;
    }
}
