package com.ldb.truck.Model.Login.Noti;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotiRes {
    private String status;
    private String message;
    private String totalRow;

    private String notiDetails;

    private String notiInvoice;

    private String  notiPerForMance;
    private String payStatus;

    private String totalOwe;
//    private List<NotiDetails> notiDetails;
//    private List<NotiInvoice> notiInvoice;
//    private List<NotiPerFormace> notiPerForMance;
//    private List<OweNoti>  totalOwe;
}
