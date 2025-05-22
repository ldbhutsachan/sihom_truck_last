//package com.ldb.truck.Service.OrderItemService;
//
//import com.ldb.truck.Entity.OrderItem.OrderItemEntity;
//import com.ldb.truck.Entity.Stock.*;
//import com.ldb.truck.Model.DataResponse;
//import com.ldb.truck.Repository.ItemEntityRepository;
//import com.ldb.truck.Repository.OrderDetailsRepository;
//import com.ldb.truck.Repository.OrderTxnEntityRepository;
//import org.jvnet.hk2.annotations.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//@Service
//
//public class OrderServiceImpl {
//    @Autowired ItemEntityRepository itemEntityRepository;
//    @Autowired OrderTxnEntityRepository orderTxnEntityRepository;
//    @Autowired OrderDetailsRepository orderDetailsRepository;
//    public DataResponse saveItemIn(OrderItemEntity stockItemDetailsEntity, String userId){
//        DataResponse response = new DataResponse();
//        try {
//            OrderItemEntity entity = getStockItemDetailsEntity(stockItemDetailsEntity, userId);
//            response.setDataResponse(orderTxnEntityRepository.save(entity));
//            if(response.getDataResponse() != null){
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
//    private static OrderItemEntity getStockItemDetailsEntity(OrderItemEntity stockItemDetailsEntity, String userId) {
//        OrderItemEntity entity = new OrderItemEntity();
//        entity.setBillNo(stockItemDetailsEntity.getBillNo());
//        entity.setBarcode(stockItemDetailsEntity.getBarcode());
//        entity.setItemId(stockItemDetailsEntity.getItemId());
//        entity.setUnit(stockItemDetailsEntity.getUnit());
//        entity.setSize(stockItemDetailsEntity.getSize());
//        entity.setCurrency(stockItemDetailsEntity.getCurrency());
//        entity.setExchangeRate(stockItemDetailsEntity.getExchangeRate());
//        entity.setQty(stockItemDetailsEntity.getQty());
//        entity.setPrice(stockItemDetailsEntity.getPrice());
//        entity.setSaveBy(userId);
//        entity.setSaveDate(new Date());
//        entity.setToKen(stockItemDetailsEntity.getToKen());
//        entity.setStatus("wait");
//        return entity;
//    }
//
//    public DataResponse updateItemIn(OrderItemEntity stockItemDetailsEntity,String userId){
//        DataResponse response = new DataResponse();
//        try {
//            response.setDataResponse(orderDetailsRepository.updateStockItemDetails(
//                    stockItemDetailsEntity.getBillNo(),
//                    stockItemDetailsEntity.getBarcode(),
//                    stockItemDetailsEntity.getItemId(),
//                    stockItemDetailsEntity.getUnit(),
//                    stockItemDetailsEntity.getSize(),
//                    stockItemDetailsEntity.getCurrency(),
//                    stockItemDetailsEntity.getExchangeRate(),
//                    stockItemDetailsEntity.getQty(),
//                    stockItemDetailsEntity.getPrice(),
//                    userId,
//                    new Date(),
//                    "wait",
//                    stockItemDetailsEntity.getDetailId()
//            ));
//            if(response.getDataResponse() != null){
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
//    @Transactional
//    public DataResponse approveStockItemDetails(StockItemDetailsReq stockItemDetailsReq) {
//        DataResponse response = new DataResponse();
//        String detailIdsStr = stockItemDetailsReq.getDetailId().stream()
//                .map(String::valueOf)
//                .collect(Collectors.joining(","));
//        try {
//            int updatedRows = orderDetailsRepository.approveStockItemDetails(
//                    stockItemDetailsReq.getUserId(),
//                    new Date(),
//                    "wait-order",
//                    detailIdsStr
//            );
//            if (updatedRows > 0) {
//                response.setStatus("00");
//                response.setMessage("Stock items approve successfully.");
//            } else {
//                response.setStatus("05");
//                response.setMessage("No stock items were approved.");
//            }
//        } catch (Exception e) {
//            response.setStatus("EE");
//            response.setMessage("Error while updating stock details.");
//        }
//        return response;
//    }
//
//    @Transactional
//    public DataResponse approveItemToStock(StockItemDetailsReq stockItemDetailsReq) {
//        DataResponse response = new DataResponse();
//        String detailIdsStr = stockItemDetailsReq.getDetailId().stream()
//                .map(String::valueOf)
//                .collect(Collectors.joining(","));
//        try {
//            int updatedRows = orderDetailsRepository.approveToStock(
//                    stockItemDetailsReq.getUserId(),
//                    new Date(),
//                    "ok",
//                    detailIdsStr
//            );
//            if (updatedRows > 0) {
//                updateItemAndUpTotal(detailIdsStr);
//                response.setStatus("00");
//                response.setMessage("Stock items approve successfully.");
//            } else {
//                response.setStatus("05");
//                response.setMessage("No stock items were approved.");
//            }
//        } catch (Exception e) {
//            response.setStatus("EE");
//            response.setMessage("Error while updating stock details.");
//        }
//        return response;
//    }
//
//    public int updateItemAndUpTotal(String itemId){
//        List<Long> itemIdList = Arrays.stream(itemId.split(","))
//                .map(Long::valueOf)
//                .collect(Collectors.toList());
//        List<OrderItemEntity> item = orderDetailsRepository.findByItemId(itemIdList);
//        if(item.isEmpty() || item.equals("") || item.equals(null)){
//            return 0;
//        }else {
//            for(OrderItemEntity stock : item){
//                Float unit = stock.getUnit();
//                Integer qty = stock.getQty();
//                Float price = stock.getPrice();
//                Integer itemNo = stock.getItemId();
//                itemEntityRepository.updateStockInItem(unit, qty, price, itemNo);
//            }
//        }
//        return 1;
//    }
//
//    public OrderItemDetailsRes getOrderItem(String billNo, String userName){
//        OrderItemDetailsRes response = new OrderItemDetailsRes();
//        List<OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
//        List<OrderItemEntity> listData = new ArrayList<>();
//        OrderItemHeader groupHeader = new OrderItemHeader();
//        try {
//            if(!"".equals(billNo)){
//                listData = orderTxnEntityRepository.getOrderByBillNo(userName,billNo);
//            }else {
//                listData = orderTxnEntityRepository.getOrderBySaveby(userName);
//            }
//            List<String> billNoList = listData.stream()
//                    .map(OrderItemEntity::getBillNo)
//                    .distinct()
//                    .collect(Collectors.toList());
//            for (String bill : billNoList){
//                groupHeader = new OrderItemHeader();
//                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getBillNo).findFirst().orElse(""));
//                groupHeader.setTxnDate(String.valueOf(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getSaveDate).findFirst()));
//                groupHeader.setQty(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getQty).collect(Collectors.summingInt(Integer::intValue)));
//                groupHeader.setAmount(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getPrice).collect(Collectors.summingDouble(Float::doubleValue)));
//                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getStatus).findFirst().orElse(""));
//                groupStockItemHeaders.add(groupHeader);
//
//                List<OrderItemEntity> groupListData = new ArrayList<>();
//                for(OrderItemEntity listStockTxn :  listData){
//                    if(listStockTxn.getBillNo().equals(bill)){
//                        groupListData.add(listStockTxn);
//                    }
//                    groupHeader.setDetails(groupListData);
//                }
//            }
//            response.setDataResponse(groupStockItemHeaders);
//            if(response.getDataResponse() != null){
//                response.setStatus("00");
//                response.setMessage("Success");
//            }else {
//                response.setStatus("05");
//                response.setMessage("Data not found");
//            }
//        }catch (Exception e){
//            response.setStatus("EE");
//            response.setMessage("Error Data");
//        }
//        return response;
//    }
//}
