package com.ldb.truck.Service.location;

import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Dao.Login.ImploginDao;
import com.ldb.truck.Model.Login.location.LocationOut;
import com.ldb.truck.Model.Login.location.LocationReq;
import com.ldb.truck.Model.Login.location.LocationRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    ImpCustomerDao impCustomerDao;

    public LocationRes getAllLocation (){

        LocationRes result = new LocationRes();
        List<LocationOut> listData = new ArrayList<>();

        try {

            listData = impCustomerDao.getAllLocatino();

            if(listData.size() < 1){

                result.setMessage("01");
                result.setStatus("data not found ");
                return result;
            }

            result.setStatus("00");
            result.setMessage("success");
            result.setData(listData);
            return  result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus("data not found ");
            return result;

        }

    }

    public LocationRes getLocationById (LocationReq locationReq){

        LocationRes result = new LocationRes();
        List<LocationOut> listData = new ArrayList<>();

        try {

            listData = impCustomerDao.getLocationById(locationReq.getId());

            if(listData.size() < 1){

                result.setMessage("01");
                result.setStatus("data not found ");
                return result;
            }

            result.setStatus("00");
            result.setMessage("success");
            result.setData(listData);
            return  result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus("data not found ");
            return result;

        }

    }

    public LocationRes StoreLocation (LocationReq locationReq){

        LocationRes result = new LocationRes();
        int i = 0;

        try {

            i = impCustomerDao.StoreLocation(locationReq);

            if(i == 0){

                result.setMessage("01");
                result.setStatus(" can not Store Location ");
                return result;
            }

            result.setMessage("00");
            result.setStatus("success");
            return result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus(" can not Store Location ");
            return result;
        }
    }

    public LocationRes UpdateLocation (LocationReq locationReq){

        LocationRes result = new LocationRes();
        int i = 0;

        try {

            i = impCustomerDao.UpdateLocation(locationReq);

            if(i == 0){

                result.setMessage("01");
                result.setStatus(" can not Update Location ");
                return result;
            }

            result.setMessage("00");
            result.setStatus("success");
            return result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus(" can not Update Location ");
            return result;
        }
    }

    public LocationRes DeleteLocation (LocationReq locationReq){

        LocationRes result = new LocationRes();
        int i = 0;

        try {

            i = impCustomerDao.DeleteLocation(locationReq);

            if(i == 0){

                result.setMessage("01");
                result.setStatus(" can not Delete Location ");
                return result;
            }

            result.setMessage("00");
            result.setStatus("success");
            return result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage("01");
            result.setStatus(" can not Delete Location ");
            return result;
        }
    }

}
