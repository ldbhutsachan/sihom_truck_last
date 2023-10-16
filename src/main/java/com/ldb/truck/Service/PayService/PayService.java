package com.ldb.truck.Service.PayService;

import com.ldb.truck.Model.Login.Pay.*;
import com.ldb.truck.Model.Login.ResFromDateReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Dao.PayDao.PayDao;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PayService {
    private static final Logger log = LogManager.getLogger(PayService.class);
    @Autowired PayDao payDao;

    public BankRes getListBank(){
        BankRes result = new BankRes();
        List<Bank> resList = new ArrayList<>();
        try {
            resList = payDao.getListBank();
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resList);
        }catch (Exception e){
            e.printStackTrace();
            result.setData(resList);
        }
        return result;
    }
    //--get billPayment
    public getBillNoRes getBillNoForPay(){
        getBillNoRes result =new getBillNoRes();
        List<getBillNo> resData = new ArrayList<>();
        try {
            resData = payDao.getBillNo();
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setData(resData);
        }
        return result;
    }
    //---store paryment
    public PayRes StorePayment(PayReq payReq){
        PayRes result = new PayRes();
        int checkData = 0;
        int checkSetInvoice = 0;
        try{
            checkSetInvoice = payDao.setInvoiceStatus(payReq);
            checkData = payDao.storePayment(payReq);
            payDao.storePayOwe(payReq);
            if (checkData ==0 && checkSetInvoice==0){
                result.setMessage("can't store Payment");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
            return result;
        }catch (Exception e) {
            result.setMessage("can't store Payment");
            result.setStatus("01");
            return result;
        }
    }
    //
    public PayRes storePayOwe(PayReq payReq){
        PayRes result = new PayRes();
        int checkData = 0;
        int checkSetInvoice = 0;
        try{
            checkSetInvoice = payDao.setPaymentStatus(payReq);
            payDao.setPaymentAmoutAndStatus(payReq);
            checkData = payDao.storePayOwe(payReq);
            if (checkData ==0 && checkSetInvoice==0){
                result.setMessage("can't store Payment");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
            return result;
        }catch (Exception e) {
            result.setMessage("can't store Payment");
            result.setStatus("01");
            return result;
        }
    }
    public PrintBillPaymentRes printBillPayment(PrintBillPaymentReq printReq){
        log.info("chec02");
        PrintBillPaymentRes result = new PrintBillPaymentRes();
        List<PrintBillPayment> resData = new ArrayList<>();
        PrintBillPayment resDataGroup = new PrintBillPayment();
        PrintBillPaymentHeader header = new PrintBillPaymentHeader();
        List<PrintBillPaymentHeader> ListHeader = new ArrayList<>();
        PrintBillPaymentFooter sumFooter = new PrintBillPaymentFooter();
        List<PrintBillPaymentFooter> ListFooter = new ArrayList<>();
        double sumTotalAll =0;
        double sumPayamount=0;
        double sumNoPayAmount=0;
        double noPayAmount =0;
        double sumPayNoFee =0;
        DecimalFormat numfm = new DecimalFormat("###,###.###");

        List<PrintBillPaymentInfo> details = new ArrayList<>();
        try{
            resData = payDao.PrintBillNo(printReq);
            List<String> billNo = resData.stream().map(PrintBillPayment::getBillNo).distinct().collect(Collectors.toList());
            for(String billNoID : billNo){
                header = new PrintBillPaymentHeader();
                header.setBillNo(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getBillNo).findFirst().orElse(""));
                header.setPrintDate(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getPrintDate).findFirst().orElse(""));
                header.setCusId(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getCusId).findFirst().orElse(""));
                header.setCusName(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getCusName).findFirst().orElse(""));
                header.setLocation(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getLocation).findFirst().orElse(""));

                ListHeader.add(header);
            }
            List<PrintBillPayment> resDataGroupDetail = new ArrayList<>();
            for(PrintBillPayment listDataGroupDetails : resData){
                resDataGroup = new PrintBillPayment();
                resDataGroup.setCusId(listDataGroupDetails.getCusId());
                resDataGroup.setCusName(listDataGroupDetails.getCusName());
                resDataGroup.setLocation(listDataGroupDetails.getLocation());
                resDataGroup.setPrintDate(listDataGroupDetails.getPrintDate());
                resDataGroup.setBillNo(listDataGroupDetails.getBillNo());
                resDataGroup.setProId(listDataGroupDetails.getProId());
                resDataGroup.setProName_type(listDataGroupDetails.getProName_type());
                resDataGroup.setProTotal(listDataGroupDetails.getProTotal());
                resDataGroup.setPriceUnit(listDataGroupDetails.getPriceUnit());
                resDataGroup.setAmountTotal(listDataGroupDetails.getAmountTotal());
                resDataGroup.setAmountTotalS(listDataGroupDetails.getAmountTotalS());
                resDataGroup.setPayAmount(listDataGroupDetails.getPayAmount());
                noPayAmount = listDataGroupDetails.getNoPayAmount();
                resDataGroup.setCurrency(listDataGroupDetails.getCurrency());
                if(noPayAmount > 0){
                  resDataGroup.setNoPayAmount(listDataGroupDetails.getNoPayAmount());
                }else{
                    resDataGroup.setNoPayAmount(sumPayNoFee);
                }
                resDataGroupDetail.add(resDataGroup);
            }
            for (String billNoID : billNo) {
                sumFooter = new PrintBillPaymentFooter();
                sumTotalAll = resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getAmountTotal).collect(Collectors.summingDouble(Double::doubleValue));
                    sumFooter.setTotalAmount(numfm.format(sumTotalAll));
                sumPayamount = Double.parseDouble(String.valueOf(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getPayAmount).findFirst().orElse(Double.valueOf("0.0"))));
                    sumFooter.setSumPayamount(numfm.format(sumPayamount));

                sumNoPayAmount = (sumTotalAll-sumPayamount);
                sumFooter.setSumNoPayAmount(numfm.format(sumNoPayAmount));

                ListFooter.add(sumFooter);
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setDetails(resDataGroupDetail);
            result.setHeader(ListHeader);
            result.setFooter(ListFooter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //--show list txn
    public  PayTxnDetailsRes listTxn(){
        PayTxnDetailsRes result = new PayTxnDetailsRes();
        List<PayTxnDetails> resList = new ArrayList<>();
        try{
            resList = payDao.listPayment();
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resList);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resList);
        }
        return  result;
    }
    //--LstPaymentByDate
    public  PayCashRes LstPaymentByDate(ResFromDateReq resFromDateReq){
        List<PayTxnDetails> resDataItems = new ArrayList<>();
        List<PayTxnDetails> resDataItemsHeader2 = new ArrayList<>();
        PayCashRes result = new PayCashRes();
        List<PayTxnDetails> resList = new ArrayList<>();
        PayHeader header = new PayHeader();
        List<PayHeader> listHeader = new ArrayList<>();
        PayHeaderFT headerFT= new PayHeaderFT();
        List<PayHeaderFT> payHeaderFTS = new ArrayList<>();
        double sumPerTotal =0;
        double totalAMountAll =0;
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        try{
            resList = payDao.LstPaymentByDate(resFromDateReq);
            List<String> PaymentType = resList.stream().map(PayTxnDetails::getPaymentType).distinct().collect(Collectors.toList());
            for(String Payment : PaymentType) {
                if (Payment.equals("ເງິນສົດ")) {
                    header = new PayHeader();
                    header.setPaymentType(resList.stream().filter(p -> p.getPaymentType().equals(Payment)).map(PayTxnDetails::getPaymentType).findFirst().orElse(""));
                    sumPerTotal = resList.stream().filter(p -> p.getPaymentType().equals(Payment)).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                    header.setTotalAmountPerTXN(numfm.format(sumPerTotal));
                    listHeader.add(header);
                    resDataItems = new ArrayList<>();
                    for (PayTxnDetails listData : resList) {
                        log.info("ss"+listData.getPaymentType());
                        log.info("ss"+resList);
                        if (listData.getPaymentType().equals("ເງິນສົດ")) {
                            PayTxnDetails listDetails = new PayTxnDetails();
                            listDetails.setBillNo(listData.getBillNo());
                            listDetails.setPayDate(listData.getPayDate());
                            listDetails.setInvoiceNo(listData.getInvoiceNo());
                            listDetails.setPaymentType(listData.getPaymentType());
                            listDetails.setBankName(listData.getBankName());
                            listDetails.setRefNo(listData.getRefNo());
                            listDetails.setCusId(listData.getCusId());
                            listDetails.setCusName(listData.getCusName());
                            listDetails.setMoBile(listData.getMoBile());
                            listDetails.setAmount(listData.getAmount());
                            listDetails.setPayAmount(listData.getPayAmount());
                            listDetails.setStatus(listData.getStatus());
                            listDetails.setTotalDay(listData.getTotalDay());
                            listDetails.setPaymentAmounts(listData.getPaymentAmounts());
                            resDataItems.add(listDetails);
                        }
                    }
                    header.setPayByCash(resDataItems);
                }//--ຳສຫຳ
            }
            for(String Payment :PaymentType){
                if(Payment.equals("ເງິນໂອນ")){
                    headerFT = new PayHeaderFT();
                    headerFT.setPaymentType(resList.stream().filter(p -> p.getPaymentType().equals(Payment)).map(PayTxnDetails::getPaymentType).findFirst().orElse(""));
                    sumPerTotal = resList.stream().filter(p -> p.getPaymentType().equals(Payment)).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                    headerFT.setTotalAmountPerTXN(numfm.format(sumPerTotal));
                    payHeaderFTS.add(headerFT);
                    resDataItemsHeader2 = new ArrayList<>();
                    for (PayTxnDetails listData : resList) {
                        log.info("ss"+listData.getPaymentType());
                        log.info("ss"+resList);
                        if (listData.getPaymentType().equals("ເງິນໂອນ")) {
                            PayTxnDetails listDetails = new PayTxnDetails();
                            listDetails.setBillNo(listData.getBillNo());
                            listDetails.setPayDate(listData.getPayDate());
                            listDetails.setInvoiceNo(listData.getInvoiceNo());
                            listDetails.setPaymentType(listData.getPaymentType());
                            listDetails.setBankName(listData.getBankName());
                            listDetails.setRefNo(listData.getRefNo());
                            listDetails.setCusId(listData.getCusId());
                            listDetails.setCusName(listData.getCusName());
                            listDetails.setMoBile(listData.getMoBile());
                            listDetails.setAmount(listData.getAmount());
                            listDetails.setPayAmount(listData.getPayAmount());
                            listDetails.setStatus(listData.getStatus());
                            listDetails.setTotalDay(listData.getTotalDay());
                            listDetails.setPaymentAmounts(listData.getPaymentAmounts());
                            resDataItemsHeader2.add(listDetails);
                        }
                    }
                    headerFT.setPayByFT(resDataItemsHeader2);
                }
            }
            //--,+
            headerFT.setPayByFT(resDataItemsHeader2);
            header.setPayByCash(resDataItems);
            totalAMountAll = resList.stream().distinct().map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
            result.setTotalAmountAll(numfm.format(totalAMountAll));
            result.setMessage("success");
            result.setStatus("00");
            result.setHeaderCash(listHeader);
            result.setHeaderFT(payHeaderFTS);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return  result;
    }
    //--LstPaymentByDateAll
    public  PayCashRes LstPaymentByDateAll(ResFromDateReq resFromDateReq){
        List<PayTxnDetails> resDataItems = new ArrayList<>();
        List<PayTxnDetails> resDataItemsHeader2 = new ArrayList<>();
        PayCashRes result = new PayCashRes();
        List<PayTxnDetails> resList = new ArrayList<>();
        PayHeader header = new PayHeader();
        List<PayHeader> listHeader = new ArrayList<>();
        PayHeaderFT headerFT= new PayHeaderFT();
        List<PayHeaderFT> payHeaderFTS = new ArrayList<>();
        double sumPerTotal =0;
        double totalAMountAll =0;
        //cur
        double totalAmountPerTXNKIP =0;
        double totalAmountPerTXNUSD =0;
        double totalAmountPerTXNTHB =0;
        double totalAmountPerCNY =0;
        //--all
        double totalAMountAllLAK =0;
        double totalAMountAllUSD =0;
        double totalAMountAllTHB =0;
        double totalAMountAllCNY =0;
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        try{
            resList = payDao.LstPaymentByDateAll(resFromDateReq);
            List<String> PaymentType = resList.stream().map(PayTxnDetails::getPaymentType).distinct().collect(Collectors.toList());
            List<String> chLAK = resList.stream().map(PayTxnDetails::getCurrency).distinct().collect(Collectors.toList());
            for(String Payment : PaymentType) {
                if (Payment.equals("ເງິນສົດ")) {
                    header = new PayHeader();
                    header.setPaymentType(resList.stream().filter(p -> p.getPaymentType().equals(Payment)).map(PayTxnDetails::getPaymentType).findFirst().orElse(""));
                    sumPerTotal = resList.stream().filter(p -> p.getPaymentType().equals(Payment) ).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                    header.setTotalAmountPerTXN(numfm.format(sumPerTotal));
                    //-----LAK
                    totalAmountPerTXNKIP = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("LAK")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                    header.setTotalAmountPerTXNKIP(numfm.format(totalAmountPerTXNKIP));
                    //-----USD
                    totalAmountPerTXNUSD = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("USD")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                    header.setTotalAmountPerTXNUSD(numfm.format(totalAmountPerTXNUSD));
                    //-----THB
                    totalAmountPerTXNTHB = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("THB")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                    header.setTotalAmountPerTXNTHB(numfm.format(totalAmountPerTXNTHB));
                    //-----CHY
                    totalAmountPerCNY = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("CNY")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                    header.setTotalAmountPerCNY(numfm.format(totalAmountPerCNY));
                    listHeader.add(header);
                    resDataItems = new ArrayList<>();
                    for (PayTxnDetails listData : resList) {
                        log.info("ss"+listData.getPaymentType());
                        log.info("ss"+resList);
                        if (listData.getPaymentType().equals("ເງິນສົດ")) {
                            PayTxnDetails listDetails = new PayTxnDetails();
                            listDetails.setBillNo(listData.getBillNo());
                            listDetails.setPayDate(listData.getPayDate());
                            listDetails.setInvoiceNo(listData.getInvoiceNo());
                            listDetails.setPaymentType(listData.getPaymentType());
                            listDetails.setBankName(listData.getBankName());
                            listDetails.setRefNo(listData.getRefNo());
                            listDetails.setCusId(listData.getCusId());
                            listDetails.setCusName(listData.getCusName());
                            listDetails.setMoBile(listData.getMoBile());
                            listDetails.setAmount(listData.getAmount());
                            listDetails.setPayAmount(listData.getPayAmount());
                            listDetails.setStatus(listData.getStatus());
                            listDetails.setTotalDay(listData.getTotalDay());
                            listDetails.setPaymentAmounts(listData.getPaymentAmounts());
                            listDetails.setCurrency(listData.getCurrency());
                            listDetails.setStaff_Curr(listData.getStaff_Curr());
                            resDataItems.add(listDetails);
                        }
                    }
                    header.setPayByCash(resDataItems);
                }//--ຳສຫຳ
            }
            for(String Payment :PaymentType){
               if(Payment.equals("ເງິນໂອນ")){
                   headerFT = new PayHeaderFT();
                   headerFT.setPaymentType(resList.stream().filter(p -> p.getPaymentType().equals(Payment)).map(PayTxnDetails::getPaymentType).findFirst().orElse(""));
                   sumPerTotal = resList.stream().filter(p -> p.getPaymentType().equals(Payment)).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                   headerFT.setTotalAmountPerTXN(numfm.format(sumPerTotal));
                   //-----LAK
                   totalAmountPerTXNKIP = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("LAK")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                   headerFT.setTotalAmountPerTXNKIP(numfm.format(totalAmountPerTXNKIP));
                   //-----USD
                   totalAmountPerTXNUSD = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("USD")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                   headerFT.setTotalAmountPerTXNUSD(numfm.format(totalAmountPerTXNUSD));
                   //-----THB
                   totalAmountPerTXNTHB = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("THB")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                   headerFT.setTotalAmountPerTXNTHB(numfm.format(totalAmountPerTXNTHB));
                   //-----CHY
                   totalAmountPerCNY = resList.stream().filter(p -> p.getPaymentType().equals(Payment) && p.getCurrency().equals("CNY")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
                   headerFT.setTotalAmountPerCNY(numfm.format(totalAmountPerCNY));

                   payHeaderFTS.add(headerFT);
                   resDataItemsHeader2 = new ArrayList<>();
                   for (PayTxnDetails listData : resList) {
                       log.info("ss"+listData.getPaymentType());
                       log.info("ss"+resList);
                       if (listData.getPaymentType().equals("ເງິນໂອນ")) {
                           PayTxnDetails listDetails = new PayTxnDetails();
                           listDetails.setBillNo(listData.getBillNo());
                           listDetails.setPayDate(listData.getPayDate());
                           listDetails.setInvoiceNo(listData.getInvoiceNo());
                           listDetails.setPaymentType(listData.getPaymentType());
                           listDetails.setBankName(listData.getBankName());
                           listDetails.setRefNo(listData.getRefNo());
                           listDetails.setCusId(listData.getCusId());
                           listDetails.setCusName(listData.getCusName());
                           listDetails.setMoBile(listData.getMoBile());
                           listDetails.setAmount(listData.getAmount());
                           listDetails.setPayAmount(listData.getPayAmount());
                           listDetails.setStatus(listData.getStatus());
                           listDetails.setTotalDay(listData.getTotalDay());
                           listDetails.setPaymentAmounts(listData.getPaymentAmounts());
                           listDetails.setCurrency(listData.getCurrency());
                           listDetails.setStaff_Curr(listData.getStaff_Curr());
                           resDataItemsHeader2.add(listDetails);
                       }
                   }
                   headerFT.setPayByFT(resDataItemsHeader2);
               }
            }
            log.info("show ch currency:"+chLAK);
            //--,+
            headerFT.setPayByFT(resDataItemsHeader2);
            header.setPayByCash(resDataItems);
            totalAMountAll = resList.stream().distinct().map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
            result.setTotalAmountAll(numfm.format(totalAMountAll));
            //--LAK
            totalAmountPerTXNKIP = resList.stream().filter(p -> p.getCurrency().equals("LAK")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
            result.setTotalAmountAllLAK(numfm.format(totalAmountPerTXNKIP));
            totalAMountAllUSD = resList.stream().filter(p -> p.getCurrency().equals("USD")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
            result.setTotalAmountAllUSD(numfm.format(totalAMountAllUSD));
            totalAMountAllTHB = resList.stream().filter(p -> p.getCurrency().equals("THB")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
            result.setTotalAmountAllTHB(numfm.format(totalAMountAllTHB));
            totalAMountAllCNY = resList.stream().filter(p -> p.getCurrency().equals("CNY")).map(PayTxnDetails::getPaymentAmounts).collect(Collectors.summingDouble(Double::doubleValue));
            result.setTotalAmountAllCNY(numfm.format(totalAMountAllCNY));
            result.setMessage("success");
            result.setStatus("00");
            result.setHeaderCash(listHeader);
            result.setHeaderFT(payHeaderFTS);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return  result;
    }
    ///-----Pay OWE
    public  PayTxnDetailsRes v_popupPay(){
        double pay = 0.0;
        double noPay = 0.0;
        double amount =0.0;
        PayTxnDetailsRes result = new PayTxnDetailsRes();
        List<PayTxnDetails> resList = new ArrayList<>();
        List<PayTxnDetails> resList02 = new ArrayList<>();
        try{
            resList = payDao.v_popupPay();
            log.info("log:"+resList);
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resList);

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return  result;
    }
    //--
    public PrintBillPaymentRes  listBillPaymentByNoBlack(PrintBillPaymentReq printReq){
        PrintBillPaymentRes result = new PrintBillPaymentRes();
        List<PrintBillPayment> resData = new ArrayList<>();
        PrintBillPaymentHeader header = new PrintBillPaymentHeader();
        List<PrintBillPaymentHeader> ListHeader = new ArrayList<>();
        PrintBillPaymentFooter sumFooter = new PrintBillPaymentFooter();
        List<PrintBillPaymentFooter> ListFooter = new ArrayList<>();
        double sumTotalAll =0;
        double sumPayamount=0;
        double sumNoPayAmount=0;
        double sumCashPayAmount=0;
        DecimalFormat numfm = new DecimalFormat("###,###.###");

        List<PrintBillPaymentInfo> details = new ArrayList<>();
        try{
            resData = payDao.listBillPaymentByNoBlack(printReq);
            List<String> billNo = resData.stream().map(PrintBillPayment::getBillNo).distinct().collect(Collectors.toList());
            for(String billNoID : billNo){
                header = new PrintBillPaymentHeader();
                header.setBillNo(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getBillNo).findFirst().orElse(""));
                header.setPrintDate(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getPrintDate).findFirst().orElse(""));
                header.setCusId(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getCusId).findFirst().orElse(""));
                header.setCusName(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getCusName).findFirst().orElse(""));
                header.setLocation(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getLocation).findFirst().orElse(""));
                ListHeader.add(header);
            }
            for (String billNoID : billNo) {
                sumFooter = new PrintBillPaymentFooter();
                sumTotalAll = resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getAmountTotal).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setTotalAmount(numfm.format(sumTotalAll));
                sumPayamount =  resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getPayAmount).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setSumPayamount(numfm.format(sumPayamount));
                sumNoPayAmount =  resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getNoPayAmount).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setSumNoPayAmount(numfm.format(sumNoPayAmount));
             //   sumFooter.setTotalCashAmount(String.valueOf(resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getPayCashAmount).findFirst().orElse(Double.valueOf(""))));
                sumCashPayAmount =  resData.stream().filter(p -> p.getBillNo().equals(billNoID)).map(PrintBillPayment::getPayCashAmount).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setTotalCashAmount(numfm.format(sumCashPayAmount));

                ListFooter.add(sumFooter);
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setDetails(resData);
            result.setHeader(ListHeader);
            result.setFooter(ListFooter);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //----revertPaymentByStatusNo---revertPaymentByStatusHis
    public PayRes revertPayment(PrintBillPaymentReq paymentReq){
        PayRes result =new PayRes();
        int i =0;
        int ii =0;
        try
        {
            i = payDao.revertPaymentByStatusNo(paymentReq);
            ii = payDao.revertPaymentByStatusHis(paymentReq);
            if(i ==0 && ii==0){
                result.setStatus("01");
                result.setMessage("can't revert payment");
                return result;
            }
            result.setStatus("00");
            result.setMessage("suceess");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
