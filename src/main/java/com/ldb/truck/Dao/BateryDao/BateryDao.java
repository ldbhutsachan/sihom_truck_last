package com.ldb.truck.Dao.BateryDao;

import com.ldb.truck.Model.Login.Batery.Batery;
import com.ldb.truck.Model.Login.Batery.BateryReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BateryDao {

    public List<Batery> getBateryAll(BateryReq bateryReq);
    public int saveBatery(BateryReq bateryReq);
    int UpdateBatery(BateryReq bateryReq);
    int DelBatery(BateryReq bateryReq);

}
