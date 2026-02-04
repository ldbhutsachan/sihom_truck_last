package com.ldb.truck.Model.ReportAllStock;

import lombok.Data;

import java.time.LocalDateTime;
import java.sql.Timestamp;

@Data
public class ItemHisModel {

    private Integer detailId;        // detail_id
    private String billNo;            // bill_no
    private Integer borId;            // key_id
    private String bName;             // b_name
    private Integer houseId;          // houseid
    private String houseName;            // khname

    private String type;
    private Integer itemId;           // item_id
    private String itemName;          // item_name
    private String currency;          // currency
    private Integer exchangeRate;     // exchange_rate

    private Integer qty;              // qty
    private Float price;              // price

    private String savebyId;          // saveby_id
    private String saveby;             // saveby
    private String savedate;   // savedate

    private String buybyId;            // buyby_id
    private String buyby;              // buyby
    private String buydate;     // buydate

    private String approvebyId;        // approveby_id
    private String approveby;          // approveby
    private String approvedate;     // approvedate

    private String acceptId;        // approveby_id
    private String acceptby;          // approveby
    private String acceptDate;     // approvedate

    private String status;             // status

    private Integer shopeId;           // shope_id
    private String shopName;           // shop_name

    private String typeOfOrder;        // type_of_order
    private String datePay;            // date_pay
    private String itemArriveDate;     // item_arrive_date
    private String payStatus;          // pay_status
}
