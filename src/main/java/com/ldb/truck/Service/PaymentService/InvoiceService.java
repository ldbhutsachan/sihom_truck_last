package com.ldb.truck.Service.PaymentService;

import com.ldb.truck.Model.Login.Payment.*;
import com.ldb.truck.Model.Login.ResFromDateReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Dao.Payment.InvoiceDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private static final Logger log = LogManager.getLogger(InvoiceService.class);
    @Autowired
    InvoiceDao invoiceDao;
    public InvoiceRes ListInvoice(){
        InvoiceRes result = new InvoiceRes();
        List<Invoice> resData = new ArrayList<>();
        try{
            resData = invoiceDao.ListInvoiceAll();
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resData);
        }
        return result;
    }
    //---generate ID
    public GenerateInvoiceIDRes gernerateID(){
        GenerateInvoiceIDRes result = new GenerateInvoiceIDRes();
        List<GenerateInvoiceID> resData = new ArrayList<>();
        try{
            resData = invoiceDao.gernerateID();
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resData);
        }
        return result;
    }
    //save invoice
    public  InvoiceRes CreateInvoice(InvoiceReq invoiceReq){
        InvoiceRes result = new InvoiceRes();
        List<Invoice> dataReturn = new ArrayList<>();
        int data = 0;
        try {
            data = invoiceDao.saveInvoice(invoiceReq);
        if (data ==0){
            result.setMessage(" can not store Invoice");
            result.setStatus("01");
            result.setData(dataReturn);
            return  result;
        }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(dataReturn);
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage(" can not store Invoice");
            result.setStatus("01");
            result.setData(dataReturn);
            return result;
        }
    }
    //---store more info
    public  invoiceDetailRes CreateInvoiceAll(List<InvoiceDetailReq> invoiceDetailReq){
        int checkData=0;
        int checkUpdate=0;
        invoiceDetailRes result = new invoiceDetailRes();
        List<InvoiceDetail>  resList = new ArrayList<>();
        try {
            for (InvoiceDetailReq re:invoiceDetailReq) {
                InvoiceDetail listObject = new InvoiceDetail();
                listObject.setPerID(re.getPerID());
                listObject.setKeyId(re.getKey_id());
                listObject.setCusID(re.getCusID());
                listObject.setCusName(re.getCusID());
                listObject.setProType(re.getProType());
                listObject.setProAmount(re.getProAmount());
                listObject.setPriCE(re.getPriCE());
                listObject.setTotalPrice(re.getTotalPrice());
                listObject.setStatus(re.getStatus());
                listObject.setInVoiceID(re.getInVoiceID());
            }
            checkData  = invoiceDao.CreateMoreInvoice(invoiceDetailReq);
            checkUpdate = invoiceDao.updatePerformance(invoiceDetailReq);
            if (checkData==0 && checkUpdate==0){
                result.setMessage(" can not store Invoice");
                result.setStatus("01");
                result.setData(resList);
                return  result;
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resList);
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage(" can not store Invoice");
            result.setStatus("01");
            result.setData(resList);
            return  result;
        }
    }
    //---printIvoice
    public  PrintInvoiceByNoRes viewPintInvoiceByNo(PrintInvoiceByNoReq printInvoiceByNoReq){
        List<PrintInvoiceByNoSumFooter> listSumFooter = new ArrayList<>();
        PrintInvoiceByNoSumFooter sumFooter = new PrintInvoiceByNoSumFooter();
        PrintInvoiceByNoRes result = new PrintInvoiceByNoRes();
        List<PrintInvoiceByNo> resData = new ArrayList<>();
        GenerateInvoiceID header = new GenerateInvoiceID();
        List<GenerateInvoiceID> headerList = new ArrayList<>();
        long totalRow = 0;
        double sumTotal =0;
        double sumTotalAll =0;
        DecimalFormat numfm = new DecimalFormat("###,###.###");
//--GenerateInvoiceID
         try {
             resData = invoiceDao.viewPintBillByNo(printInvoiceByNoReq);
             //-PrintInvoiceByNoSumFooter
             List<String> InVoiceID = resData.stream().map(PrintInvoiceByNo::getInVoiceID).distinct().collect(Collectors.toList());
             for(String InVoiceNo : InVoiceID){
                 header = new GenerateInvoiceID();
                 header.setINVOICE_ID(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceID).findFirst().orElse(""));
                 header.setPrintDate(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceDate).findFirst().orElse(""));
                 headerList.add(header);
             }
             for (String InVoiceNo : InVoiceID) {
                 sumFooter = new PrintInvoiceByNoSumFooter();
                 totalRow = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceID).count();
                 sumFooter.setRowTotal(totalRow);

                 sumTotal = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getPriCES).collect(Collectors.summingDouble(Double::doubleValue));
                 sumFooter.setPriceUnit(numfm.format(sumTotal));

                 sumTotalAll = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getTotalPriceS).collect(Collectors.summingDouble(Double::doubleValue));
                 sumFooter.setAmountTotal(numfm.format(sumTotalAll));

                 listSumFooter.add(sumFooter);
             }
             result.setMessage("success");
             result.setStatus("00");
             result.setData(resData);
             result.setHeaderPrint(headerList);
             result.setSumTotal(listSumFooter);
         }catch (Exception e){
             e.printStackTrace();
             result.setMessage("data not found");
             result.setStatus("01");
             result.setData(resData);
         }
            return result;
    }
    //----list txn
    public InvoiceRes ListviewBlackPrintNo(ResFromDateReq resFromDateReq){
        InvoiceRes result = new InvoiceRes();
        List<Invoice> resdata = new ArrayList<>();
        try {
            resdata = invoiceDao.listInvoiceDetails(resFromDateReq);
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resdata);

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resdata);
        }
        return result;
    }
    //--Report laiHup
