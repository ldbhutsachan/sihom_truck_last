package com.ldb.truck.Service.ExchangeService;

import com.ldb.truck.Dao.ExchangeRate.ExchangeRateDao;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRateReq;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRateRes;
import org.apache.coyote.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeService {
    private static final Logger log = LogManager.getLogger(ExchangeService.class);
    @Autowired
    ExchangeRateDao exchangeRateDao;
    public ExchangeRateRes listExchangeRate(ExchangeRateReq exchangeRateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        List<ExchangeRate>  listData = new ArrayList<>();
        try{
            listData= exchangeRateDao.listExchangeRate(exchangeRateReq);
            if(listData.size() ==0){
                result.setStatus("01");
                result.setMessage("No Data");
                result.setData(listData);
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("success");
                result.setData(listData);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //----store data
    public ExchangeRateRes storeExchangeRate(ExchangeRateReq exchangeRateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        int checkdata = 0;
        try{
            checkdata= exchangeRateDao.storeExchange(exchangeRateReq);
            if(checkdata ==0){
                result.setStatus("01");
                result.setMessage("Can't Save  Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage(" Save  Data success");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public ExchangeRateRes updateExchangeRate(ExchangeRateReq exchangeRateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        int checkdata = 0;
        try{
            checkdata= exchangeRateDao.updateExchange(exchangeRateReq);
            if(checkdata ==0){
                result.setStatus("01");
                result.setMessage("Can't Update  Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("Update  Data success");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public ExchangeRateRes delExchangeRate(ExchangeRateReq exchangeRateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        int checkdata = 0;
        try{
            checkdata= exchangeRateDao.delExchange(exchangeRateReq);
            if(checkdata ==0){
                result.setStatus("01");
                result.setMessage("Can't del  Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("delete  Data success");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

