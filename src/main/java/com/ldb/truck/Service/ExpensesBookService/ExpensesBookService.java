package com.ldb.truck.Service.ExpensesBookService;
import com.ldb.truck.Controller.ExpensesBookController;
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
import java.util.ArrayList;
import java.util.List;
@Service
public class ExpensesBookService {
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
    public ExpenTypeRes getExpensesTypeAll(){
        ExpenTypeRes result = new ExpenTypeRes();
        List<ExpenType> resData = new ArrayList<>();
        try{
            resData = expensesBookDao.ListExpensesTypeAll();
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
    public ExpensesBookRes getExpensesBookAll(){
        ExpensesBookRes result = new ExpensesBookRes();
        List<ExpensesBook> resData = new ArrayList<>();
        try{
            resData = expensesBookDao.ListExpensesALL();
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
    public ExpensesBookRes storeExpenses(ExpensesBookReq expensesBookReq){
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