//    ResFromDateReq resFromDateReq
    //--viewPintBillByNo
    public  PrintInvoiceByNoRes PintBillByNo(PrintInvoiceByNoReq printInvoiceByNoReq){
        List<PrintInvoiceByNoSumFooter> listSumFooter = new ArrayList<>();
        PrintInvoiceByNoSumFooter sumFooter = new PrintInvoiceByNoSumFooter();
        PrintInvoiceByNoRes result = new PrintInvoiceByNoRes();
        List<PrintInvoiceByNo> resData = new ArrayList<>();

        GenerateInvoiceID header = new GenerateInvoiceID();
        List<GenerateInvoiceID> headerList = new ArrayList<>();
        long totalRow = 0;
        double sumTotal =0;
        double sumTotalAll =0;
        double sumTotalLak=0;
        double sumTotalUSD=0;
        double sumTotalTHB=0;
        double sumTotalCNY=0;
        DecimalFormat numfm = new DecimalFormat("###,###.###");
//--GenerateInvoiceID
        try {
            resData = invoiceDao.pintBillByNo(printInvoiceByNoReq);
            //-PrintInvoiceByNoSumFooter
            List<String> InVoiceID = resData.stream().map(PrintInvoiceByNo::getInVoiceID).distinct().collect(Collectors.toList());
            for(String InVoiceNo : InVoiceID){
                header = new GenerateInvoiceID();
                header.setINVOICE_ID(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceID).findFirst().orElse(""));
                header.setPrintDate(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceDate).findFirst().orElse(""));
                header.setPayDateOwe(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getPayDateOwe).findFirst().orElse(""));
                headerList.add(header);
            }
            for (String InVoiceNo : InVoiceID) {
                sumFooter = new PrintInvoiceByNoSumFooter();
                totalRow = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceID).count();
                sumFooter.setRowTotal(totalRow);
                sumTotal = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getPriCES).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setPriceUnit(numfm.format(sumTotal));
                sumTotalAll = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getTotalPriceS).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setAmountTotal(numfm.format(sumTotalAll));
                sumTotalLak =resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo) && p.getCurrency().equals("LAK")).map(PrintInvoiceByNo::getTotalPriceS).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setSumTotalLak(numfm.format(sumTotalLak));
                sumTotalUSD =resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo) && p.getCurrency().equals("USD")).map(PrintInvoiceByNo::getTotalPriceS).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setSumTotalUSD(numfm.format(sumTotalUSD));
                sumTotalTHB =resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo) && p.getCurrency().equals("THB")).map(PrintInvoiceByNo::getTotalPriceS).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setSumTotalTHB(numfm.format(sumTotalTHB));
                sumTotalCNY =resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo) && p.getCurrency().equals("CNY")).map(PrintInvoiceByNo::getTotalPriceS).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setSumTotalCNY(numfm.format(sumTotalCNY));
                listSumFooter.add(sumFooter);
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
            result.setHeaderPrint(headerList);
            result.setSumTotal(listSumFooter);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resData);
        }
        return result;
    }
    //--
    public InvoiceRes v_popupPerInVoice(){
        InvoiceRes result = new InvoiceRes();
        List<Invoice> resdata = new ArrayList<>();
        try {
            resdata = invoiceDao.List_v_popupPerInVoice();
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resdata);

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resdata);
        }
        return result;
    }
    //----view Payment by billpayment
    public  PrintInvoiceByNoRes viewPintBillBackByNo(PrintInvoiceByNoReq printInvoiceByNoReq){
        List<PrintInvoiceByNoSumFooter> listSumFooter = new ArrayList<>();
        PrintInvoiceByNoSumFooter sumFooter = new PrintInvoiceByNoSumFooter();
        PrintInvoiceByNoRes result = new PrintInvoiceByNoRes();
        List<PrintInvoiceByNo> resData = new ArrayList<>();
        GenerateInvoiceID header = new GenerateInvoiceID();
        List<GenerateInvoiceID> headerList = new ArrayList<>();
        long totalRow = 0;
        double sumTotal =0;
        double sumTotalAll =0;
        double sumPayamount=0;
        double sumNoPayAmount=0;

        DecimalFormat numfm = new DecimalFormat("###,###.###");
//--GenerateInvoiceID
        try {
            resData = invoiceDao.viewPintBillBackByNo(printInvoiceByNoReq);
            //-PrintInvoiceByNoSumFooter
            List<String> InVoiceID = resData.stream().map(PrintInvoiceByNo::getInVoiceID).distinct().collect(Collectors.toList());
            for(String InVoiceNo : InVoiceID){
                header = new GenerateInvoiceID();
                header.setINVOICE_ID(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceID).findFirst().orElse(""));
                header.setPrintDate(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceDate).findFirst().orElse(""));
                headerList.add(header);
            }
            for (String InVoiceNo : InVoiceID) {
                sumFooter = new PrintInvoiceByNoSumFooter();
                totalRow = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getInVoiceID).count();
                sumFooter.setRowTotal(totalRow);
                sumTotal = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getPriCES).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setPriceUnit(numfm.format(sumTotal));
                sumTotalAll = resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getTotalPriceS).collect(Collectors.summingDouble(Double::doubleValue));
                sumFooter.setAmountTotal(numfm.format(sumTotalAll));
                sumPayamount =Double.parseDouble(String.valueOf(resData.stream().filter(p -> p.getInVoiceID().equals(InVoiceNo)).map(PrintInvoiceByNo::getSumPayamount).findFirst().orElse(Double.valueOf("0.0"))));
                sumFooter.setSumPayamount(numfm.format(sumPayamount));
                sumNoPayAmount = (sumTotalAll-sumPayamount);
                sumFooter.setSumNoPayAmount(numfm.format(sumNoPayAmount));

                listSumFooter.add(sumFooter);
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
            result.setHeaderPrint(headerList);
            result.setSumTotal(listSumFooter);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resData);
        }
        return result;
    }

}
