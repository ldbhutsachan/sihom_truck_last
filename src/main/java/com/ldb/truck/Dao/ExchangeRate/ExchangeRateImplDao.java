package com.ldb.truck.Dao.ExchangeRate;

import java.text.ParseException;
import java.util.List;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRate;
import com.ldb.truck.Model.Login.ExchangeRate.ExchangeRateReq;

public interface ExchangeRateImplDao {
    List<ExchangeRate> listExchangeRate(ExchangeRateReq exchangeRateReq);
    public int storeExchange(ExchangeRateReq exchangeRateReq) throws ParseException;
    public int updateExchange(ExchangeRateReq exchangeRateReq) throws ParseException;
    public int delExchange(ExchangeRateReq exchangeRateReq);

}
