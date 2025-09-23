package com.ldb.truck.Service.StockService;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Entity.Bor.BorEntity;
import com.ldb.truck.Entity.Bor.BorEntityReq;
import com.ldb.truck.Entity.Bor.BorEntityReqSave;
import com.ldb.truck.Entity.Item.viewItemEntity;
import com.ldb.truck.Entity.ItemPayment.*;
import com.ldb.truck.Entity.OrderItem.*;
import com.ldb.truck.Entity.RequestItem.*;
import com.ldb.truck.Entity.Stock.*;
import com.ldb.truck.Entity.User.UserHisEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.*;
import com.ldb.truck.Repository.Payment.ItemDetailsEntityRepository;
import com.ldb.truck.Repository.Payment.ItemPaymentEntityRepository;
import com.ldb.truck.Repository.Payment.PaymentDetailsEntityRepository;
import com.ldb.truck.Repository.Payment.VCalOrderEntityRepository;
import com.ldb.truck.Repository.RequestItem.RequestItemTypeRepository;
import com.ldb.truck.Entity.RequestItem.requestData;
import com.ldb.truck.Repository.RequestItem.requestItemTypeBorNameEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    @Autowired
    UserHisRepository userHisRepository;

    @Autowired
    ItemDetailsEntityRepository itemDetailsEntityRepository;

    @Autowired
    ViewItemEntityRepository viewItemEntityRepository ;

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
    @Autowired V_OrderTxnEntityRepository vOrderTxnEntityRepository;
    @Autowired OrderItemSaveEntityRepository orderItemSaveEntityRepository;
    @Autowired OrderDetailsRepository orderDetailsRepository;
    @Autowired ViewOrderDetailsRepository viewOrderDetailsRepository;
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
                String ccy = stock.getCurrency();
                    itemEntityRepository.updateStockInItemStock(qty,price,ccy ,itemNo);
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

