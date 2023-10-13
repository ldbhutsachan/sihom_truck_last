package com.ldb.truck.Dao.Truck;

import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.Truck.*;
import com.ldb.truck.Model.Login.delivery.DeliveryOut;
import com.ldb.truck.Model.Login.delivery.DeliveryReq;

import java.util.List;

public interface TruckDao {

    List<TruckOut> getAllTruck();
    List<TruckOut> getTruckById( TruckReq truckReq);
    int StoreTruck (TruckReq truckReq);
    int updateTruck (TruckReq truckReq);
    int deleteTruck (int truckId);

    List<DeliveryOut> getAllDelivery();
    int StoreDelivery (DeliveryReq deliveryReq);
    int UpdateDelivery(DeliveryReq deliveryReq);
    int DeleteDelivery(String id);

    public List<TruckDetails> ReportGive(ResFromDateReq resFromDateReq);
    List<TruckDetailsGroupDataDetails> ReportGiveDetails(TruckDetailsReq truckDetailsReq);


}
