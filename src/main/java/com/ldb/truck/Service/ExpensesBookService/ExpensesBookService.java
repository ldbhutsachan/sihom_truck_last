package com.ldb.truck.Service.ExpensesBookService;
import com.ldb.truck.Controller.ExpensesBookController;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.IncomePay.incomePayReq;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.ResFromDateReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Dao.ExpensesBook.ExpensesBookDao;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenType;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenTypeRes;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenTypeReq;

import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBook;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBookRes;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBookReq;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensesBookService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(ExpensesBookService.class);
    @Autowired ExpensesBookDao expensesBookDao;
    public ExpenTypeRes getExpensesType(ExpenTypeReq expenTypeReq){
        ExpenTypeRes result = new ExpenTypeRes();
        List<ExpenType> resData = new ArrayList<>();
        try{
            resData = expensesBookDao.ListExpensesType(expenTypeReq);
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
    //--get data all
    public ExpenTypeRes getExpensesTypeAll(ExpenTypeReq expenTypeReq){
        log.info("toKen=======================:"+expenTypeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(expenTypeReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        //expenTypeReq.setUserId(userId);
        expenTypeReq.setBranch(userBranchNo);

        //====================================================================
        ExpenTypeRes result = new ExpenTypeRes();
        List<ExpenType> resData = new ArrayList<>();
        try{
            resData = expensesBookDao.ListExpensesTypeAll(expenTypeReq);
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
    public ExpenTypeRes storeExpensesType(ExpenTypeReq expenTypeReq){
        log.info("toKen=======================:"+expenTypeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(expenTypeReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        expenTypeReq.setUserId(userId);
        expenTypeReq.setBranch(userBranchNo);

        //====================================================================
        ExpenTypeRes result = new ExpenTypeRes();
      int i = 0;
        try{
            i = expensesBookDao.storeExpensesType(expenTypeReq);
            if(i==0){
                result.setMessage("can't store data");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
    return result;
    }
    public ExpenTypeRes storeExpensesTypeUpdate(ExpenTypeReq expenTypeReq){
        log.info("toKen=======================:"+expenTypeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(expenTypeReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        expenTypeReq.setUserId(userId);
        expenTypeReq.setBranch(userBranchNo);
        //====================================================================
        ExpenTypeRes result = new ExpenTypeRes();
        int i = 0;
        try{
            i = expensesBookDao.updateExpensesType(expenTypeReq);
            if(i==0){
                result.setMessage("can't store data");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }
    public ExpenTypeRes storeExpensesTypeDel(ExpenTypeReq expenTypeReq){
        ExpenTypeRes result = new ExpenTypeRes();
        int i = 0;
        try{
            i = expensesBookDao.delExpensesType(expenTypeReq);
            if(i==0){
                result.setMessage("can't store data");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }
    //--
    public ExpensesBookRes getExpensesBook(ResFromDateReq expensesBookReq){
        ExpensesBookRes result = new ExpensesBookRes();
        List<ExpensesBook> resData = new ArrayList<>();
        try{
            resData = expensesBookDao.ListExpenses(expensesBookReq);
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
    public ExpensesBookRes getExpensesBookAll(incomePayReq incomePayReq){
        log.info("toKen=======================:"+incomePayReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(incomePayReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        incomePayReq.setUserId(userId);
        incomePayReq.setBranch(userBranchNo);

        //====================================================================

        DecimalFormat numfm = new DecimalFormat("###,###,###");
        ExpensesBookRes result = new ExpensesBookRes();
        List<ExpensesBook> resData = new ArrayList<>();
        try{
            resData = expensesBookDao.ListExpensesALL(incomePayReq);
            //==========================count pay and income=============================
            List<String> refIds = resData.stream().map(ExpensesBook::getStatus).distinct().collect(Collectors.toList());
            Double countTotalPay = 0.0;
            Double countTotalIncome=0.0;
            for (String refIdsNo : refIds ){
                if (refIdsNo.equals("PAY")){
                    countTotalPay =  resData.stream().filter(p -> p.getStatus().equals(refIdsNo)).map(ExpensesBook::getToTal).collect(Collectors.summingDouble(Double::doubleValue));
                }
                else if (refIdsNo.equals("INCOME")){
                    countTotalIncome =  resData.stream().filter(p -> p.getStatus().equals(refIdsNo)).map(ExpensesBook::getToTal).collect(Collectors.summingDouble(Double::doubleValue));
                }
            }
            //=================count pay - Income =======================================
            Double countIncome_Pay = countTotalIncome-countTotalPay;
            //==========================count pay and income=============================
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
            result.setTotalPay(numfm.format(countTotalPay));
            result.setTotalIncome(numfm.format(countTotalIncome));
            result.setTotalIncome_PayAll(numfm.format(countIncome_Pay));

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setData(resData);
        }
        return result;
    }
    public ExpensesBookRes storeExpenses(ExpensesBookReq expensesBookReq){
        log.info("toKen=======================:"+expensesBookReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(expensesBookReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        expensesBookReq.setUserId(userId);
        expensesBookReq.setBranch(userBranchNo);

        //====================================================================
        ExpensesBookRes result = new ExpensesBookRes();
        int i = 0;
        try{
            i = expensesBookDao.storeExpenses(expensesBookReq);
            if(i==0){
                result.setMessage("can't store data");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }
    //--update
    public ExpensesBookRes storeExpensesUpdate(ExpensesBookReq expensesBookReq){
        log.info("toKen=======================:"+expensesBookReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(expensesBookReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        expensesBookReq.setUserId(userId);
        expensesBookReq.setBranch(userBranchNo);

        //====================================================================
        ExpensesBookRes result = new ExpensesBookRes();
        int i = 0;
        try{
            i = expensesBookDao.updateExpenses(expensesBookReq);
            if(i==0){
                result.setMessage("can't update data");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }
    //--dele
    public ExpensesBookRes storeExpensesDel(ExpensesBookReq expensesBookReq){
        ExpensesBookRes result = new ExpensesBookRes();
        int i = 0;
        try{
            i = expensesBookDao.delExpenses(expensesBookReq);
            if(i==0){
                result.setMessage("can't store data");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }

}
