package com.ldb.truck.Dao.Payment;

import java.util.List;
import com.ldb.truck.Model.Login.Payment.Customer_Payment;
import com.ldb.truck.Model.Login.Payment.Customer_PaymentReq;
public interface CustomerPaymentInfDao {
    List<Customer_Payment>  listGetCustomer();
    List<Customer_Payment>  listGetCustomerByInfo(Customer_PaymentReq customerPaymentReq);

    //public int CreateInvoice ();
}
