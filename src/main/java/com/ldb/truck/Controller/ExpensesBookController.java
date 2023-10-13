package com.ldb.truck.Controller;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenType;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenTypeRes;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenTypeReq;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBook;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBookReq;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBookRes;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Service.ExpensesBookService.ExpensesBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("${base_url}")
public class ExpensesBookController {
    private static final Logger log = LogManager.getLogger(ExpensesBookController.class);
    @Autowired
    ExpensesBookService expensesBookService;
    @CrossOrigin(origins = "*")
    @PostMapping("/getExpensesType.service")
    public ExpenTypeRes listTxn(@RequestBody  ExpenTypeReq expenTypeReq){
        ExpenTypeRes result =new ExpenTypeRes();
        try
        {
            result = expensesBookService.getExpensesType(expenTypeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getExpensesTypeAll.service")
    public ExpenTypeRes getExpensesTypeAll(){
        ExpenTypeRes result =new ExpenTypeRes();
        try
        {
            result = expensesBookService.getExpensesTypeAll();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeExpensesType.service")
    public ExpenTypeRes storeExpensesType(@RequestBody  ExpenTypeReq expenTypeReq){
        ExpenTypeRes result =new ExpenTypeRes();
        try
        {
            result = expensesBookService.storeExpensesType(expenTypeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeExpensesTypeUpdate.service")
    public ExpenTypeRes storeExpensesTypeUpdate(@RequestBody ExpenTypeReq expenTypeReq){
        log.info("req:"+expenTypeReq);
        log.info("req:"+expenTypeReq.getTypeName());
        ExpenTypeRes result =new ExpenTypeRes();
        try
        {
            result = expensesBookService.storeExpensesTypeUpdate(expenTypeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeExpensesTypeDel.service")
    public ExpenTypeRes storeExpensesTypeDel(@RequestBody  ExpenTypeReq expenTypeReq){
        ExpenTypeRes result =new ExpenTypeRes();
        try
        {
            result = expensesBookService.storeExpensesTypeDel(expenTypeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--ExpensesBookRes
    @CrossOrigin(origins = "*")
    @PostMapping("/getExpenses.service")
    public ExpensesBookRes listTxnExpenses(@RequestBody ResFromDateReq expensesBookReq){
        ExpensesBookRes result =new ExpensesBookRes();
        try
        {
            result = expensesBookService.getExpensesBook(expensesBookReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getExpensesAll.service")
    public ExpensesBookRes listTxnExpensesAll(){
        ExpensesBookRes result =new ExpensesBookRes();
        try
        {
            result = expensesBookService.getExpensesBookAll();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeExpenses.service")
    public ExpensesBookRes storeExpenses(@RequestBody  ExpensesBookReq expensesBookReq){
        ExpensesBookRes result =new ExpensesBookRes();
        try
        {
            result = expensesBookService.storeExpenses(expensesBookReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--report expenses storeExpensesUpdate
    @CrossOrigin(origins = "*")
    @PostMapping("/storeExpensesUpdate.service")
    public ExpensesBookRes storeExpensesUpdate(@RequestBody  ExpensesBookReq expensesBookReq){
        ExpensesBookRes result =new ExpensesBookRes();
        try
        {
            result = expensesBookService.storeExpensesUpdate(expensesBookReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeExpensesDel.service")
    public ExpensesBookRes storeExpensesDel(@RequestBody  ExpensesBookReq expensesBookReq){
        ExpensesBookRes result =new ExpensesBookRes();
        try
        {
            result = expensesBookService.storeExpensesDel(expensesBookReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
}
