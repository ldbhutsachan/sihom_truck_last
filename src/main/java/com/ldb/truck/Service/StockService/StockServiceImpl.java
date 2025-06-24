package com.ldb.truck.Service.StockService;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Entity.Bor.BorEntity;
import com.ldb.truck.Entity.Bor.BorEntityReq;
import com.ldb.truck.Entity.Bor.BorEntityReqSave;
import com.ldb.truck.Entity.ItemPayment.*;
import com.ldb.truck.Entity.OrderItem.*;
import com.ldb.truck.Entity.RequestItem.*;
import com.ldb.truck.Entity.Stock.*;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.*;
import com.ldb.truck.Repository.Payment.ItemDetailsEntityRepository;
import com.ldb.truck.Repository.Payment.ItemPaymentEntityRepository;
import com.ldb.truck.Repository.Payment.PaymentDetailsEntityRepository;
import com.ldb.truck.Repository.Payment.VCalOrderEntityRepository;
import com.ldb.truck.Repository.RequestItem.RequestItemTypeRepository;
import com.ldb.truck.Entity.RequestItem.requestData;
import com.ldb.truck.Repository.RequestItem.requestItemTypeBorNameEntityRepository;
import com.ldb.truck.Service.customer.CustomerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Slf4j
@Service
public class StockServiceImpl {
    @Value("${info.logo}")
    private String logo;

    @Value("${info.title}")
    private String title;

    @Value("${info.address}")
    private String address;

    @Value("${info.contract}")
    private String contract;

    @Value("${info.email}")
    private String email;

