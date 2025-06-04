package com.ldb.truck.Service.StockService;

import com.ldb.truck.Entity.OrderItem.*;
import com.ldb.truck.Entity.RequestItem.*;
import com.ldb.truck.Entity.Stock.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class StockServiceImpl {
    @Autowired
    RequestItemEntityRepository requestItemEntityRepository;
    @Autowired
    OrderItemTxnEntityRepository orderItemTxnEntityRepository;
    @Autowired
    RequestGenKeyRepository requestGenKeyRepository;
    @Autowired
    OrderGenKeyRepository orderGenKeyRepository;
    @Autowired
    RequestItemRepository requestItemRepository;
    @Autowired
    RequestTxnRepository requestTxnRepository;
    @Autowired ItemEntityRepository itemEntityRepository;
    @Autowired OrderTxnEntityRepository orderTxnEntityRepository;
    @Autowired OrderItemSaveEntityRepository orderItemSaveEntityRepository;
    @Autowired OrderDetailsRepository orderDetailsRepository;
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
               int i =  updateItemAndUpTotal(detailIdsStr);

                response.setStatus("00");
                response.setMessage("Stock items approve successfully.");
            } else {
                response.setStatus("00");
                response.setMessage("No stock items were approved !!!");
            }
        } catch (Exception e) {
            log.error("Error updating stock details: ", e);
            response.setStatus("00");
            response.setMessage("Error while updating stock details !!!");
        }
        return response;
    }
    public int updateItemAndUpTotal(String itemId){

        List<Long> itemIdList = Arrays.stream(itemId.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        List<StockItemDetailsEntity> item = stockDetailsRepository.findByItemId(itemIdList);
        if(item.isEmpty() || item.equals("") || item.equals(null)){
            return 0;
        }else {
            for(StockItemDetailsEntity stock : item){
                Integer qty = stock.getQty();
                Float price = stock.getPrice();
                Integer itemNo = stock.getItemId();
                    itemEntityRepository.updateStockInItem(qty, itemNo);
            }
            return 1;
        }
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

        public OrderItemDetailsRes getOrderItem(String billNo, String userName){
        OrderItemDetailsRes response = new OrderItemDetailsRes();
        List<OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderItemEntity> listData = new ArrayList<>();
        OrderItemHeader groupHeader = new OrderItemHeader();
        try {
            if(!"".equals(billNo)){
                listData = orderTxnEntityRepository.getOrderByBillNo(userName,billNo);
            }else {
                listData = orderTxnEntityRepository.getOrderBySaveby(userName);
            }
            List<String> billNoList = listData.stream()
                    .map(OrderItemEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new OrderItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(OrderItemEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));
                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                groupHeader.setAmount(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue)));
                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);

                List<OrderItemEntity> groupListData = new ArrayList<>();
                for(OrderItemEntity listStockTxn :  listData){
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

    ///-----
    public DataResponse saveItemIn(OrderRequest stockItemDetailsEntity, String userId) {
        DataResponse response = new DataResponse();

        try {
            List<OrderItemReportEntity> entities = convertToOrderItemEntities(stockItemDetailsEntity, userId);
            List<OrderItemReportEntity> savedEntities = new ArrayList<>();
            orderItemSaveEntityRepository.saveAll(entities).forEach(savedEntities::add);
            if (!savedEntities.isEmpty()) {
                OrderItemTxnEntity requestEntity = new OrderItemTxnEntity();
                requestEntity.setBillNo(stockItemDetailsEntity.getBillNo());
                requestEntity.setSaveDate(new Date());
                requestEntity.setSaveBy(userId);
                orderItemTxnEntityRepository.save(requestEntity);
                response.setDataResponse(savedEntities);
                response.setStatus("00");
                response.setMessage("Success");
            } else {
                response.setStatus("05");
                response.setMessage("Can't Save order Details");
            }

        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error order Data: " + e.getMessage());
        }

        return response;
    }
    private List<RequestItemEbtity> convertToRequest(RequestItems request, String userId) {
        List<RequestItemEbtity> entities = new ArrayList<>();

        for (RequestItem item : request.getItemId()) {
            RequestItemEbtity entity = new RequestItemEbtity();
            entity.setBillNo(request.getBillNo());
            entity.setItemId(item.getItem());
            entity.setHeaderNo(item.getHeaderNo());
            entity.setFooterNo(item.getFooterNo());
            entity.setQty(item.getQty());
            entity.setSaveBy(userId);
            entity.setSaveDate(new Date());
            entity.setStatus("wait"); // Example default status
            entities.add(entity);
        }

        return entities;
    }
    private List<OrderItemReportEntity> convertToOrderItemEntities(OrderRequest request, String userId) {
        List<OrderItemReportEntity> entities = new ArrayList<>();

        for (OrderReqItem item : request.getItemId()) {
            OrderItemReportEntity entity = new OrderItemReportEntity();
            entity.setBillNo(request.getBillNo());
            entity.setItemId(item.getItem());
            entity.setQty(item.getQty());
            entity.setSaveBy(userId);
            entity.setSaveDate(new Date());
            entity.setStatus("wait"); // Example default status
            entities.add(entity);
        }

        return entities;
    }
    private static OrderItemEntity getStockItemDetailsEntity(OrderItemEntity stockItemDetailsEntity, String userId) {
        OrderItemEntity entity = new OrderItemEntity();
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

    public DataResponse updateOrderItemIn(OrderItemReportEntity stockItemDetailsEntity,String userId){
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(orderDetailsRepository.updateStockItemDetails(
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
    public DataResponse approveStockItemDetailsOrderProd(StockItemDetailsReq stockItemDetailsReq) {
        DataResponse response = new DataResponse();
        String detailIdsStr = stockItemDetailsReq.getDetailId().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        try {
            int updatedRows = orderDetailsRepository.approveStockItemDetails(
                    stockItemDetailsReq.getUserId(),
                    new Date(),
                    "wait-order",
                    detailIdsStr
            );
            if (updatedRows > 0) {
                response.setStatus("00");
                response.setMessage("Stock items approve successfully.");
            } else {
                response.setStatus("05");
                response.setMessage("No stock items were approved.");
            }
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error while updating stock details.");
        }
        return response;
    }

    @Transactional
    public DataResponse approveItemToStock(StockItemDetailsReq stockItemDetailsReq) {
        DataResponse response = new DataResponse();
        String detailIdsStr = stockItemDetailsReq.getDetailId().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        try {
            int updatedRows = orderDetailsRepository.approveToStock(
                    stockItemDetailsReq.getUserId(),
                    new Date(),
                    "ok",
                    detailIdsStr
            );

            if (updatedRows > 0) {
                updateItemAndUpTotalOrder(detailIdsStr);
                log.info("start show 1");
                response.setStatus("00");
                response.setMessage("Stock items approve successfully.");
            } else {
                response.setStatus("00");
                response.setMessage("No stock items were approved.");
            }
        } catch (Exception e) {
            response.setStatus("00");
            response.setMessage("Error while updating stock details.");
        }
        return response;
    }
    public void updateItemInTableItem(String itemId) {
        // Convert itemId string to a list of Long values
        List<Long> itemIdList = Arrays.stream(itemId.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        // Retrieve items from repository
        List<RequestItemEbtity> items = requestItemRepository.findByItemId(itemIdList);

        // Check if items list is null or empty
        if (items == null || items.isEmpty()) {
            log.warn("No items found for itemId: " + itemId);
            return;
        }

        // Log the first item for debugging purposes
        log.info("First item in list: " + items.get(0).getItemId());

        // Process and update items
        for (RequestItemEbtity stock : items) {
            Integer qty = stock.getQty();
            Integer itemNo = stock.getItemId();
            // Logging details before updating
            log.info("Processing item out: " + itemNo + ", Quantity: " + qty);
            // Perform database update
            itemEntityRepository.updateStockInItemOut(qty, itemNo);
        }
    }

    public void updateItemAndUpTotalOrder(String itemId) {
        // Convert itemId string to a list of Long values
        List<Long> itemIdList = Arrays.stream(itemId.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        // Retrieve items from repository
        List<OrderItemReportEntity> items = orderDetailsRepository.findByItemId(itemIdList);

        // Check if items list is null or empty
        if (items == null || items.isEmpty()) {
            log.warn("No items found for itemId: " + itemId);
            return;
        }

        // Log the first item for debugging purposes
        log.info("First item in list: " + items.get(0).getItemId());

        // Process and update items
        for (OrderItemReportEntity stock : items) {
            Integer qty = stock.getQty();
            Integer itemNo = stock.getItemId();

            // Logging details before updating
            log.info("Processing item: " + itemNo + ", Quantity: " + qty);

            // Perform database update
            itemEntityRepository.updateStockInItem(qty, itemNo);
        }
    }
    //getOrderItemDetailsAuth
    public OrderItemDetailsRes getOrderItemAuth(String billNo, String role, String userName){
        OrderItemDetailsRes response = new OrderItemDetailsRes();
        List<OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderItemEntity> listData = new ArrayList<>();
        OrderItemHeader groupHeader = new OrderItemHeader();
        try {
            listData = orderTxnEntityRepository.getOrderByAdmin();
            log.info("show data :"+listData.get(0).getItemId());
            List<String> billNoList = listData.stream()
                    .map(OrderItemEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new OrderItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(OrderItemEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                groupHeader.setAmount(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue)));
                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);
                List<OrderItemEntity> groupListData = new ArrayList<>();
                for(OrderItemEntity listStockTxn :  listData){
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
                response.setStatus("00");
                response.setMessage("Data not found");
            }
        }catch (Exception e){
            response.setStatus("00");
            response.setMessage("Data not found");
        }
        return response;
    }

    public OrderItemDetailsRes getReportOrderItem(StockRequest stockRequest, String userName){
        OrderItemDetailsRes response = new OrderItemDetailsRes();
        List<OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderItemEntity> listData = new ArrayList<>();
        OrderItemHeader groupHeader = new OrderItemHeader();
        try {
            String startDate = stockRequest.getStartDate();
            String endDate = stockRequest.getEndDate();
            String status = stockRequest.getStatus();
            if(!"ALL".equals(status)) {
                listData = orderTxnEntityRepository.getOrderReport(startDate, endDate, status);
            }else {
                listData = orderTxnEntityRepository.getOrderReportNoStatus(startDate, endDate);
            }
            List<String> billNoList = listData.stream()
                    .map(OrderItemEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(OrderItemEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                groupHeader.setAmount(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue)));
                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);
                List<OrderItemEntity> groupListData = new ArrayList<>();
                for(OrderItemEntity listStockTxn :  listData){
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


    //*****request data from branch
//    public DataResponse saveRequestItem(RequestItems stockItemDetailsEntity, String userId){
//        DataResponse response = new DataResponse();
//        try {
//            RequestItemEbtity entity = getRequestItemEntity(stockItemDetailsEntity, userId);
//            response.setDataResponse(requestItemRepository.save(entity));
//            if(response.getDataResponse() != null){
//                //====if data store then insert data to request item as below first
//                RequestItemEntity requestEntity = new RequestItemEntity();
//                requestEntity.setBillNo(stockItemDetailsEntity.getBillNo());
//                requestEntity.setSaveDate(stockItemDetailsEntity.getSaveDate());
//                requestEntity.setSaveBy(stockItemDetailsEntity.getSaveBy());
//                requestItemEntityRepository.save(requestEntity);
//                response.setStatus("00");
//                response.setMessage("Success");
//            }else {
//                response.setStatus("05");
//                response.setMessage("Can't Save stock Details");
//            }
//        }catch (Exception e){
//            response.setStatus("EE");
//            response.setMessage("Error Stock Data");
//        }
//        return response;
//    }
    public DataResponse saveRequestItem(RequestItems stockItemDetailsEntity, String userId) {
        DataResponse response = new DataResponse();
        try {
            List<RequestItemEbtity> entities = convertToRequest(stockItemDetailsEntity, userId);
            List<RequestItemEbtity> savedEntities = new ArrayList<>();
            requestItemRepository.saveAll(entities).forEach(savedEntities::add);
            if (!savedEntities.isEmpty()) {
                RequestItemEntity requestEntity = new RequestItemEntity();
                requestEntity.setBillNo(stockItemDetailsEntity.getBillNo());
                requestEntity.setSaveDate(new Date());
                requestEntity.setSaveBy(userId);
                requestItemEntityRepository.save(requestEntity);
                response.setDataResponse(savedEntities);
                response.setStatus("00");
                response.setMessage("Success");
            } else {
                response.setStatus("00");
                response.setMessage("Can't Save order Details");
            }

        } catch (Exception e) {
            response.setStatus("00");
            response.setMessage("Error order Data: " + e.getMessage());
        }

        return response;
    }
    private static RequestItemEbtity getRequestItemEntity(RequestItemEbtity stockItemDetailsEntity, String userId) {
        RequestItemEbtity entity = new RequestItemEbtity();
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
        entity.setFooterNo(stockItemDetailsEntity.getFooterNo());
        entity.setHeaderNo(stockItemDetailsEntity.getHeaderNo());
        entity.setNote(stockItemDetailsEntity.getNote());
        entity.setStatus("wait");
        return entity;
    }

    public DataResponse updateRequestItem(RequestItemEbtity stockItemDetailsEntity,String userId){
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(requestItemRepository.updateStockItemDetails(
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
                    stockItemDetailsEntity.getHeaderNo(),
                    stockItemDetailsEntity.getFooterNo(),
                    stockItemDetailsEntity.getNote(),
                    stockItemDetailsEntity.getDetailId()
            ));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Can't Save request item Details");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error request item Data");
        }
        return response;
    }

    @Transactional
    public DataResponse approveRequestItem(StockItemDetailsReq stockItemDetailsReq) {
        log.info("show status:"+stockItemDetailsReq.getStatus());
        DataResponse response = new DataResponse();
        String detailIdsStr = stockItemDetailsReq.getDetailId().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        try {
            //=====then
            int updatedRows = requestItemRepository.approveRequestItem(
                    stockItemDetailsReq.getUserId(),
                    new Date(),
                    stockItemDetailsReq.getStatus(),
                    detailIdsStr
            );
            if (updatedRows > 0) {
                if(stockItemDetailsReq.getStatus().equals("ok")){
                    log.info("=====start update item inventory=====");
                    updateItemInTableItem(detailIdsStr);
                }
                response.setStatus("00");
                response.setMessage("request items approve successfully.");
            } else {
                response.setStatus("05");
                response.setMessage("No request items were approved.");
            }
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error while updating request details.");
        }
        return response;
    }
    public DataResponse getRequestKey(){
        //RequestGenKeyRepository
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(requestGenKeyRepository.findAll());
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
public DataResponse checkKeyOrder(){
        //RequestGenKeyRepository
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(orderGenKeyRepository.findAll());
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

    //save request item when have insert data

    public RequestItemDetailsRes getRequestItem(String billNo, String role, String userName, String status){
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        RequestItemDetailsRes response = new RequestItemDetailsRes();
        List<RequestItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<RequestTxnEntity> listData = new ArrayList<>();
        RequestItemHeader groupHeader = new RequestItemHeader();
        try {
            //if("ADMIN".equals(role)){
                listData = requestTxnRepository.getStockByBillNoAdmin(status);
           // }else {
            //    listData = requestTxnRepository.getStockByBillByUser(userName,status);
            //}
            List<String> billNoList = listData.stream()
                    .map(RequestTxnEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new RequestItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(RequestTxnEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                //numfm
                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue));

                groupHeader.setAmount(numfm.format(total));
                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);
                List<RequestTxnEntity> groupListData = new ArrayList<>();
                for(RequestTxnEntity listStockTxn :  listData){
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

    public RequestItemDetailsRes getRequestItemReport(StockRequest stockRequest, String userName,String role){
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        RequestItemDetailsRes response = new RequestItemDetailsRes();
        List<RequestItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<RequestTxnEntity> listData = new ArrayList<>();
        RequestItemHeader groupHeader = new RequestItemHeader();
        try {
            String startDate = stockRequest.getStartDate();
            String endDate = stockRequest.getEndDate();
            String status = stockRequest.getStatus();
            if(!"ALL".equals(status)) {
                listData = requestTxnRepository.getRequestReport(startDate, endDate, status);
            }else {
                listData = requestTxnRepository.getRequestReportNoStatus(startDate, endDate);
            }
            List<String> billNoList = listData.stream()
                    .map(RequestTxnEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new RequestItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(RequestTxnEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue));

                groupHeader.setAmount(numfm.format(total));

                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);
                List<RequestTxnEntity> groupListData = new ArrayList<>();
                for(RequestTxnEntity listStockTxn :  listData){
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
}
