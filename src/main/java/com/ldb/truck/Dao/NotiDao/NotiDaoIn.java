package com.ldb.truck.Dao.NotiDao;

import com.ldb.truck.Model.Login.Noti.*;

import java.util.List;

public interface NotiDaoIn{
    List<NotiDetails> notiDetails();
    List<NotiInvoice> notiInvoice();
    List<NotiPerFormace> noTiPer();

    List<NotiDetails> notiDetal();
    List<NotiInvoice> Invoice();
    List<NotiPerFormace> noPer();
    List<OweNoti> oweNoti();
    List<notiPay> notiPayList();
}
