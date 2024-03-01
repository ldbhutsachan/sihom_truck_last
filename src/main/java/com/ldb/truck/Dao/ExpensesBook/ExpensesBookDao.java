package com.ldb.truck.Dao.ExpensesBook;

import com.ldb.truck.Model.IncomePay.incomePayReq;
import com.ldb.truck.Model.Login.ExpensesBook.*;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.RowMapper.ExpensesBook.ExpenTypeMapper;
import com.ldb.truck.RowMapper.ExpensesBook.ExpensesBookMapper;
import com.ldb.truck.Service.ExpensesBookService.ExpensesBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class ExpensesBookDao implements ExpensesBookImDao{
    private static final Logger log = LogManager.getLogger(ExpensesBookImDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL ="";
    @Override
    public List<ExpenType> ListExpensesType(ExpenTypeReq expenTypeReq) {
        List<ExpenType> result = new ArrayList<>();
        try{
        SQL = "select * from EXPENSE_TYPE where key_id like '%"+expenTypeReq.getKey_id()+"%'";
            result = EBankJdbcTemplate.query(SQL,new ExpenTypeMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<ExpenType> ListExpensesTypeAll(ExpenTypeReq expenTypeReq) {
        List<ExpenType> result = new ArrayList<>();
        try{
            SQL = "select * from EXPENSE_TYPE b inner join LOGIN a on b.userId =a.KEY_ID where a.BRANCH='"+expenTypeReq.getBranch()+"'";
            log.info("sql0000000000:"+SQL);
            result = EBankJdbcTemplate.query(SQL,new ExpenTypeMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int storeExpensesType(ExpenTypeReq expenTypeReq) {
        try{
            SQL="insert into  EXPENSE_TYPE (TYPENAME,C_DATE,userId) values(?,now(),?)";
            List<Object> paraList = new ArrayList<>();
            paraList.add(expenTypeReq.getTypeName());
            paraList.add(expenTypeReq.getUserId());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateExpensesType(ExpenTypeReq expenTypeReq) {
        List<ExpenType> result = new ArrayList<>();
        try{
            SQL ="update  EXPENSE_TYPE set typename=? ,userId=? where key_id ='"+expenTypeReq.getKey_id()+"'";
            log.info("sql"+SQL);
            List<Object> paraList = new ArrayList<>();
            paraList.add(expenTypeReq.getTypeName());
            paraList.add(expenTypeReq.getUserId());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }
    @Override
    public int delExpensesType(ExpenTypeReq expenTypeReq) {
        List<ExpenType> result = new ArrayList<>();
        try{
            SQL ="delete FROM  EXPENSE_TYPE where key_id ='"+expenTypeReq.getKey_id()+"'";
            log.info("sql"+SQL);
            List<Object> paraList = new ArrayList<>();
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<ExpensesBook> ListExpenses(ResFromDateReq expensesBookReq) {
        List<ExpensesBook> result = new ArrayList<>();
        try{
            SQL = "select * from V_EXPENSES where EXPDATE between '"+expensesBookReq.getStartDate()+"' and '"+expensesBookReq.getEndDate()+"' AND BRANCH = '"+expensesBookReq.getBranch()+"'";
           log.info("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL,new ExpensesBookMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<ExpensesBook> ListExpensesALL(incomePayReq incomePayReq) {
        List<ExpensesBook> result = new ArrayList<>();
        try{
             if (incomePayReq.getStartDate() != null && incomePayReq.getEndDate()!= null && incomePayReq.getStatus().equals("0")){
                SQL = "select * from V_EXPENSES where  EXPDATE between '"+incomePayReq.getStartDate()+"' and '"+incomePayReq.getEndDate()+"' AND BRANCH = '"+incomePayReq.getBranch()+"'";
                log.info( "Q2"+SQL);
            }
            else if (incomePayReq.getStartDate() != null && incomePayReq.getEndDate()!= null && !incomePayReq.getStatus().equals("0")){
                SQL = "select * from V_EXPENSES where STATUS='"+incomePayReq.getStatus()+"' and EXPDATE between '"+incomePayReq.getStartDate()+"' and '"+incomePayReq.getEndDate()+"' AND BRANCH = '"+incomePayReq.getBranch()+"'";
                log.info( "Q3"+SQL);
            }
            else if (incomePayReq.getStartDate() == null && incomePayReq.getEndDate()== null && !incomePayReq.getStatus().equals("0")){
                SQL = "select * from V_EXPENSES where STATUS='"+incomePayReq.getStatus()+"' AND BRANCH = '"+incomePayReq.getBranch()+"'";
                log.info( "Q4"+SQL);

            }
             else if (incomePayReq.getStartDate() == null && incomePayReq.getEndDate()== null && incomePayReq.getStatus().equals("0")){
                 SQL = "select * from V_EXPENSES  where BRANCH = '"+incomePayReq.getBranch()+"'";
                 log.info( "Q5"+SQL);

             }
            result = EBankJdbcTemplate.query(SQL,new ExpensesBookMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int storeExpenses(ExpensesBookReq expensesBookReq) {
        try{
            SQL="insert into EXPENSE (EXPNAME,EXPENSESTYPE,PERAMOUNT,TOTAL,AMOUNT,EXPDATE,C_DATE,REF_NO,STATUS,userId)" +
                    " values(?,?,?,?,?,?,now(),?,?,?)";
            List<Object> paraList = new ArrayList<>();
            paraList.add(expensesBookReq.getExPName());
            paraList.add(expensesBookReq.getExPType());
            paraList.add(expensesBookReq.getPerAmount());
            paraList.add(expensesBookReq.getToTal());
            paraList.add(expensesBookReq.getAmount());
            paraList.add(expensesBookReq.getExpDate());
//            paraList.add(expensesBookReq.getCDate());
            paraList.add(expensesBookReq.getRef_NO());
            paraList.add(expensesBookReq.getStatus());
            paraList.add(expensesBookReq.getUserId());

            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateExpenses(ExpensesBookReq expensesBookReq) {
        try{
            SQL="update  EXPENSE set EXPNAME=?,EXPENSESTYPE=?,TOTAL=?,PERAMOUNT=?,AMOUNT=?,EXPDATE=?,REF_NO=?,STATUS=? ,userId=? where key_id= ? ";
            log.info("sql"+SQL);
            List<Object> paraList = new ArrayList<>();
            paraList.add(expensesBookReq.getExPName());
            paraList.add(expensesBookReq.getExPType());
            paraList.add(expensesBookReq.getToTal());
            paraList.add(expensesBookReq.getPerAmount());
            paraList.add(expensesBookReq.getAmount());
            paraList.add(expensesBookReq.getExpDate());
            paraList.add(expensesBookReq.getRef_NO());
            paraList.add(expensesBookReq.getStatus());
            paraList.add(expensesBookReq.getUserId());
            paraList.add(expensesBookReq.getKey_id());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int delExpenses(ExpensesBookReq expensesBookReq) {
        try{
            SQL="delete from EXPENSE  where key_id = ? ";
            log.info("sql"+SQL);
            List<Object> paraList = new ArrayList<>();
            paraList.add(expensesBookReq.getKey_id());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
