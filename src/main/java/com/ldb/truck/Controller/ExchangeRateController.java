package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRateReq;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRateRes;
import com.ldb.truck.Service.ExchangeService.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class ExchangeRateController {
  @Autowired
    ExchangeService exchangeService;
    @CrossOrigin(origins = "*")
    @PostMapping("/getExchange.service")
    public ExchangeRateRes getExchange(@RequestBody ExchangeRateReq exchangeRateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        try{
          result= exchangeService.listExchangeRate(exchangeRateReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeExchange.service")
    public ExchangeRateRes storeExchange(@RequestBody ExchangeRateReq rateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        try{
            result= exchangeService.storeExchangeRate(rateReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateExchange.service")
    public ExchangeRateRes updateExchange(@RequestBody ExchangeRateReq rateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        try{
            result= exchangeService.updateExchangeRate(rateReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/delExchange.service")
    public ExchangeRateRes delExchange(@RequestBody ExchangeRateReq rateReq){
        ExchangeRateRes result =new ExchangeRateRes();
        try{
            result= exchangeService.delExchangeRate(rateReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
