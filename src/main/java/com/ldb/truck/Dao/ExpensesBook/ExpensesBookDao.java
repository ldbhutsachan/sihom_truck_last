package com.ldb.truck.Dao.ExpensesBook;

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
    public List<ExpenType> ListExpensesTypeAll() {
        List<ExpenType> result = new ArrayList<>();
        try{
            SQL = "select * from EXPENSE_TYPE";
            log.info("sql"+SQL);
            result = EBankJdbcTemplate.query(SQL,new ExpenTypeMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int storeExpensesType(ExpenTypeReq expenTypeReq) {
        try{
            SQL="insert into  EXPENSE_TYPE (TYPENAME,C_DATE) values(?,now())";
            List<Object> paraList = new ArrayList<>();
            paraList.add(expenTypeReq.getTypeName());
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
            SQL ="update  EXPENSE_TYPE set typename=? where key_id ='"+expenTypeReq.getKey_id()+"'";
            log.info("sql"+SQL);
            List<Object> paraList = new ArrayList<>();
            paraList.add(expenTypeReq.getTypeName());
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
            SQL = "select * from V_EXPENSES where EXPDATE between '"+expensesBookReq.getStartDate()+"' and '"+expensesBookReq.getEndDate()+"'";
           log.info("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL,new ExpensesBookMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<ExpensesBook> ListExpensesALL() {
        List<ExpensesBook> result = new ArrayList<>();
        try{
            SQL = "select * from V_EXPENSES";
            result = EBankJdbcTemplate.query(SQL,new ExpensesBookMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int storeExpenses(ExpensesBookReq expensesBookReq) {
        try{
            SQL="insert into EXPENSE (EXPNAME,EXPENSESTYPE,TOTAL,PERAMOUNT,AMOUNT,EXPDATE,C_DATE)" +
                    " values(?,?,?,?,?,?,now())";
            List<Object> paraList = new ArrayList<>();
            paraList.add(expensesBookReq.getExPName());
            paraList.add(expensesBookReq.getExPType());
            paraList.add(expensesBookReq.getToTal());
            paraList.add(expensesBookReq.getPerAmount());
            paraList.add(expensesBookReq.getAmount());
            paraList.add(expensesBookReq.getExpDate());
            return EBankJdbcTemplate.update(SQL,paraList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateExpenses(ExpensesBookReq expensesBookReq) {
        log.info("expensesBookReq:"+expensesBookReq.getExPType());
        log.info("expensesBookReq:"+expensesBookReq.getKey_id());
        try{
            SQL="update  EXPENSE set EXPNAME=?,EXPENSESTYPE=?,TOTAL=?,PERAMOUNT=?,AMOUNT=?,EXPDATE=? where key_id= ? ";
            log.info("sql"+SQL);
            List<Object> paraList = new ArrayList<>();
            paraList.add(expensesBookReq.getExPName());
            paraList.add(expensesBookReq.getExPType());
            paraList.add(expensesBookReq.getToTal());
            paraList.add(expensesBookReq.getPerAmount());
            paraList.add(expensesBookReq.getAmount());
            paraList.add(expensesBookReq.getExpDate());
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
