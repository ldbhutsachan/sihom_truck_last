package com.ldb.truck.Dao.Customer;

import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.customer.CustomerOut;
import com.ldb.truck.Model.Login.customer.CustomerReq;
import com.ldb.truck.Model.Login.location.LocationOut;
import com.ldb.truck.Model.Login.location.LocationReq;
import com.ldb.truck.Model.Login.product.ProductOut;
import com.ldb.truck.Model.Login.product.ProductReq;
import com.ldb.truck.Model.Login.staft.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;

public interface CustomerDao {
    public int UpdateStaftNoUpdate(stafReq stafReq);
    List<StaffDetails> ListDetailStaff(StaffPayReq staffPayReq);
    List<StaffPay> ListStaffPay(StaffPayReq staffPayReq);
    List<ReportStaff> ListStaffPaydetailsByStaffId(StaffPayReq staffPayReq);
    List<ReportStaff> ListWaiyPaymentStaff();
    List<ReportStaff> ReportStaffPeymnet(ResFromDateReq resFromDateReq);
    public int paymentStaff(StaffPaymentReq staffPaymentReq) throws ParseException;
    public int paymentStaffUpdate(StaffPaymentReq staffPaymentReq) throws ParseException;
    List<CustomerOut> getAllCustomer(CustomerReq custoerReq);
    List<CustomerOut> getCustomerById(CustomerReq custoerReq);
    int StoreCustomer (CustomerReq custoerReq);
    int UpdateCustomer (CustomerReq custoerReq);
    int deleteCustomer (String id);
    List<staftOut> getChooseStaft01(stafReq stafReq);
    public List<staftOut> getChooseStaft02();
    List<staftOut> getAllStaft();
    List<staftOut> getStaftById( String id);
    int StoreStaft(stafReq stafReq);
    int UpdateStaft(stafReq stafReq);
    int deleteStaft(String id);
    List<ProductOut> getAllProduct (ProductReq productReq);
    List<ProductOut> getProductById (ProductReq productReq);
    int StoreProduct(ProductReq productReq);
    int UpdateProduct(ProductReq productReq);
    int deleteProduct(String id);
    List<LocationOut> getAllLocatino(LocationReq locationReq);
    List<LocationOut> getLocationById( LocationReq locationReq);
    int StoreLocation (LocationReq locationReq);
    int UpdateLocation (LocationReq locationReq);
    int DeleteLocation (LocationReq locationReq);


}