    @Value("${info.location}")
    private String location;
    @Autowired
    ItemDetailsEntityRepository itemDetailsEntityRepository;
    @Autowired
    PaymentDetailsEntityRepository paymentDetailsEntityRepository;
    @Autowired
    InvoiceKeyRepository invoiceKeyRepository;
    @Autowired
    ItemPaymentEntityRepository itemPaymentEntityRepository;
    @Autowired
    ItemPaymentViewEntityRepository itemPaymentViewEntityRepository;
    @Autowired
    BorEntityRepository borEntityRepository;
    @Autowired
    RequestItemEntityRepository requestItemEntityRepository;
    @Autowired
    OrderItemTxnEntityRepository orderItemTxnEntityRepository;
    @Autowired
    RequestItemTypeRepository requestItemTypeRepository;
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
@Autowired
    BorRepository view_borRepository;
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
        DecimalFormat numfm = new DecimalFormat("###,###.###");
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

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(StockTxnEntity::getSaveDate)
                        .findFirst();
                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());

                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getCaltotal).collect(Collectors.summingDouble(Float::doubleValue));

                groupHeader.setAmount(numfm.format(total));

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
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        StockItemDetailsRes response = new StockItemDetailsRes();
        response.setLogo(logo);
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

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a"); // Format with AM/PM
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(StockTxnEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());

                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getCaltotal).collect(Collectors.summingDouble(Float::doubleValue));
                groupHeader.setAmount(numfm.format(total));

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
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        StockItemDetailsRes response = new StockItemDetailsRes();

        response.setLogo(logo);
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

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a"); // Format with AM/PM
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(StockTxnEntity::getSaveDate)
                        .findFirst();
                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(StockTxnEntity::getCaltotal).collect(Collectors.summingDouble(Float::doubleValue));
                groupHeader.setAmount(numfm.format(total));

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
    public DataResponse getAlertStock (AlertReq alertReq){
        String branchNo = alertReq.getBranchNo();
        String role = alertReq.getRole();
        log.info("role:"+role);
        log.info("branchNo:"+branchNo);
        DataResponse dataResponse = new DataResponse();
        try {
            if(role.equals("USERSTOCK")){
                dataResponse.setDataResponse(stockAlertRepository.getAlertByBranchNo(branchNo));
            }
            else if(role.equals("AUTH")){
                dataResponse.setDataResponse(stockAlertRepository.getAlertByBranchNo(branchNo));
            }else {
                dataResponse.setDataResponse(stockAlertRepository.findAll());
           }
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

   public OrderItemDetailsRes getOrderItem(String billNo, String userId){
        log.info("userId:"+userId);
        log.info("billNo:"+billNo);
            DecimalFormat numfm = new DecimalFormat("###,###.###");
        OrderItemDetailsRes response = new OrderItemDetailsRes();
        List<OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderItemEntity> listData = new ArrayList<>();
        OrderItemHeader groupHeader = new OrderItemHeader();
        try {
            if(!"".equals(billNo)){
                listData = orderTxnEntityRepository.getOrderByBillNo(userId,billNo);
            }else {
                listData = orderTxnEntityRepository.getOrderBySaveby(userId);
            }
            List<String> billNoList = listData.stream()
                    .map(OrderItemEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new OrderItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a"); // Format with AM/PM
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(OrderItemEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));
                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue));
                groupHeader.setAmount(numfm.format(total));

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
            entity.setBorNo(item.getBorNo());
            entity.setType(item.getType());
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

    @Autowired
    VCalOrderEntityRepository vCalOrderEntityRepository;
    @Transactional
    public DataResponse approveStockItemDetailsOrderProd(StockItemDetailsReq stockItemDetailsReq) {
        String role = stockItemDetailsReq.getRole();
        log.info("role:"+role);
        DataResponse response = new DataResponse();
        String detailIdsStr = stockItemDetailsReq.getDetailId().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        try {
            //======check role call to this first =======
            if("USER".equals(role)){
                response.setStatus("00");
                response.setMessage("ທ່ານ ບໍ່ມີສິດອະນຸມັດລາຍການ !!!");
            }else {
                int updatedRows = 0;
                String status = "";
                if ("AUTH".equals(role)) {
                    status = "auth";
                } else if ("BUYER".equals(role)) {
                    status = "buyer";
                } else if ("ACCOUNTING".equals(role)) {
                    status = "wait-item";
                }
                if ("AUTH".equals(role)) {
                    updatedRows = orderDetailsRepository.approveStockItemDetailsAuth(
                            stockItemDetailsReq.getUserId(),
                            new Date(),
                            status,
                            stockItemDetailsReq.getBillNo(),
                            detailIdsStr
                    );
                } else if ("BUYER".equals(role)) {
                    updatedRows = orderDetailsRepository.approveStockItemDetailsBuyer(
                            stockItemDetailsReq.getUserId(),
                            new Date(),
                            status,
                            stockItemDetailsReq.getBillNo(),
                            detailIdsStr
                    );
                } else if ("ACCOUNTING".equals(role)) {
                    updatedRows = orderDetailsRepository.approveStockItemDetailsAccounting(
                            stockItemDetailsReq.getUserId(),
                            new Date(),
                            status,
                            stockItemDetailsReq.getBillNo(),
                            detailIdsStr
                    );
                    //=====if accounting approve txn then auto save payment
                    //check total amount first
                    List<VCalOrderEntity> getCal = vCalOrderEntityRepository.getVCalOrderEntity(stockItemDetailsReq.getBillNo());
                    log.info("show check getCal size :"+getCal.size());
                    Optional<String> optionalKeyNo = invoiceKeyRepository.getMaxStockId();
                    String keyNo = optionalKeyNo.map(String::valueOf).orElse(null);
                    log.info("keyNo:"+keyNo);
                    ItemPaymentEntity entity = new ItemPaymentEntity();
                        entity.setSavebBy(stockItemDetailsReq.getUserId());
                        entity.setSaveDate(new Date());
                        entity.setBillNo(stockItemDetailsReq.getBillNo());
                        entity.setInvoiceNo(keyNo);
                        entity.setCcy("LAK");
                        entity.setStatus("wait-payment");
                        entity.setQty(getCal.get(0).getQty());
                        entity.setTotal(getCal.get(0).getPaymentTotal());
                        //total
                    itemPaymentEntityRepository.save(entity);

                    //then store data to payment entity
                    log.info("===let save payment details===");
                    PaymentDetailsEntity itemPaymentEntity = new PaymentDetailsEntity();
                    itemPaymentEntity.setSaveby(stockItemDetailsReq.getUserId());
                    itemPaymentEntity.setSaveDate(new Date());
                    itemPaymentEntity.setInvoiceNo(keyNo);
                    itemPaymentEntity.setRexchangeRate(1.0f);
                    itemPaymentEntity.setType("cash");
                    itemPaymentEntity.setCcy("LAK");
                    itemPaymentEntity.setAmount(0.0f);
                    itemPaymentEntity.setPaymentType("LDBBANK");
                    itemPaymentEntity.setExp(new Date());
                    itemPaymentEntity.setStatus("suspend");
                    paymentDetailsEntityRepository.save(itemPaymentEntity);
                }
                if (updatedRows > 0) {
                    response.setStatus("00");
                    response.setMessage("ທ່ານອະນຸມັດລາຍການສັ່ງຊື້ອາໄລ ສໍາເລັດ. ");
                } else {
                    response.setStatus("00");
                    response.setMessage("No stock items were approved.");
                }
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
    @Autowired
    OrderAuthEntityRepository orderAuthEntityRepository;
    public OrderAuthResponse getOrderItemAuth(String billNo, String role, String userId,String branchNo,String status){
        log.info("billNo:"+billNo);
        log.info("userId:"+userId);
        log.info("role:"+role);
        log.info("branchNo:"+branchNo);
        log.info("status:"+status);
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        OrderAuthResponse response = new OrderAuthResponse();
        List<OrderAuthHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderAuthEntity> listData = new ArrayList<>();
        OrderAuthHeader groupHeader = new OrderAuthHeader();
        try {
            if("PADMIN".equals(role)){
                    listData =orderAuthEntityRepository.getOrderByAdmin();
            }
            else if("AUTH".equals(role)){
                    listData =orderAuthEntityRepository.getOrderAuthByBranchNo(branchNo);
            }
            else if("BUYER".equals(role)){
                listData =orderAuthEntityRepository.getOrderAuthByBuyer();
            }
            else if("ACCOUNTING".equals(role)){
                listData =orderAuthEntityRepository.getOrderAuthByAccounting();
            }else {
                response.setStatus("00");
                response.setMessage("ທ່ານ ບໍ່ມີສິດເຂົ້າເບີ່ງ !!!");
            }
            List<String> billNoList = listData.stream()
                    .map(OrderAuthEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new OrderAuthHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderAuthEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  hh:mm a"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(OrderAuthEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderAuthEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue));
                groupHeader.setAmount(numfm.format(total));

                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderAuthEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);
                List<OrderAuthEntity> groupListData = new ArrayList<>();
                for(OrderAuthEntity listStockTxn :  listData){
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
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        OrderItemDetailsRes response = new OrderItemDetailsRes();
        List<OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderItemEntity> listData = new ArrayList<>();
        OrderItemHeader groupHeader = new OrderItemHeader();
        try {
            String startDate = stockRequest.getStartDate();
            String endDate = stockRequest.getEndDate();
            String status = stockRequest.getStatus();
            if(!"ALL".equals(status)) {
                log.info("show 1");
                listData = orderTxnEntityRepository.getOrderReport(startDate, endDate, status);
            }else {
                log.info("show 2");
                listData = orderTxnEntityRepository.getOrderReportNoStatus(startDate, endDate);
            }
            List<String> billNoList = listData.stream()
                    .map(OrderItemEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .map(OrderItemEntity::getSaveDate)
                        .findFirst();

                groupHeader.setTxnDate(optionalDate.map(formatter::format).orElse("No Date Found"));

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getBillNo().equals(bill))
                        .count());
                Double total = listData.stream().filter(p -> p.getBillNo().equals(bill)).map(OrderItemEntity::getTotal).collect(Collectors.summingDouble(Float::doubleValue));
                groupHeader.setAmount(numfm.format(total));
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
        entity.setBorNo(stockItemDetailsEntity.getBorNo());
        entity.setType(stockItemDetailsEntity.getType());
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
                    stockItemDetailsEntity.getType(),
                    stockItemDetailsEntity.getBorNo(),
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
            if("all".equals(status)){
                listData = requestTxnRepository.getStockByBillNoAdminAll();
            }else {
                listData = requestTxnRepository.getStockByBillNoAdmin(status);
            }
            List<String> billNoList = listData.stream()
                    .map(RequestTxnEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new RequestItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  hh:mm a"); // Desired format
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
                log.info("show 01");
                listData = requestTxnRepository.getRequestReport(startDate, endDate, status);
            }else {
                log.info("show 02");
                listData = requestTxnRepository.getRequestReportNoStatus(startDate, endDate);
            }
            List<String> billNoList = listData.stream()
                    .map(RequestTxnEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new RequestItemHeader();
                groupHeader.setBillNo(listData.stream().filter(p -> p.getBillNo().equals(bill)).map(RequestTxnEntity::getBillNo).findFirst().orElse(""));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  hh:mm a"); // Desired format
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


    //======make bor start =====
    //view_borRepository

    public DataResponse getBorAll( BorEntityReq borEntityReq){
        String keyId = borEntityReq.getKeyId();
        log.info("role start:"+keyId);
        DataResponse response = new DataResponse();
       try {
           if(keyId.isEmpty() || keyId.equals("all") || keyId.equals("all")){
               response.setDataResponse(view_borRepository.getBorViewEntityAll());
           }else {
               response.setDataResponse(view_borRepository.findBykeyId(keyId));
           }
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
    public DataResponse saveBoEntity(BorEntityReqSave borEntity, String userId) {
        DataResponse response = new DataResponse();
        try {
        BorEntity entity = getMapBor(borEntity,userId);
        response.setDataResponse(borEntityRepository.save(entity));
        if(response.getDataResponse() != null){
            response.setStatus("00");
            response.setMessage("ສໍາເລັດ");
        }else {
            response.setStatus("05");
            response.setMessage("ບໍ່ສາມາດບັນທຶກບໍ່ເເຫ່ໄດ້");
        }
    }catch (Exception e){
        response.setStatus("EE");
        response.setMessage("Error Data");
    }
        return response;
    }
    public DataResponse updateBoEntity(BorEntityReqSave borEntity, String userId) {

        DataResponse response = new DataResponse();
        try {
        response.setDataResponse(borEntityRepository.updateBorEntity(
                borEntity.getKeyId(),
                borEntity.getBname(),
                borEntity.getBtel(),
                borEntity.getBlocation(),
                borEntity.getEmail(),
                borEntity.getUserId(),
                new Date(),
                borEntity.getSortName(),
                borEntity.getBrandNo(),
                borEntity.getStatus()));
        if(response.getDataResponse() != null){
            response.setStatus("00");
            response.setMessage("ສໍາເລັດ");
        }else {
            response.setStatus("05");
            response.setMessage("ບໍ່ສາມາດບັນທຶກບໍ່ເເຫ່ໄດ້");
        }
    }catch (Exception e){
        response.setStatus("EE");
        response.setMessage("Error Data");
    }
        return response;
    }
  public DataResponse disbleBorEntity(BorEntityReqSave borEntity, String userId) {

        DataResponse response = new DataResponse();
        try {
        response.setDataResponse(borEntityRepository.disbleBorEntity(
                borEntity.getKeyId(),
                borEntity.getStatus()));
        if(response.getDataResponse() != null){
            response.setStatus("00");
            response.setMessage("ສໍາເລັດ");
        }else {
            response.setStatus("05");
            response.setMessage("ບໍ່ສາມາດບັນທຶກບໍ່ເເຫ່ໄດ້");
        }
    }catch (Exception e){
        response.setStatus("EE");
        response.setMessage("Error Data");
    }
        return response;
    }


private static BorEntity getMapBor(BorEntityReqSave borEntity, String userId) {
    BorEntity entity = new BorEntity();
    entity.setBName(borEntity.getBname());
    entity.setEmail(borEntity.getEmail());
    entity.setBTel(borEntity.getBtel());
    entity.setLocation(borEntity.getBlocation());
    entity.setSortName(borEntity.getSortName());
    entity.setBrandNo(borEntity.getBrandNo());
    entity.setCreateDate(new Date());
    entity.setUserId(userId);
    entity.setStatus(borEntity.getStatus());
    return entity;
}
//======make bor end =====


    public DataResponse paymentItem(ItemPaymentReq itemPaymentReq, String userId, String role, String branchNo){
        DataResponse response = new DataResponse();
        try {

            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("ສໍາເລັດ");
            }else {
                response.setStatus("00");
                response.setMessage("ລາຍການຊໍາລະຂອງທ່ານ ບໍ່ສໍາເລັດ !!!!");
            }
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error data Please check you're data !!!");

        }
        return response;
    }
    @Autowired
    ImpCustomerDao customerService;
    public PaymentItemDetailsRes getPaymentItem(ItemPaymentReq itemPaymentReq, String userId, String role, String branchNo){
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        PaymentItemDetailsRes response = new PaymentItemDetailsRes();
        response.setLogo("http://khounkham.com/images/batery/b94de922-005b-4452-8763-5246c207fa86-b94de922-005b-4452-8763-5246c207fa86.jpg");
        List<GroupPaymentItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<ItemPaymentViewEntity> listData = new ArrayList<>();
        try {
              listData =   customerService.getPaymentItem();
            List<String> billNoList = listData.stream()
                    .map(ItemPaymentViewEntity::getInvoiceNo)
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList) {
                GroupPaymentItemHeader groupHeader = new GroupPaymentItemHeader();
                // Filter once and reuse
                List<ItemPaymentViewEntity> filteredItems = listData.stream()
                        .filter(p -> p.getInvoiceNo().equals(bill))
                        .collect(Collectors.toList());
                if (filteredItems.isEmpty()) continue;
                ItemPaymentViewEntity firstItem = filteredItems.get(0);
                groupHeader.setInvoiceNo(firstItem.getInvoiceNo());
                groupHeader.setBillNo(firstItem.getBillNo());
                groupHeader.setBorName(firstItem.getBorname());
                groupHeader.setBorLocation(firstItem.getBlocation());

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                groupHeader.setTxnDate(firstItem.getSaveDate() != null ? formatter.format(firstItem.getSaveDate()) : "No Date Found");
                groupHeader.setExpDate3(firstItem.getExp() != null ? formatter.format(firstItem.getExp()) : "No Date Found");

                int totalQty = filteredItems.stream().mapToInt(ItemPaymentViewEntity::getQtycal).sum();
                double totalAmount = filteredItems.stream().mapToDouble(p -> p.getAmountscal().doubleValue()).sum();
                double totalPayment = filteredItems.stream().mapToDouble(p -> p.getTotalcal().doubleValue()).sum();

                groupHeader.setQty(totalQty);
                groupHeader.setAmount(numfm.format(totalAmount));
                groupHeader.setPaymentTotal(numfm.format(totalPayment));
                groupHeader.setSunSpendTotal(numfm.format(totalPayment - totalAmount));
                groupHeader.setStatus(firstItem.getStatus());

                // Remove duplicates by detail_id if needed
                List<ItemPaymentViewEntity> uniqueDetails = new ArrayList<>();
                for(ItemPaymentViewEntity listStockTxn :  listData){
                    if(listStockTxn.getInvoiceNo().equals(bill)) {
                        ItemPaymentViewEntity ent = new ItemPaymentViewEntity();
                        ent.setPaymentNo(listStockTxn.getPaymentNo());
                        ent.setPaymentSaveBy(listStockTxn.getPaymentSaveBy());
                        ent.setPaymentSaveDate(listStockTxn.getPaymentSaveDate());
                        ent.setPayBy(listStockTxn.getPayBy());
                        ent.setPayDate(listStockTxn.getPayDate());
                        ent.setDetail_id(listStockTxn.getDetail_id());
                        ent.setBillNo(listStockTxn.getBillNo());
                        ent.setInvoiceNo(listStockTxn.getInvoiceNo());
                        ent.setBorname(listStockTxn.getBorname());
                        ent.setBlocation(listStockTxn.getBlocation());
                        ent.setItemId(listStockTxn.getItemId());
                        ent.setItemName(listStockTxn.getItemName());
                        ent.setUnit(listStockTxn.getUnit());
                        ent.setSize(listStockTxn.getSize());
                        ent.setQty(listStockTxn.getQty());
                        ent.setPrice(listStockTxn.getPrice());
                        ent.setTotal(listStockTxn.getTotal());
                        ent.setSaveBy(listStockTxn.getSaveBy());
                        ent.setSaveDate(listStockTxn.getSaveDate());
                        ent.setApproveBy(listStockTxn.getApproveBy());
                        ent.setApproveDate(listStockTxn.getApproveDate());
                        ent.setBrandName(listStockTxn.getBrandName());
                        ent.setItemTypeName(listStockTxn.getItemTypeName());
                        ent.setImage(listStockTxn.getImage());
                        ent.setStatus(listStockTxn.getStatus());
                        ent.setAmount(listStockTxn.getAmount());
                        ent.setCcy(listStockTxn.getCcy());
                        ent.setExp(listStockTxn.getExp());
                        ent.setTotalcal(listStockTxn.getTotalcal());
                        ent.setQtycal(listStockTxn.getQtycal());
                        ent.setAmountscal(listStockTxn.getAmountscal());
                        uniqueDetails.add(ent);
                    }
                    groupHeader.setDetails(uniqueDetails);
                }
                groupStockItemHeaders.add(groupHeader);
            }
            response.setDataResponse(groupStockItemHeaders);
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("Success");
            }else {
                response.setStatus("05");
                response.setMessage("Data not found");
            }
        }
        catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error retrieving data: " + e.getMessage());
            log.error("Exception: ", e);
        }
        return response;
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    public PaymentDetailsRes getPaymentItemDetail(ItemPaymentReq itemPaymentReq, String userId, String role, String branchNo) {
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        PaymentDetailsRes response = new PaymentDetailsRes();
        response.setLogo("http://khounkham.com/images/batery/b94de922-005b-4452-8763-5246c207fa86-b94de922-005b-4452-8763-5246c207fa86.jpg");

        List<GroupDetailsHeader> groupStockItemHeaders = new ArrayList<>();
        List<ItemDetailsEntity> listData = customerService.getPaymentItemDetails(); // Assuming this fetches your data

        try {
            // Filter distinct invoice_no entries
            List<String> billNoList = listData.stream()
                    .map(ItemDetailsEntity::getInvoice_no)
                    .distinct()
                    .collect(Collectors.toList());

            for (String bill : billNoList) {
                GroupDetailsHeader groupHeader = new GroupDetailsHeader();

                List<ItemDetailsEntity> filtered = listData.stream()
                        .filter(p -> p.getInvoice_no().equals(bill))
                        .collect(Collectors.toList());

                if (filtered.isEmpty()) continue;

                ItemDetailsEntity first = filtered.get(0);
                groupHeader.setInvoiceNo(first.getInvoice_no());
                groupHeader.setBillNo(first.getBill_no());

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                groupHeader.setTxnDate(first.getSavedate() != null ? formatter.format(first.getSavedate()) : "No Date Found");
                groupHeader.setExpDate3(first.getExp() != null ? formatter.format(first.getExp()) : "No Date Found");

                int totalQty = filtered.stream().mapToInt(ItemDetailsEntity::getQty).sum();
                double totalAmount = filtered.stream().mapToDouble(p -> p.getAmount() != null ? p.getAmount() : 0f).sum();
                double totalPayment = filtered.stream().mapToDouble(p -> p.getTotal() != null ? p.getTotal() : 0f).sum();

                groupHeader.setQty(totalQty);
                groupHeader.setAmount(numfm.format(totalAmount));
                groupHeader.setPaymentTotal(numfm.format(totalPayment));
                groupHeader.setSunSpendTotal(numfm.format(totalPayment - totalAmount));
                groupHeader.setStatus(first.getStatus());

                // Deep copy to avoid reference issues
                List<ItemDetailsEntity> uniqueDetails = filtered.stream().map(item -> {
                    ItemDetailsEntity ent = new ItemDetailsEntity();
                    ent.setPayment_no(item.getPayment_no());
                    ent.setBill_no(item.getBill_no());
                    ent.setStatus(item.getStatus());
                    ent.setInvoice_no(item.getInvoice_no());
                    ent.setTotal(item.getTotal());
                    ent.setQty(item.getQty());
                    ent.setAmount(item.getAmount());
                    ent.setCcy(item.getCcy());
                    ent.setPayment_type(item.getPayment_type());
                    ent.setRexchange_rate(item.getRexchange_rate());
                    ent.setSavedate(item.getSavedate());
                    ent.setSaveby(item.getSaveby());
                    ent.setType(item.getType());
                    ent.setExp(item.getExp());
                    return ent;
                }).collect(Collectors.toList());

                groupHeader.setDetails(uniqueDetails);
                groupStockItemHeaders.add(groupHeader);
            }

            response.setDataResponse(groupStockItemHeaders);
            if (!groupStockItemHeaders.isEmpty()) {
                response.setStatus("00");
                response.setMessage("Success");
            } else {
                response.setStatus("05");
                response.setMessage("Data not found");
            }
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error retrieving data: " + e.getMessage());
            log.error("Exception: ", e);
        }

        return response;
    }
    public PaymentItemDetailsRes getReportPaymentItem(ItemPaymentReq itemPaymentReq, String userId, String role, String branchNo){
        String borNumber = itemPaymentReq.getBorNumber();
        String status = itemPaymentReq.getStatus();
        String startDate = itemPaymentReq.getStartDate();
        String endDate = itemPaymentReq.getEndDate();
        log.info("borNumber:"+borNumber);
        log.info("status:"+status);
        log.info("startDate:"+startDate);
        log.info("endDate:"+endDate);
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        PaymentItemDetailsRes response = new PaymentItemDetailsRes();
        response.setLogo("http://khounkham.com/images/batery/b94de922-005b-4452-8763-5246c207fa86-b94de922-005b-4452-8763-5246c207fa86.jpg");
        List<GroupPaymentItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<ItemPaymentViewEntity> listData = new ArrayList<>();
        GroupPaymentItemHeader groupHeader = new GroupPaymentItemHeader();
        try {
            if(!"all".equals(status)){
                log.info("===start 01");
                if(!"all".equals(borNumber)){
                    listData = itemPaymentViewEntityRepository.getBillPaymentByDateHaveStatus(startDate,endDate,status);
                }else {
                    listData = itemPaymentViewEntityRepository.getBillPaymentByDateHaveStatusBor(startDate,endDate,status,borNumber);
                }
            }else {
                log.info("===start 02");
                if(!"all".equals(borNumber)){
                    log.info("===start 02.1");
                    listData = itemPaymentViewEntityRepository.findPaymentsByDateRangeBorNumber(startDate,endDate,borNumber);
                }else {
                    log.info("===start 02.2");
                    listData = itemPaymentViewEntityRepository.findPaymentsByDateRangeBor(startDate,endDate);
                }
            }

            List<String> billNoList = Optional.ofNullable(listData)
                    .orElse(Collections.emptyList()) // Ensure `listData` isn't null
                    .stream()
                    .map(ItemPaymentViewEntity::getInvoiceNo)
                    .filter(Objects::nonNull) // Remove null values from invoiceNo
                    .distinct()
                    .collect(Collectors.toList());
            for (String bill : billNoList){
                groupHeader = new GroupPaymentItemHeader();
                groupHeader.setInvoiceNo(listData.stream().filter(p -> p.getInvoiceNo().equals(bill)).map(ItemPaymentViewEntity::getInvoiceNo).findFirst().orElse(""));
                groupHeader.setBillNo(listData.stream().filter(p -> p.getInvoiceNo().equals(bill)).map(ItemPaymentViewEntity::getBillNo).findFirst().orElse(""));
                //===start =========
                groupHeader.setBorName(listData.stream().filter(p -> p.getInvoiceNo().equals(bill)).map(ItemPaymentViewEntity::getBorname).findFirst().orElse(""));
                groupHeader.setBorLocation(listData.stream().filter(p -> p.getInvoiceNo().equals(bill)).map(ItemPaymentViewEntity::getBlocation).findFirst().orElse(""));

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a"); // Desired format
                Optional<Date> optionalDate = listData.stream()
                        .filter(p -> p.getInvoiceNo().equals(bill))
                        .map(ItemPaymentViewEntity::getSaveDate)
                        .findFirst();
                groupHeader.setTxnDate(null);

                groupHeader.setQty((int) listData.stream()
                        .filter(p -> p.getInvoiceNo().equals(bill))
                        .count());
                Double total = listData.stream().filter(p -> p.getInvoiceNo().equals(bill)).map(ItemPaymentViewEntity::getAmount).collect(Collectors.summingDouble(Float::doubleValue));
                groupHeader.setAmount(numfm.format(total));
                groupHeader.setStatus(listData.stream().filter(p -> p.getInvoiceNo().equals(bill)).map(ItemPaymentViewEntity::getStatus).findFirst().orElse(""));
                groupStockItemHeaders.add(groupHeader);
                List<ItemPaymentViewEntity> groupListData = new ArrayList<>();
                for(ItemPaymentViewEntity listStockTxn :  listData){
                    if(listStockTxn.getInvoiceNo().equals(bill)){
                        groupListData.add(listStockTxn);
                    }
                    groupHeader.setDetails(null);
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
        }
        catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error retrieving data: " + e.getMessage());
            log.error("Exception: ", e);
        }

        return response;
    }

    //*****payment item
    public DataResponse paymentItem (PaymentDetailsEntityReq paymentDetailsEntity,String userId){
        String status = paymentDetailsEntity.getStatus();
        log.info("status:"+status);
        log.info("exp:"+paymentDetailsEntity.getExp());
        Date conExpiredDate = null;
        Date expiredDate = paymentDetailsEntity.getExp();
        if (expiredDate == null){
            conExpiredDate = new Date();
        }else {
            conExpiredDate = paymentDetailsEntity.getExp();
        }
        DataResponse response = new DataResponse();
        try {
            PaymentDetailsEntity mapperEntity = mapper(paymentDetailsEntity,userId,conExpiredDate);
            response.setDataResponse(paymentDetailsEntityRepository.save(mapperEntity));
            if(response.getDataResponse() != null){
                //******if payment get status = ok then update payment item set status = ok
                if("ok".equals(status)){
                    log.info("===let update payment to ok");
                    itemPaymentEntityRepository.updateStatusPayment(paymentDetailsEntity.getInvoiceNo(),status);
                }else {
                    itemPaymentEntityRepository.updateStatusPayment(paymentDetailsEntity.getInvoiceNo(),status);
                }
                response.setStatus("00");
                response.setMessage("success");
            }else {
                response.setStatus("00");
                response.setMessage("Can't Payment This Invoice");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  response;
    }
    public  PaymentDetailsEntity mapper(PaymentDetailsEntityReq paymentDetailsEntity,String userId,Date expDate){
        PaymentDetailsEntity entity = new PaymentDetailsEntity();
        entity.setInvoiceNo(paymentDetailsEntity.getInvoiceNo());
        entity.setAmount(paymentDetailsEntity.getAmount());
        entity.setCcy(paymentDetailsEntity.getCcy());
        entity.setRexchangeRate(paymentDetailsEntity.getExchangeRate());
        entity.setType(paymentDetailsEntity.getType());
        entity.setPaymentType(paymentDetailsEntity.getPaymentType());
        entity.setSaveDate(new Date());
        entity.setSaveby(userId);
        entity.setExp(expDate);
       /// entity.setTotal(paymentDetailsEntity.getTotal());
        entity.setStatus(paymentDetailsEntity.getStatus());
   return entity;
    }

    //****
    public DataResponse getRequestItemType(){
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(requestItemTypeRepository.findAll());
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("success");
            }else {
                response.setStatus("00");
                response.setMessage("Data not Found !!");
            }

        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");
        }
        return response;
    }

    @Autowired
    requestItemTypeBorNameEntityRepository requestItemTypeBorNameEntityRepository;
    public DataResponse getRequestItemByItemType(requestData requestData,String borId){
        log.info("borId:"+borId);
        log.info("info req:"+requestData.toString());
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(requestItemTypeBorNameEntityRepository.findByTypeAndBorNo(requestData.getReqTypeId(),borId));
            if(response.getDataResponse() != null){
                response.setStatus("00");
                response.setMessage("success");
            }else {
                response.setStatus("00");
                response.setMessage("Data not Found !!");
            }

        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data !!");
        }
        return response;
    }
}
