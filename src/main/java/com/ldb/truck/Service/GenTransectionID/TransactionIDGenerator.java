package com.ldb.truck.Service.GenTransectionID;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TransactionIDGenerator {
    public static String generateTransactionID() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        String formattedDate = formatter.format(now);
//        return formattedDate;
        return "KK" + formattedDate;
    }
}