//   public V_OrderItemDetailsRes getOrderItem(String conReq,String branchNo, String userId,String role,String status){
//        log.info("userId:"+userId);
//        log.info("branchNo:"+branchNo);
//        log.info("role:"+role);
//        log.info("conReq:"+conReq);
//          //  DecimalFormat numfm = new DecimalFormat("###,###.###");
//       V_OrderItemDetailsRes response = new V_OrderItemDetailsRes();
//        List<V_OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
//        List<V_order_item_details> listData = new ArrayList<>();
//      // V_OrderItemHeader groupHeader = new V_OrderItemHeader();
//        try {
//            //***step ກວດສອບເງືອນໄຂກ່ອນ
//            //I : ກວດສະຖານະ
//            if("all".equals(status)){
//                //I : ກວດສະຖານະ
//                if ("1".equals(conReq)) {
//                    if ("USERSTOCK".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderBySavebyStatus(branchNo, userId);
//                    } else if ("AUTH".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByBranchNoStatusAll(branchNo);
//                    } else if ("BUYER".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByBranchNoStatus(branchNo);
//                    } else if ("ACCOUNTING".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByBranchNoStatus(branchNo);
//                    } else if ("PADMIN".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByAdminNo();
//                    }
//                }
//                else if ("2".equals(conReq)) {
//                    listData = vOrderTxnEntityRepository.getOrderByAdminNo();
//                }
//
//            }else {
//                //1:ສະເເດງສະເພາະສາຂາ 2: ສະເເດງທັງໝົດລວມທັງສາຂາ
//                if ("1".equals(conReq)) {
//                    //1:ສະເເດງສະເພາະສາຂາ
//                    //====first step 1 check role mk auth padmin buyyer accounting
//                    if ("USERSTOCK".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderBySavebyWithBranchNo(branchNo, userId, status);
//                    } else if ("AUTH".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByBranchNo(branchNo, status);
//                    } else if ("BUYER".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderBybuyerBranchNo(branchNo, status);
//                    } else if ("ACCOUNTING".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByAccountBranchNo(branchNo, status);
//                    } else if ("PADMIN".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByAdminBranchNo(status);
//                    }
//                } else if ("2".equals(conReq)) {
//                    //2: ສະເເດງທັງໝົດລວມທັງສາຂາ
//                    //====first step 1 check role mk auth padmin buyyer accounting
//                    if ("USERSTOCK".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderBySaveby(userId, status);
//                    } else if ("AUTH".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByBr(status);
//                    } else if ("BUYER".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderBybuyer(status);
//                    } else if ("ACCOUNTING".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByAccount(status);
//                    } else if ("PADMIN".equals(role)) {
//                        listData = vOrderTxnEntityRepository.getOrderByAdmin(status);
//                    }
//                }
//            }
//            List<String> billNoList = listData.stream()
//                    .map(V_order_item_details::getBillNo)
//                    .distinct()
//                    .collect(Collectors.toList());
//
//            NumberFormat numfm = NumberFormat.getNumberInstance(); // Or use DecimalFormat if needed
//
//            for (String bill : billNoList) {
//                log.info("Processing billNo: {}", bill);
//                V_OrderItemHeader groupHeader = new V_OrderItemHeader();
//
//                groupHeader.setBillNo(bill);
//
//                String formattedDate = Optional.ofNullable(listData)
//                        .flatMap(data -> data.stream()
//                                .filter(p -> bill.equals(p.getBillNo()))
//                                .map(V_order_item_details::getSaveDate)
//                                .findFirst())
//                        .map(date -> new SimpleDateFormat("yyyy-MM-dd").format(date))
//                        .orElse("0000-00-00");
//
//                groupHeader.setTxnDate(formattedDate);
//
//                // ==== Currency groupings ====
//                groupHeader.setLaklQty((int) listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()) && "LAK".equals(p.getCurrency()))
//                        .count());
//
//                double totalLak = listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()) && "LAK".equals(p.getCurrency()))
//                        .map(V_order_item_details::getAmountCurrency)
//                        .filter(Objects::nonNull)
//                        .mapToDouble(Float::doubleValue)
//                        .sum();
//
//                groupHeader.setLakAmount(numfm.format(totalLak));
//
//                groupHeader.setUsdQty((int) listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()) && "USD".equals(p.getCurrency()))
//                        .count());
//
//                double totalUsd = listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()) && "USD".equals(p.getCurrency()))
//                        .map(V_order_item_details::getAmountCurrency)
//                        .filter(Objects::nonNull)
//                        .mapToDouble(Float::doubleValue)
//                        .sum();
//
//                groupHeader.setUsdAmount(numfm.format(totalUsd));
//
//                groupHeader.setThbQty((int) listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()) && "THB".equals(p.getCurrency()))
//                        .count());
//
//                double totalThb = listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()) && "THB".equals(p.getCurrency()))
//                        .map(V_order_item_details::getAmountCurrency)
//                        .filter(Objects::nonNull)
//                        .mapToDouble(Float::doubleValue)
//                        .sum();
//
//                groupHeader.setThbAmount(numfm.format(totalThb));
//
//                groupHeader.setStatus(listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()))
//                        .map(V_order_item_details::getStatus)
//                        .findFirst()
//                        .orElse(""));
//
//                // Set details
//                List<V_order_item_details> groupListData = listData.stream()
//                        .filter(p -> bill.equals(p.getBillNo()))
//                        .collect(Collectors.toList());
//
//                groupHeader.setDetails(groupListData);
//                groupStockItemHeaders.add(groupHeader);
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
    public V_OrderItemDetailsRes getOrderItemReport(String conReq,String branchNo,
                                                     String userId,String role,String status,String startDate,String endDate,String borNo,String borNoFone){
        log.info("userId:"+userId);
        log.info("branchNo:"+branchNo);
        log.info("borNo:"+borNo);
        log.info("borNoFone:"+borNoFone);
        log.info("role:"+role);
        log.info("conReq:"+conReq);
        log.info("startDate:"+startDate);
        log.info("endDate:"+endDate);
          //  DecimalFormat numfm = new DecimalFormat("###,###.###");
       V_OrderItemDetailsRes response = new V_OrderItemDetailsRes();
        List<V_OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<V_order_item_details> listData = new ArrayList<>();
        try {

            listData = getDataReportDetails ( conReq, branchNo,
                     userId, role, status, startDate, endDate,borNo,borNoFone);

            List<String> billNoList = listData.stream()
                    .map(V_order_item_details::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());

            NumberFormat numfm = NumberFormat.getNumberInstance(); // Or use DecimalFormat if needed

            for (String bill : billNoList) {
                log.info("Processing billNo: {}", bill);
                V_OrderItemHeader groupHeader = new V_OrderItemHeader();

                groupHeader.setBillNo(bill);

                String formattedDate = Optional.ofNullable(listData)
                        .flatMap(data -> data.stream()
                                .filter(p -> bill.equals(p.getBillNo()))
                                .map(V_order_item_details::getSaveDate)
                                .findFirst())
                        .map(date -> new SimpleDateFormat("yyyy-MM-dd").format(date))
                        .orElse("0000-00-00");

                groupHeader.setTxnDate(formattedDate);

                // ==== Currency groupings ====
                groupHeader.setLaklQty((int) listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()) && "LAK".equals(p.getCurrency()))
                        .count());

                double totalLak = listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()) && "LAK".equals(p.getCurrency()))
                        .map(V_order_item_details::getAmountCurrency)
                        .filter(Objects::nonNull)
                        .mapToDouble(Float::doubleValue)
                        .sum();

                groupHeader.setLakAmount(numfm.format(totalLak));

                groupHeader.setUsdQty((int) listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()) && "USD".equals(p.getCurrency()))
                        .count());

                double totalUsd = listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()) && "USD".equals(p.getCurrency()))
                        .map(V_order_item_details::getAmountCurrency)
                        .filter(Objects::nonNull)
                        .mapToDouble(Float::doubleValue)
                        .sum();

                groupHeader.setUsdAmount(numfm.format(totalUsd));

                groupHeader.setThbQty((int) listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()) && "THB".equals(p.getCurrency()))
                        .count());

                double totalThb = listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()) && "THB".equals(p.getCurrency()))
                        .map(V_order_item_details::getAmountCurrency)
                        .filter(Objects::nonNull)
                        .mapToDouble(Float::doubleValue)
                        .sum();

                groupHeader.setThbAmount(numfm.format(totalThb));


                groupHeader.setStatus(listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()) && !p.getStatus().equals("reject_buyer") && !p.getStatus().equals("reject"))
                        .map(V_order_item_details::getStatus)
                        .findFirst()
                        .orElse(""));

                // Set details
                List<V_order_item_details> groupListData = listData.stream()
                        .filter(p -> bill.equals(p.getBillNo()))
                        .collect(Collectors.toList());

                groupHeader.setDetails(groupListData);
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
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Error Data");
        }
        return response;
    }
    public List<V_order_item_details> getDataReportDetails(String conReq, String branchNo,
                                                           String userId, String role,
                                                           String status, String startDate,
                                                           String endDate, String borNo,String borNoFone) {
        try {
            String borNoCon = "";
            String conDate= "";
            String conUserId= "";
            String conBranch  = "";
            String conStatus = "";
            if(conReq.equals("1")){
                conBranch = "\n AND branchno ='"+branchNo+"' ";
            }else if (conReq.equals("2")){
                conBranch ="";
            }
            if(!status.equals("all")){
                conStatus ="\n AND status ='"+status+"' ";
            }else {
                conStatus = "";
            }
            if ("USERSTOCK".equals(role)) {
                conUserId = "\n AND saveby ='"+userId+"' and borkey='"+borNo+"' ";
            } else if ("AUTH".equals(role)) {
                conUserId = "";
            } else if ("BUYER".equals(role)) {
                conUserId = "";
            } else if ("ACCOUNTING".equals(role)) {
                conUserId = "";
            } else if ("PADMIN".equals(role)) {
                conUserId = "";
            }

            if (startDate != null && !startDate.trim().isEmpty()) {
                conDate = "\n AND DATE_FORMAT(savedate, '%Y-%m-%d') >= '" + startDate + "' AND DATE_FORMAT(savedate, '%Y-%m-%d') <= '" + endDate + "' ";
            } else {
                conDate = "";
            }

            if(!borNoFone.equals("all")){
                borNoCon = "\n AND borkey ='"+borNo+"' ";
            }else {
                borNoCon = "";
            }

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM v_order_item where 1=1 "); // You can add WHERE clauses based on parameters
            sb.append(conBranch);
            sb.append(conStatus);
            sb.append(conUserId);
            sb.append(conDate);
            sb.append(borNoCon);

            String sql = sb.toString();
            log.info("sql:"+sql);
            return EBankJdbcTemplate.query(sql, new RowMapper<V_order_item_details>() {
                @Override
                public V_order_item_details mapRow(ResultSet rs, int rowNum) throws SQLException {
                    V_order_item_details tr = new V_order_item_details();
                    tr.setDetailId(rs.getInt("detail_id"));
                    tr.setBillNo(rs.getString("bill_no"));
                    tr.setBarcode(rs.getString("barcode"));
                    tr.setItemId(rs.getInt("item_id"));
                    tr.setItemName(rs.getString("item_name"));
                    tr.setUnit(rs.getString("unit"));
                    tr.setSize(rs.getString("size"));
                    tr.setCurrency(rs.getString("currency"));
                    tr.setExchangeRate(rs.getInt("exchange_rate"));
                    tr.setQty(rs.getInt("qty"));
                    tr.setPrice(rs.getFloat("price"));
                    tr.setStatus(rs.getString("status"));
                    tr.setToKen(rs.getString("token"));
                    tr.setTotal(rs.getFloat("total"));
                    tr.setAmountCurrency(rs.getFloat("amount"));
                    tr.setTotalAmountCurrency(rs.getFloat("toal_amount"));
                    tr.setImage(rs.getString("image"));
                    tr.setSaveBy(rs.getString("saveby"));
                    tr.setSaveDate(rs.getDate("savedate"));
                    tr.setEditBy(rs.getString("editby"));
                    tr.setEditDate(rs.getDate("editdate"));
                    tr.setApproveBy(rs.getString("approveby"));
                    tr.setApproveDate(rs.getDate("approvedate"));
                    tr.setRejectBy(rs.getString("rejectby"));
                    tr.setRejectDate(rs.getDate("rejectby_date"));
                    tr.setBuyerBy(rs.getString("buyerby"));
                    tr.setBuyerDate(rs.getDate("buyerdate"));
                    tr.setAccountBy(rs.getString("accountby"));
                    tr.setAccountDate(rs.getDate("accountdate"));
                    tr.setAcceptBy(rs.getString("acceptby"));
                    tr.setAcceptDate(rs.getDate("acceptdate"));
                    tr.setBranchNo(rs.getString("branchno"));
                    tr.setBranchName(rs.getString("bname"));
                    tr.setBorkey(rs.getString("borkey"));
                    tr.setBorame(rs.getString("borname"));
                    tr.setRemark(rs.getString("remark"));
                    return tr;
                }
            });
        } catch (Exception e) {
            e.printStackTrace(); // Consider logging this properly
        }
        return null;
    }

    ///-----
    public DataResponse saveItemIn(OrderRequest stockItemDetailsEntity, String userId) {
        DataResponse response = new DataResponse();
        OrderGenEntity keyGen =  orderGenKeyRepository.maxReqKey();
        String genKey = keyGen.getMaxReqKey();
        log.info("genKey:"+genKey);
        try {
            List<OrderItemReportEntity> entities = convertToOrderItemEntities(stockItemDetailsEntity, userId,genKey);
            List<OrderItemReportEntity> savedEntities = new ArrayList<>();
            orderItemSaveEntityRepository.saveAll(entities).forEach(savedEntities::add);
            if (!savedEntities.isEmpty()) {
                OrderItemTxnEntity requestEntity = new OrderItemTxnEntity();
                requestEntity.setBillNo(genKey);
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
    private List<OrderItemReportEntity> convertToOrderItemEntities(OrderRequest request, String userId,String genKey) {
        List<OrderItemReportEntity> entities = new ArrayList<>();

        for (OrderReqItem item : request.getItemId()) {
            OrderItemReportEntity entity = new OrderItemReportEntity();
            entity.setBillNo(genKey);
            entity.setItemId(item.getItem());
            entity.setQty(item.getQty());
            entity.setRealQty(item.getRealQty());
            entity.setRPrice(item.getPrice());
            entity.setRealPrice(item.getRealPrice());
            entity.setExchangeRate(item.getExchangeRate());
            entity.setRealCurrency(item.getCurrency());

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


    private List<OrderItemReportEntity> mapOrderItemAuth(StockItemAuthReq request, String userId) {
        List<OrderItemReportEntity> entities = new ArrayList<>();

        for (StockItemAuthModel item : request.getDetailId()) {
            OrderItemReportEntity entity = new OrderItemReportEntity();
            entity.setBillNo(request.getBillNo());
            entity.setItemId(item.getItemId());
            entity.setQty(item.getQty());
            entity.setPrice(item.getAmount());
            entity.setApproveBy(userId);
            entity.setApproveDate(new Date());
            entity.setStatus("auth"); // Example default status
            entities.add(entity);
        }

        return entities;
    }
    @Autowired
    VCalOrderEntityRepository vCalOrderEntityRepository;

    public DataResponse auth(StockItemAuthReq request, String userId,String userName) {

        String status = request.getStatus();
        String choiceStatus = request.getOrderStatus();
        log.info("retry:"+request.getOrderStatus());
        DataResponse response = new DataResponse();
        try {
            int updated = 0;
            //check edit or
            if("edit".equals(choiceStatus)){
                //****let update data only
                log.info("==START EDIT =====");
                updated= editTxn(request,userId,userName);
            }else if("retry".equals(choiceStatus)){
                log.info("==START RETRY =====");
                updated= retryTxn(request,userId,userName);
            }
            else if("reject_buyer".equals(choiceStatus)){
                //****i want reject when buyer  check no data
                log.info("show :reject_buyer:"+ choiceStatus);
                updated= retryTxnReject(request,userId,userName);
            }
            else {
                if ("wait".equals(status)) {
                    log.info("status:" + request.getStatus());
                    updated = checkStatusWait(request.getStatus(), request, userId);
                } else if ("auth".equals(status)) {
                    log.info("status:" + request.getStatus());
                    updated = checkStatusAuth(request.getStatus(), request, userId);
                } else if ("reject".equals(status)) {
                    log.info("status:" + request.getStatus());
                    updated = checkStatusReject(request.getStatus(), request, userId);
                } else if ("buyer".equals(status)) {
                    log.info("status:" + request.getStatus());
                    updated = checkStatusBuyer(request.getStatus(), request, userId);
                } else if ("accounting".equals(status)) {
                    log.info("status:" + request.getStatus());
                    updated = checkStatusAccounting(request.getStatus(), request, userId);
                } else if ("wait-item".equals(status)) {
                    log.info("status:" + request.getStatus());
                    updated = checkStatusWaitItem(request.getStatus(), request, userId);
                } else if ("ok".equals(status)) {
                    log.info("status:" + request.getStatus());
                    updated = checkStatusOK(request.getStatus(), request, userId);
                }
            }
            log.info("show size update :" + updated);
            if (updated > 0) {
                response.setDataResponse(updated);
                response.setStatus("00");
                response.setMessage("ການອະນຸມັດສຳເລັດ");
            } else {
                response.setDataResponse(updated);
                response.setStatus("00");
                response.setMessage("ການອະນຸມັດບໍ່ສຳເລັດ !!!");
            }

        } catch (Exception e) {
            log.error("Approval failed", e);
            response.setStatus("EE");
            response.setMessage("Error while saving data: " + e.getMessage());
        }

        return response;
    }


    //=========wait
    public int checkStatusWait(String status,StockItemAuthReq request, String userId){
            log.info("====start service ====");
            //****let start other service
            List<OrderItemReportEntity> items = authConvert(request, userId);
            log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
            int updated = 0;
            final String sql = "UPDATE order_item_details SET " +
                    "saveby = ?, " +
                    "savedate = ?, " +
                    "qty = ?," +
                    "price = ?," +
                    "status= 'wait' , " +
                    "currency= ?, " +
                    "exchange_rate= ? " +
                    "WHERE item_id = ? and bill_no=?  ";
            for (OrderItemReportEntity item : items) {
                log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
                updated = EBankJdbcTemplate.update(
                        sql,
                        userId,
                        new Date(),
                        item.getQty(),
                        item.getPrice(),
                        item.getCurrency(),
                        item.getExchangeRate(),
                        item.getDetailId(),
                        item.getBillNo()
                );
                log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
            }
        return 1;

    }
    //================auth
    public int checkStatusAuth(String status,StockItemAuthReq request, String userId){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "approveby = ?, " +
                "approvedate = ?, " +
                "qty = ?," +
                "price = ?," +
                "status= 'auth' , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ? and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                    userId,
                    new Date(),
                    item.getQty(),
                    item.getPrice(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
        }
        return 1;

    }
    //================reject
    public int checkStatusReject(String status, StockItemAuthReq request, String userId) {
        log.info("==== Starting checkStatusReject service for billNo: {} ====", request.getBillNo());

        // Convert request to domain entities
        List<OrderItemReportEntity> items = authConvert(request, userId);
        log.info("Found {} item(s) to reject", items.size());

        // Update remark on order_item
        String updateRemarkSql = "UPDATE order_item SET remark = ? WHERE bill_no = ?";
        EBankJdbcTemplate.update(updateRemarkSql, request.getRemark(), request.getBillNo());
        log.info("Updated remark for billNo: {}", request.getBillNo());
        log.info("Updated remark for getRemark: {}", request.getRemark());

        // Prepare update for order_item_details
        String updateDetailSql = "UPDATE order_item_details SET " +
                "rejectby = ?, " +
                "rejectbyDate = ?, " +
                "qty = ?, " +
                "price = ?, " +
                "status = 'reject', " +
                "currency = ?, " +
                "exchange_rate = ? " +
                "WHERE item_id = ? AND bill_no = ?";

        int totalUpdated = 0;
        Date now = new Date();

        for (OrderItemReportEntity item : items) {
            log.debug("Rejecting item_id={}, qty={}, price={}, currency={}, exchangeRate={}",
                    item.getDetailId(), item.getQty(), item.getPrice(), item.getCurrency(), item.getRealExchangeRate());

            int updated = EBankJdbcTemplate.update(
                    updateDetailSql,
                    userId,
                    now,
                    item.getQty(),
                    item.getPrice(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            totalUpdated += updated;

            log.info("Updated {} row(s) for item_id={}", updated, item.getDetailId());
        }

        log.info("==== Completed reject updates. Total rows affected: {} ====", totalUpdated);
        return totalUpdated;
    }
    //buyer
    public int checkStatusBuyer(String status,StockItemAuthReq request, String userId){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "buyer_id = ?, " +
                "buyer_date = ?, " +
                "qty = ?," +
                "price = ?," +
                "status= 'buyer' , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ? and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                    userId,
                    new Date(),
                    item.getQty(),
                    item.getPrice(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
        }
        return 1;

    }
    //accounting
    public int checkStatusAccounting(String status,StockItemAuthReq request, String userId){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "account_id = ?, " +
                "account_date = ?, " +
                "qty = ?," +
                "price = ?," +
                "status= 'accounting' , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ? and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                    userId,
                    new Date(),
                    item.getQty(),
                    item.getPrice(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
        }
        return 1;

    }
    //wait-item
    public int checkStatusWaitItem(String status,StockItemAuthReq request, String userId){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "account_id = ?, " +
                "account_date = ?, " +
                "qty = ?," +
                "price = ?," +
                "status= 'wait-item' , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ? and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                    userId,
                    new Date(),
                    item.getQty(),
                    item.getPrice(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
        }
        return 1;

    }
    public int checkStatusOK(String status,StockItemAuthReq request, String userId){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "acceptby = ?, " +
                "acceptdate = ?, " +
                "qty = ?," +
                "price = ?," +
                "status= 'ok' , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ? and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                    userId,
                    new Date(),
                    item.getQty(),
                    item.getPrice(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
        }
        return 1;

    }
    public int editTxn(StockItemAuthReq request, String userId,String userName){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        Date now = new Date();
        log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "qty = ?," +
                "price = ?," +
             //   "status= ? , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ? and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                 //   userId,
                  //  new Date(),
                    item.getQty(),
                    item.getPrice(),
                  //  item.getStatus(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
            String details = item.getDetailId().toString();
            //****let store log
            UserHisEntity entity = new UserHisEntity();
            entity.setUser_id(userId);
            entity.setUserName(userName);
            entity.setDetailId(item.getDetailId());
            entity.setBillNo(item.getBillNo());
            entity.setCreateDate(now);
            entity.setDetails(details);
            userHisRepository.save(entity);
        }
        return 1;

    }
    public int retryTxn(StockItemAuthReq request, String userId,String userName){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        Date now = new Date();
        log.info("Approving {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "qty = ?," +
                "price = ?," +
                "status= ? , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ? and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                 //   userId,
                  //  new Date(),
                    item.getQty(),
                    item.getPrice(),
                    item.getStatus(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
            String details = item.getDetailId().toString();
            //****let store log
            UserHisEntity entity = new UserHisEntity();
            entity.setId(UUID.randomUUID().toString());
            entity.setUser_id(userId);
            entity.setUserName(userName);
            entity.setDetailId(item.getDetailId());
            entity.setBillNo(item.getBillNo());
            entity.setCreateDate(now);
            entity.setDetails(details);
            userHisRepository.save(entity);
        }
        return 1;

    }
    public int retryTxnReject(StockItemAuthReq request, String userId,String userName){
        log.info("====start service ====");
        //****let start other service
        List<OrderItemReportEntity> items = authConvert(request, userId);
        Date now = new Date();
        log.info("Reject {} item(s) for billNo: {}", items.size(), request.getBillNo());
        int updated = 0;
        final String sql = "UPDATE order_item_details SET " +
                "qty = ?," +
                "price = ?," +
                "status= ? , " +
                "currency= ?, " +
                "exchange_rate= ? " +
                "WHERE item_id = ?  and bill_no=?  ";
        for (OrderItemReportEntity item : items) {
            log.debug("Updating detail_id = {}, qty = {}, price = {} ,status ={}", item.getDetailId(), item.getQty(), item.getPrice(),item.getStatus());
            updated = EBankJdbcTemplate.update(
                    sql,
                    item.getQty(),
                    item.getPrice(),
                    item.getStatus(),
                    item.getCurrency(),
                    item.getExchangeRate(),
                    item.getDetailId(),
                    item.getBillNo()
            );
            log.info("Updated {} row(s) for detail_id = {}", updated, item.getDetailId());
          //  String details = item.getDetailId().toString();
            //****let store log
//            UserHisEntity entity = new UserHisEntity();
//            entity.setId(UUID.randomUUID().toString());
//            entity.setUser_id(userId);
//            entity.setUserName(userName);
//            entity.setDetailId(item.getDetailId());
//            entity.setBillNo(item.getBillNo());
//            entity.setCreateDate(now);
//            entity.setDetails(details);
//            userHisRepository.save(entity);
        }
        return 1;

    }

    public DataResponse authByAdmin(StockItemAuthReq request, String userId) {
        DataResponse response = new DataResponse();
        try {
            int updated = 0;
            final String sql = "UPDATE order_item_details SET approveby = ?, approvedate = ?,status=? WHERE  bill_no=?  ";
                updated = EBankJdbcTemplate.update(
                        sql,
                        userId,
                        new Date(),
                        request.getStatus(),
                        request.getBillNo()

                );
                log.info("Updated {} row(s) for detail_id = {}", updated, request.getBillNo());

            if(updated > 0){
                response.setDataResponse(updated);
                response.setStatus("00");
                response.setMessage("ການອະນຸມັດສຳເລັດ");
            }else {
                response.setDataResponse(updated);
                response.setStatus("00");
                response.setMessage("ການອະນຸມັດບໍ່ສຳເລັດ !!!");
            }

        } catch (Exception e) {
            log.error("Approval failed", e);
            response.setStatus("EE");
            response.setMessage("Error while saving data: " + e.getMessage());
        }

        return response;
    }
    private List<OrderItemReportEntity> authConvert(StockItemAuthReq request, String userId) {
        List<OrderItemReportEntity> entities = new ArrayList<>();

        for (StockItemAuthModel item : request.getDetailId()) {
            OrderItemReportEntity entity = new OrderItemReportEntity();
            entity.setDetailId(item.getItemId()); // ✅ Set the missing key!
            entity.setBillNo(request.getBillNo());
            entity.setItemId(item.getItemId());
            entity.setQty(item.getQty());
            entity.setPrice(item.getAmount());
            entity.setStatus(request.getStatus());

            entity.setCurrency(item.getCurrency());
            entity.setExchangeRate(item.getExchangeRate());

            entity.setSaveBy(userId);
            entity.setSaveDate(new Date());
            entity.setStatus(request.getStatus());

            entities.add(entity);
        }

        return entities;
    }


    public DataResponse approveStockItemDetailsOrderProd(StockItemDetailsReq stockItemDetailsReq) {
        String role = stockItemDetailsReq.getRole();
        String pathApi = stockItemDetailsReq.getPathApi();
        log.info("role:"+role);
        log.info("pathApi:"+pathApi);
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
                 //=====ກວດສອບ pathApi ວ່າມາຈາກໃສ່
                if("buyer".equals(pathApi)){
                //***************buyer path*******
                    log.info("===let's start api path :"+pathApi);
                    updatedRows = orderDetailsRepository.approveStockItemDetailsBuyer(
                            stockItemDetailsReq.getUserId(),
                            new Date(),
                            "buyer",
                            stockItemDetailsReq.getBillNo(),
                            detailIdsStr
                    );
                }
                else if("accounting".equals(pathApi)){
                //********************account path api
                    log.info("===let's start api path :"+pathApi);
                    updatedRows = orderDetailsRepository.approveStockItemDetailsAccounting(
                            stockItemDetailsReq.getUserId(),
                            new Date(),
                            "wait-item",
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
   log.info("let start approve ===");
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
                updateItemAndUpTotalOrder(detailIdsStr,stockItemDetailsReq.getBillNo());
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
    public void updateItemInTableItem(Long itemId,String billNo) {

        List<RequestItemEbtity> items = requestItemRepository.findByItemId(itemId,billNo);
        // Check if items list is null or empty
        if (items == null || items.isEmpty()) {
            log.warn("No items found for itemId: " + itemId);
            return;
        }
        // Log the first item for debugging purposes
        log.info("First item in list id: " + items.get(0).getItemId());
        log.info("First item in list qty:" + items.get(0).getQty());
        // Process and update items
        for (RequestItemEbtity stock : items) {

            Integer qty = stock.getQty();
            Integer itemNo = stock.getItemId();

            // Logging details before updating
            log.info("Processing item out: " + itemNo + ", Quantity: " + qty);
            // Perform database update
            itemEntityRepository.updateStockInItemOut(qty, itemNo);


            //call check out stock
            List<viewItemEntity> inventoryList = viewItemEntityRepository.getItemByItemIds(itemNo);
            Integer beforeQty = inventoryList.get(0).getQty();
            String itemNoId = inventoryList.get(0).getItemId();
            Integer delId = stock.getDetailId();
            log.info("show beforeQty:"+beforeQty);
            log.info("show itemNoId:"+itemNoId);
            log.info("show delId:"+delId);
            requestItemRepository.approveRequestItemDetails(
                    beforeQty,delId,itemNoId
            );
        }
    }

    public void updateItemAndUpTotalOrder(String itemId,String billNo) {
        log.info("start 01");
        // Convert itemId string to a list of Long values
        List<Long> itemIdList = Arrays.stream(itemId.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        // Retrieve items from repository
        List<ViewOrderItemReportEntity> items = viewOrderDetailsRepository.findByItemIdToStock(itemIdList);
        // Check if items list is null or empty
        if (items == null || items.isEmpty()) {
            log.warn("No items found for itemId: " + itemId);
            return;
        }
        // Log the first item for debugging purposes
        log.info("First item in list: " + items.get(0).getItemId());
        // Process and update items
        for (ViewOrderItemReportEntity stock : items) {
            Integer qty = stock.getQty();
            Float amount = stock.getPrice();
            Integer itemNo = stock.getItemId();
            Integer qtyData = stock.getRealQtyData();
            String ccy = stock.getCurrency();
            Float amountData = stock.getRPriceData();
            String currencyData = stock.getRealCurrencyData();
            Integer exchangeRateData = stock.getRealExchangeRatedata();
            Float realPriceData = stock.getRealPriceData();
            log.info("Processing item: " + itemNo + ", Quantity: " + qty);
            // Perform database update inventory
            itemEntityRepository.updateStockInItem(qty,amount,ccy,realPriceData,itemNo);

            // let update details for real money
            log.info("show before insert itemNo :"+itemNo);
            log.info("show before insert qty :"+qtyData);
            log.info("show before insert amount :"+amountData);
            log.info("show before insert currency :"+currencyData);
            log.info("show before insert exchangeRate :"+exchangeRateData);
            log.info("show before insert realPrice :"+realPriceData);
            itemEntityRepository.updateStockInItemOrderDetails(qtyData,amountData,currencyData,exchangeRateData,realPriceData,itemNo,billNo);
        }
    }
    //getOrderItemDetailsAuth
    @Autowired
    OrderAuthEntityRepository orderAuthEntityRepository;
    public OrderAuthResponse getOrderItemAuth(String billNo, String role, String userId,String branchNo,String borNo,String status){
        log.info("billNo:"+billNo);
        log.info("userId:"+userId);
        log.info("role:"+role);
        log.info("branchNo:"+branchNo);
        log.info("status:"+status);
        log.info("borNo:"+borNo);
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        OrderAuthResponse response = new OrderAuthResponse();
        List<OrderAuthHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderAuthEntity> listData = new ArrayList<>();
        OrderAuthHeader groupHeader = new OrderAuthHeader();
        try {
            if("PADMIN".equals(role)){
                    listData =orderAuthEntityRepository.getOrderByAdmin(status);///
            }
            else if("USERSTOCK".equals(role)){
                listData =orderAuthEntityRepository.getOrderAuthByBranchNoByMaker(branchNo,status,borNo);
            }
            else if("AUTH".equals(role)){
                    listData =orderAuthEntityRepository.getOrderAuthByBranchNo(branchNo,status,borNo);
            }
            else if("BUYER".equals(role)){
                listData =orderAuthEntityRepository.getOrderAuthByBuyer(status);
            }
            else if("ACCOUNTING".equals(role)){
                listData =orderAuthEntityRepository.getOrderAuthByAccounting(status);
            }else {
                response.setStatus("00");
                response.setMessage("ທ່ານ ບໍ່ມີສິດເຂົ້າເບີ່ງ !!!");
            }
            List<String> billNoList = listData.stream()
                    .map(OrderAuthEntity::getBillNo)
                    .distinct()
                    .collect(Collectors.toList());

            // Collect distinct billNo values where status is not 'reject_buyer'

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

                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill) && !p.getStatus().equals("reject_buyer") && !p.getStatus().equals("reject")).map(OrderAuthEntity::getStatus).findFirst().orElse(""));
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

    public OrderItemDetailsRes getReportOrderItem(StockRequest stockRequest, String userName,String role,String borNo){
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        OrderItemDetailsRes response = new OrderItemDetailsRes();
        List<OrderItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<OrderItemEntity> listData = new ArrayList<>();
        OrderItemHeader groupHeader = new OrderItemHeader();
        try {
            String startDate = stockRequest.getStartDate();
            String endDate = stockRequest.getEndDate();
            String status = stockRequest.getStatus();
            if("PADMIN".equals(role)){
                if (!"ALL".equals(status)) {
                    log.info("show 1");
                    listData = orderTxnEntityRepository.getOrderReportPadmin(startDate, endDate, status);
                } else {
                    log.info("show 2");
                    listData = orderTxnEntityRepository.getOrderReportNoStatusPAdmin(startDate, endDate);
                }
            }else {

                if (!"ALL".equals(status)) {
                    log.info("show 1");
                    listData = orderTxnEntityRepository.getOrderReport(startDate, endDate, status,borNo);
                } else {
                    log.info("show 2");
                    listData = orderTxnEntityRepository.getOrderReportNoStatus(startDate, endDate,borNo);
                }
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

//    @Transactional
//    public DataResponse approveRequestItem(RequestItemDetailsReq stockItemDetailsReq) {
//        log.info("show status:"+stockItemDetailsReq.getStatus());
//        DataResponse response = new DataResponse();
//        try {
//            //=====then
//            boolean allRejected = stockItemDetailsReq.getDetailId().stream()
//                    .allMatch(item -> "reject".equalsIgnoreCase(item.getStatus()));
//
//            if (allRejected) {
//                log.info("=====reject =====");
//                //let update reject by who
//                requestItemRepository.rejectItemRequestByUser(stockItemDetailsReq.getRemark(),
//                        stockItemDetailsReq.getBillNo());
//                //****call to reject item inRequest
//
//                int check = 0;
//                for (RequestItemDetailsReq.OrderObject item : stockItemDetailsReq.getDetailId()) {
//                   check =  requestItemRepository.updateItemStatusById(
//                            stockItemDetailsReq.getUserId(),
//                            new Date(),
//                            item.getItemId(),
//                            stockItemDetailsReq.getBillNo()
//                    );
//                }
//                if (check > 0){
//                    response.setStatus("00");
//                    response.setMessage("Ready Reject Del OK ");
//                    return response;
//                }
//                response.setStatus("EE");
//                response.setMessage("Can't Reject this del No!!!!");
//                return response;
//            }
//            else {
//                log.info("ok =====");
//                //=====let check product qty first
//                List<Long> itemIdList = stockItemDetailsReq.getDetailId().stream()
//                        .map(RequestItemDetailsReq.OrderObject::getItemId)
//                        .collect(Collectors.toList());
//
//                // Retrieve items from repository
//                log.info("=====start check item in stock first auth:"+itemIdList);
//                List<RequestItemEbtity> items = requestItemRepository.findByItemId2(itemIdList,  stockItemDetailsReq.getBillNo());
//                for (RequestItemEbtity item : items) {
//                    List<viewItemEntity> inventoryList = viewItemEntityRepository.getItemByItemIds(item.getItemId());
//
//                    if (inventoryList.isEmpty() || item.getQty() > inventoryList.get(0).getQty()) {
//                        log.warn("Insufficient inventory for itemId: " + item.getItemId());
//                        String msg = String.format(
//                                "No: %s, Name: %s, QtyInStock: %s",
//                                inventoryList.get(0).getItemId(),
//                                inventoryList.get(0).getItem_name(),
//                                inventoryList.get(0).getQty()
//                        );
//                        response.setStatus("00");
//                        response.setMessage("ອາໄຫຼ່ນີ້ໝົດເເລ້ວ : "+msg);
//                        return response;
//                    }
//
//                }
//                //****
//                int updatedRows = 0;
//                for (RequestItemDetailsReq.OrderObject item : stockItemDetailsReq.getDetailId()) {
//                     updatedRows = requestItemRepository.approveRequestItem(
//                            stockItemDetailsReq.getUserId(),
//                            new Date(),
//                            stockItemDetailsReq.getStatus(),
//                             item.getItemId(),
//                            stockItemDetailsReq.getBillNo()
//                    );
//                }
//
//                if (updatedRows > 0) {
//                    if(stockItemDetailsReq.getStatus().equals("ok")){
//                        log.info("=====start update item inventory=====");
//                        for (RequestItemDetailsReq.OrderObject item : stockItemDetailsReq.getDetailId()) {
//                            updateItemInTableItem(item.getItemId(),stockItemDetailsReq.getBillNo());
//                        }
//                    }
//                    response.setStatus("00");
//                    response.setMessage("ທ່ານອະນຸມັດລາຍການຂໍເບີກເຄື່ອງສໍາເລັດ");
//                } else {
//                    response.setStatus("05");
//                    response.setMessage("ທ່ານອະນຸມັດລາຍການຂໍເບີກເຄື່ອງບໍ່ສໍາເລັດ.");
//                }
//                //=======end check
//            }
//        } catch (Exception e) {
//            response.setStatus("EE");
//            response.setMessage("Error while updating request details.");
//        }
//        return response;
//    }
@Transactional
public DataResponse approveRequestItem(RequestItemDetailsReq stockItemDetailsReq) {
    log.info("show status: " + stockItemDetailsReq.getStatus());
    DataResponse response = new DataResponse();

    try {
        // ===== ตรวจสอบว่า reject ทั้งหมดหรือไม่
        boolean allRejected = stockItemDetailsReq.getDetailId().stream()
                .allMatch(item -> "reject".equalsIgnoreCase(item.getStatus()));

        if (allRejected) {
            log.info("=====reject all items=====");
            requestItemRepository.rejectItemRequestByUser(
                    stockItemDetailsReq.getRemark(),
                    stockItemDetailsReq.getBillNo()
            );

            int check = 0;
            for (RequestItemDetailsReq.OrderObject item : stockItemDetailsReq.getDetailId()) {
                check = requestItemRepository.updateItemStatusById(
                        stockItemDetailsReq.getUserId(),
                        new Date(),
                        item.getItemId(),
                        stockItemDetailsReq.getBillNo()
                );
            }

            if (check > 0) {
                response.setStatus("00");
                response.setMessage("Ready Reject Del OK");
            } else {
                response.setStatus("EE");
                response.setMessage("Can't Reject this del No!!!!");
            }
            return response;
        }

        // ===== ตรวจสอบ stock ก่อน approve
        List<Long> itemIdList = stockItemDetailsReq.getDetailId().stream()
                .map(RequestItemDetailsReq.OrderObject::getItemId)
                .collect(Collectors.toList());

        log.info("=====start check item in stock first: " + itemIdList);

        // ดึงรายการที่ request ไว้จาก DB
        List<RequestItemEbtity> items = requestItemRepository.findByItemIdsAndBillNo(
                itemIdList,
                stockItemDetailsReq.getBillNo()
        );

        for (RequestItemEbtity item : items) {
            List<viewItemEntity> inventoryList = viewItemEntityRepository.getItemByItemIds(item.getItemId());

            if (inventoryList.isEmpty() || item.getQty() > inventoryList.get(0).getQty()) {
                String msg = String.format(
                        "No: %s, Name: %s, QtyInStock: %s, RequestedQty: %s",
                        inventoryList.isEmpty() ? "?" : inventoryList.get(0).getItemId(),
                        inventoryList.isEmpty() ? "?" : inventoryList.get(0).getItem_name(),
                        inventoryList.isEmpty() ? 0 : inventoryList.get(0).getQty(),
                        item.getQty()
                );
                response.setStatus("05");
                response.setMessage("ອາໄຫຼ່ນີ້ໝົດເເລ້ວ : " + msg);
                return response; // หยุด function ไม่ให้ approve
            }
        }

        // ===== approve items
        int updatedRows = 0;
        for (RequestItemDetailsReq.OrderObject item : stockItemDetailsReq.getDetailId()) {
            updatedRows = requestItemRepository.approveRequestItem(
                    stockItemDetailsReq.getUserId(),
                    new Date(),
                    stockItemDetailsReq.getStatus(),
                    item.getItemId(),
                    stockItemDetailsReq.getBillNo()
            );
        }

        if (updatedRows > 0) {
            response.setStatus("00");
            response.setMessage("ທ່ານອະນຸມັດລາຍການຂໍເບີກເຄື່ອງສໍາເລັດ");
        } else {
            response.setStatus("05");
            response.setMessage("ທ່ານອະນຸມັດລາຍການຂໍເບີກເຄື່ອງບໍ່ສໍາເລັດ");
        }

    } catch (Exception e) {
        log.error("Error while approving request item: ", e);
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

    public RequestItemDetailsRes getRequestItem(String billNo, String role, String userName, String status,String branchNo){
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        RequestItemDetailsRes response = new RequestItemDetailsRes();
        List<RequestItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<RequestTxnEntity> listData = new ArrayList<>();
        RequestItemHeader groupHeader = new RequestItemHeader();
        String stat = status;
        try {
            //if status all show all
            if(stat.equals("all")){
                //if PADMIN =PADMIN show all
                if("PADMIN".equals(role)) {
                    listData = requestTxnRepository.getStockByBillNoAdminAll();
                }else {
                    listData = requestTxnRepository.getStockByBranch(branchNo);
                }
            }
            else {
                listData = requestTxnRepository.getStockByBillNoAdmin(status,branchNo);
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

                groupHeader.setStatus(listData.stream().filter(p -> p.getBillNo().equals(bill) && !p.getStatus().equals("reject_buyer") && !p.getStatus().equals("reject")).map(RequestTxnEntity::getStatus).findFirst().orElse(""));
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

    public RequestItemDetailsRes getRequestItemReport(StockRequest stockRequest, String userName,String role,String borId){
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        RequestItemDetailsRes response = new RequestItemDetailsRes();
        List<RequestItemHeader> groupStockItemHeaders = new ArrayList<>();
        List<RequestTxnEntity> listData = new ArrayList<>();
        RequestItemHeader groupHeader = new RequestItemHeader();
        try {
            String startDate = stockRequest.getStartDate();
            String endDate = stockRequest.getEndDate();
            String status = stockRequest.getStatus();
            if("PADMIN".equals(role)){
                if(!"ALL".equals(status)) {
                    log.info("show 01");
                    listData = requestTxnRepository.getRequestReport(startDate, endDate, status);
                }else {
                    log.info("show 02");
                    listData = requestTxnRepository.getRequestReportNoStatus(startDate, endDate);
                }
            }else {
                if(!"ALL".equals(status)) {
                    log.info("show 01");
                    listData = requestTxnRepository.getRequestReportBranchNo(startDate, endDate, status,borId);
                }else {
                    log.info("show 02");
                    listData = requestTxnRepository.getRequestReportBranchNStatuso(startDate, endDate,borId);
                }
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

    public DataResponse getBorAll(BorEntityReq borEntityReq){
        String keyId = borEntityReq.getKeyId();
        String typeBor = borEntityReq.getTypeBor();
        log.info("role start:"+keyId);
        DataResponse response = new DataResponse();
       try {
           if(keyId.isEmpty() || keyId.equals("all") || keyId.equals("all")){
               response.setDataResponse(view_borRepository.getBorViewEntityAll(typeBor));
           }else {
               response.setDataResponse(view_borRepository.findBykeyId(keyId,typeBor));
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
    public DataResponse getRequestItemByItemType(requestData requestData,String borId,String boNo,String role){
        log.info("borId:"+borId);
        log.info("info req:"+requestData.toString());
        DataResponse response = new DataResponse();
        try {
            response.setDataResponse(getBor(boNo,requestData,role));
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
    //*********getBor
    public List<requestItemTypeBorNameEntity> getBor(String borId,requestData requestData,String role){
        log.info("show bor:"+borId);
        String borNo = borId;
        String reqTypeId = requestData.getReqTypeId();
        String conBorNo = "";
        String conReqTypeId = "";
        String conQuery = "";
        if(reqTypeId.equals("50")){
            //50 ທົ່ວໄປ
            conReqTypeId  = "\n AND req_id='"+reqTypeId+"'";
            conQuery = "\nselect * from v_req_type where  1=1 ";
        }else if(reqTypeId.equals("51")) {
            //51 ຫົວເຈາະ
            //*****check addmin
            if(role.equals("PADMIN")){
                conQuery = "select '51' req_id,a.mch_name req_name,a.mch_no bor_id,b.key_id bor_no ,'51' type,b.location  from tb_machine a inner join \n" +
                        "tb_bors b  on b.key_id=a.borNo where  1=1";
            }else {
                //51 ຫົວເຈາະ
                conQuery = "select '51' req_id,a.mch_name req_name,a.mch_no bor_id,b.key_id bor_no ,'51' type,b.location  from tb_machine a inner join \n" +
                        "tb_bors b  on b.key_id=a.borNo where a.borNo='"+borNo+"' and 1=1";
            }
        }
        else {
            conQuery = "\n select * from  v_req_type where  1=1 ";
        }
        StringBuilder sb  = new StringBuilder();
        sb.append(conQuery);
        sb.append(conBorNo);
        sb.append(conReqTypeId);
        String sql = sb.toString();
        log.info("sql bor and machine :"+ sql);
        return EBankJdbcTemplate.query(sql, new RowMapper<requestItemTypeBorNameEntity>() {
            @Override
            public requestItemTypeBorNameEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                requestItemTypeBorNameEntity tr = new requestItemTypeBorNameEntity();
                tr.setReqId(rs.getString("req_id"));
                tr.setReqName(rs.getString("req_name"));
                tr.setBorId(rs.getString("bor_id"));
                tr.setBorNo(rs.getString("bor_no"));
                tr.setType(rs.getString("type"));
                tr.setLocation(rs.getString("location"));
                return tr;
            }
        });


    }

}
