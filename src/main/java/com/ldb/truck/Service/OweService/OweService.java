package com.ldb.truck.Service.OweService;
import com.ldb.truck.Dao.OweDao.OweDao;
import com.ldb.truck.Model.Login.Owe.*;
import com.ldb.truck.Model.Login.Pay.PayTxnDetails;
import com.ldb.truck.Model.Login.Pay.PayTxnDetailsRes;
import com.ldb.truck.Model.Login.Payment.Customer_Payment;
import com.ldb.truck.Model.Login.Payment.PrintInvoiceByNo;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Service.PayService.PayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OweService {
    private static final Logger log = LogManager.getLogger(OweService.class);
@Autowired
OweDao oweDao;
    public OweRes listTxnOwe(){
        OweRes result = new OweRes();
        List<Owe> resList = new ArrayList<>();
        try{
            resList = oweDao.ListOweDetails();
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
    //---Report
    public OwePayBackRes listReportOwe(ResFromDateReq resFromDateReq){
        List<sumOweFooter> resgetSumfooter = new ArrayList<>();
        OwePayBackRes result = new OwePayBackRes();
        List<OwePayBack> resData = new ArrayList<>();
        OwePayBack listData = new OwePayBack();
        List<OweCustomerDetails> listCusDetails = new ArrayList<>();
        List<OwePayDetails> listDetails = new ArrayList<>();
        OwePayDetails DetailsFooterGroup = new OwePayDetails();
        double sumPayAmount01=0.0;
        double  sumPayAmount02=0.0;
        double  sumPayAmount03=0.0;
        double  sumPayAmount04=0.0;
        double sumTotalPayAmount01= 0.0;
        double sumTotalPayAmount02= 0.0;
        double sumTotalPayAmount03= 0.0;
        double sumTotalPayAmount04= 0.0;
        OweCustomerDetails listDataCus = new OweCustomerDetails();
        List<sumOweFooter> sumOweFooterList = new ArrayList<>();
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        List<GroupCurr> resGroupCurrList  = new ArrayList<>();

        try {
            resData = oweDao.OweReport(resFromDateReq);
            resgetSumfooter= oweDao.getSumfooter(resFromDateReq);
            resGroupCurrList = oweDao.getGroupCurrSumfooter(resFromDateReq);
            log.info("show:"+resgetSumfooter);
            List<String> cusID = resData.stream().map(OwePayBack::getCusId).distinct().collect(Collectors.toList());
            log.info("show999:"+cusID);
            List<String> cusIDSumFooterID = resgetSumfooter.stream().map(sumOweFooter::getCusId).distinct().collect(Collectors.toList());
            for(String cId : cusID){
                listDataCus = new OweCustomerDetails();
                listDataCus.setCusId(resData.stream().filter(p -> p.getCusId().equals(cId)).map(OwePayBack::getCusId).findFirst().orElse(""));
                listDataCus.setCusName(resData.stream().filter(p -> p.getCusId().equals(cId)).map(OwePayBack::getCusName).findFirst().orElse(""));
                listDataCus.setCalTotalDate(resData.stream().filter(p -> p.getCusId().equals(cId)).map(OwePayBack::getCalTotalDate).findFirst().orElse(""));
                listCusDetails.add(listDataCus);
                List<OwePayDetails> listOwePayDetails = new ArrayList<>();
                for(OwePayBack listDataGroup : resData) {
                        if(listDataGroup.getCusId().equals(cId)) {
                            OwePayDetails  resOwePayDetailsGroup = new OwePayDetails();
                            resOwePayDetailsGroup.setCusId(listDataGroup.getCusId());
                            resOwePayDetailsGroup.setBillNo(listDataGroup.getBillNo());
                            resOwePayDetailsGroup.setPayDate(listDataGroup.getPayDate());
                            resOwePayDetailsGroup.setDayAmountCheck01(listDataGroup.getDayAmountCheck01());
                            resOwePayDetailsGroup.setDayAmountCheck02(listDataGroup.getDayAmountCheck02());
                            resOwePayDetailsGroup.setDayAmountCheck03(listDataGroup.getDayAmountCheck03());
                            resOwePayDetailsGroup.setDayAmountCheck04(listDataGroup.getDayAmountCheck04());
                            resOwePayDetailsGroup.setCurrency(listDataGroup.getCurrency());
                            listOwePayDetails.add(resOwePayDetailsGroup);
                        }
                }
                //sum footer details
                listDataCus.setListOwePayDetails(listOwePayDetails);
                List<GroupCurr> resGroupCurrArray = new ArrayList<>();
                for (GroupCurr listGroup : resGroupCurrList){
                    if (listGroup.getCusId().equals(cId)) {
                        GroupCurr groupCurrList = new GroupCurr();
                        groupCurrList.setCusId(listGroup.getCusId());
                        groupCurrList.setLakCurr(listGroup.getLakCurr());
                        groupCurrList.setUsdCurr(listGroup.getUsdCurr());
                        groupCurrList.setThbCurr(listGroup.getThbCurr());
                        groupCurrList.setCnyCurr(listGroup.getCnyCurr());
                        resGroupCurrArray.add(groupCurrList);
                    }
                    }
                listDataCus.setGroupCurrDetails(resGroupCurrArray);
            }
            //--sum header
            List<sumFooterGroupBelow> sumFooterGroupBel = new ArrayList<>();
            sumFooterGroupBelow resGroupFooterGroup = new sumFooterGroupBelow();
            sumTotalPayAmount01 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck01).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount01(numfm.format(sumTotalPayAmount01));
            sumTotalPayAmount02 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck02).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount02(numfm.format(sumTotalPayAmount02));
            sumTotalPayAmount03 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck03).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount03(numfm.format(sumTotalPayAmount03));
            sumTotalPayAmount04 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck04).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount04(numfm.format(sumTotalPayAmount04));
            sumFooterGroupBel.add(resGroupFooterGroup);
            result.setSumAmountTotalGroup(sumFooterGroupBel);
            result.setMessage("success");
            result.setStatus("00");
            result.setCustomerData(listCusDetails);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }
    //---get by date to date
    public OwePayBackRes listReportOweByDate(ResFromDateReq resFromDateReq){
        List<sumOweFooter> resgetSumfooter = new ArrayList<>();
        OwePayBackRes result = new OwePayBackRes();
        List<OwePayBack> resData = new ArrayList<>();
        OwePayBack listData = new OwePayBack();
        List<OweCustomerDetails> listCusDetails = new ArrayList<>();
        List<OwePayDetails> listDetails = new ArrayList<>();
        OwePayDetails DetailsFooterGroup = new OwePayDetails();
        double sumPayAmount01=0.0;
        double  sumPayAmount02=0.0;
        double  sumPayAmount03=0.0;
        double  sumPayAmount04=0.0;
        double sumTotalPayAmount01= 0.0;
        double sumTotalPayAmount02= 0.0;
        double sumTotalPayAmount03= 0.0;
        double sumTotalPayAmount04= 0.0;
        OweCustomerDetails listDataCus = new OweCustomerDetails();
        List<sumOweFooter> sumOweFooterList = new ArrayList<>();
        DecimalFormat numfm = new DecimalFormat("###,###.###");
        GroupCurr groupCurrList = new GroupCurr();
        List<GroupCurr> resGroupCurrList = new ArrayList<>();
        try {
            resData = oweDao.OweReportByDate(resFromDateReq);
            resgetSumfooter= oweDao.getSumfooterByDate(resFromDateReq);
            resGroupCurrList = oweDao.getGroupCurrSumfooter(resFromDateReq);
            log.info("show:"+resgetSumfooter);
            List<String> cusID = resData.stream().map(OwePayBack::getCusId).distinct().collect(Collectors.toList());
            List<String> cusIDSumFooterID = resgetSumfooter.stream().map(sumOweFooter::getCusId).distinct().collect(Collectors.toList());
            for(String cId : cusID){
                listDataCus = new OweCustomerDetails();
                listDataCus.setCusId(resData.stream().filter(p -> p.getCusId().equals(cId)).map(OwePayBack::getCusId).findFirst().orElse(""));
                listDataCus.setCusName(resData.stream().filter(p -> p.getCusId().equals(cId)).map(OwePayBack::getCusName).findFirst().orElse(""));
                listDataCus.setCalTotalDate(resData.stream().filter(p -> p.getCusId().equals(cId)).map(OwePayBack::getCalTotalDate).findFirst().orElse(""));
                listCusDetails.add(listDataCus);
                List<OwePayDetails> listOwePayDetails = new ArrayList<>();
                for(OwePayBack listDataGroup : resData) {
                    if(listDataGroup.getCusId().equals(cId)) {
                        OwePayDetails  resOwePayDetailsGroup = new OwePayDetails();
                        resOwePayDetailsGroup.setCusId(listDataGroup.getCusId());
                        resOwePayDetailsGroup.setBillNo(listDataGroup.getBillNo());
                        resOwePayDetailsGroup.setPayDate(listDataGroup.getPayDate());
                        resOwePayDetailsGroup.setDayAmountCheck01(listDataGroup.getDayAmountCheck01());
                        resOwePayDetailsGroup.setDayAmountCheck02(listDataGroup.getDayAmountCheck02());
                        resOwePayDetailsGroup.setDayAmountCheck03(listDataGroup.getDayAmountCheck03());
                        resOwePayDetailsGroup.setDayAmountCheck04(listDataGroup.getDayAmountCheck04());
                        listOwePayDetails.add(resOwePayDetailsGroup);
                    }
                }
                //sum footer details
                listDataCus.setListOwePayDetails(listOwePayDetails);
                for (GroupCurr listGroup : resGroupCurrList){
                    if (listGroup.getCusId().equals(cId)) {
                        groupCurrList = new GroupCurr();
                        resGroupCurrList  = new ArrayList<>();
                        groupCurrList.setCusId(listGroup.getCusId());
                        groupCurrList.setLakCurr(listGroup.getLakCurr());
                        groupCurrList.setUsdCurr(listGroup.getUsdCurr());
                        groupCurrList.setThbCurr(listGroup.getThbCurr());
                        groupCurrList.setCnyCurr(listGroup.getCnyCurr());
                        resGroupCurrList.add(groupCurrList);
                    }
                }
                listDataCus.setGroupCurrDetails(resGroupCurrList);
            }
            //--sum header
            List<sumFooterGroupBelow> sumFooterGroupBel = new ArrayList<>();
            sumFooterGroupBelow resGroupFooterGroup = new sumFooterGroupBelow();
            sumTotalPayAmount01 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck01).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount01(numfm.format(sumTotalPayAmount01));
            sumTotalPayAmount02 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck02).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount02(numfm.format(sumTotalPayAmount02));
            sumTotalPayAmount03 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck03).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount03(numfm.format(sumTotalPayAmount03));
            sumTotalPayAmount04 = resData.stream().distinct().map(OwePayBack::getDayAmountCheck04).collect(Collectors.summingDouble(Double::doubleValue));
            resGroupFooterGroup.setSumTotalPayAmount04(numfm.format(sumTotalPayAmount04));
            sumFooterGroupBel.add(resGroupFooterGroup);
            result.setSumAmountTotalGroup(sumFooterGroupBel);
            result.setMessage("success");
            result.setStatus("00");
            result.setCustomerData(listCusDetails);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }

}
