package com.ldb.truck.Dao.Payment;
import com.ldb.truck.Model.Login.Payment.Customer_Payment;
import com.ldb.truck.Model.Login.Payment.Customer_PaymentReq;
import com.ldb.truck.RowMapper.Payment.Customer_PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class CustomerPaymentDao implements CustomerPaymentInfDao{
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    String SQL="";
    @Override
    public List<Customer_Payment> listGetCustomer() {
        List<Customer_Payment> result = new ArrayList<>();
        try
        {
             SQL = "select * from V_CHOOSE_PAYMENT where status='N' order by PERFORMANCEBILLNO asc";
            System.out.println("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL , new Customer_PaymentMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
            return result;
    }

    @Override
    public List<Customer_Payment> listGetCustomerByInfo(Customer_PaymentReq customerPaymentReq) {
        List<Customer_Payment> result = new ArrayList<>();
        try
        {
             SQL = "select * from V_CHOOSE_PAYMENT where status='N' and PERFORMANCEBILLNO like '%"+customerPaymentReq.getBillNo()+"%' " +
                    "OR CUSTOMER_ID like '%"+customerPaymentReq.getBillNo()+"%' OR CUSTOMER_NAME like '%"+customerPaymentReq.getBillNo()+"%' order by PERFORMANCEBILLNO asc";
            System.out.println("sql:"+SQL);
            result = EBankJdbcTemplate.query(SQL , new Customer_PaymentMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
