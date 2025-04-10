package com.ldb.truck.Dao.Details;

import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Inventory.OfferPaper.PoCodeModel;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.*;
import com.ldb.truck.Model.Login.Details.DetailsReq;

import java.util.List;

public interface DetailsDao  {
    List<Details> ListDetailsPopup();
    public List<getInvoiceDeptCode> showINVcode ();
    public List<GetOfferCode> GenOfferCodeBorhin (TogenTheCodeReq togenTheCodeReq);
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
    public List<GetOfferCode> showMaxOfferCode (TogenTheCodeReq togenTheCodeReq);
    public List<PoCodeModel> showMaxPOCodeNew (TogenTheCodeReq togenTheCodeReq);

}
