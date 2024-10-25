package com.ldb.truck.Dao.AssetOfficeDAOs;

import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeModel;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeReq;

import java.text.ParseException;
import java.util.List;

public interface AssetsInterface {
    public List<AssetsOfficeModel> listAssetsOfficeDAOs (AssetsOfficeReq assetsOfficeReq);
    public List<AssetsOfficeModel> listAssetsOfficeDAOsDetailById (AssetsOfficeReq assetsOfficeReq);
    public int delAssetOfficeDAOs (AssetsOfficeReq assetsOfficeReq);
    public int InsertAssetsOfficeDAOs (AssetsOfficeReq assetsOfficeReq) throws ParseException;
    public int UpdateAssetsOfficeDAOs (AssetsOfficeReq assetsOfficeReq) throws ParseException;
    public int updateAssetsOfficeUppicHaveData (AssetsOfficeReq assetsOfficeReq) throws ParseException;
}
