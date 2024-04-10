package com.ldb.truck.Dao.NotiDao;

import com.ldb.truck.Model.Login.Noti.*;

import java.util.List;

public interface NotiDaoIn{
    List<NotiDetails> notiDetails(NoticeReq noticeReq);
    List<NotiInvoice> notiInvoice(NoticeReq noticeReq);
    List<NotiPerFormace> noTiPer(NoticeReq noticeReq);

    List<NotiDetails> notiDetal(NoticeReq noticeReq);
    List<NotiInvoice> Invoice(NoticeReq noticeReq);
    List<NotiPerFormace> noPer(NoticeReq noticeReq);
    List<OweNoti> oweNoti(NoticeReq noticeReq);
    List<notiPay> notiPayList(NoticeReq noticeReq);
}
