package com.ldb.truck.Dao.Details;

import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Performance.v_performance;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.TogenTheCodeReq;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceDeptCode;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNo;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getQuotationCode;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import java.util.List;

public interface DetailsDao  {
    List<Details> ListDetailsPopup();
    public List<getInvoiceDeptCode> showINVcode ();
    public List<getQuotationCode> showKKTcode ();
    List<getInvoiceNo> showMaxLahudPoyLod(TogenTheCodeReq togenTheCodeReq);
    List<Details> ListDetails();
    List<Details> ListDetailsById(DetailsReq detailsReq);
    public int updateData(DetailsReq detailsReq);
    public List<Details> delData(DetailsReq detailsReq);
    public int saveData(DetailsReq detailsReq);
    public int UpdateHeader(DetailsReq detailsReq);
    public int UpdateFooter(DetailsReq detailsReq);
    public int updateStaff01(DetailsReq detailsReq);
    public int updateStaff02(DetailsReq detailsReq);



}
