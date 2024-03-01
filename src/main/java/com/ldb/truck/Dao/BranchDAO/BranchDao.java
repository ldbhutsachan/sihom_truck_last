package com.ldb.truck.Dao.BranchDAO;

import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Branch.Branch;

import java.util.List;

public interface BranchDao {
    public List<Branch> getBranch(BrachReq brachReq);
    public int saveDataBranch(BrachReq brachReq);
    public int updateDataBranch(BrachReq brachReq);
    public int delDataBranch(BrachReq brachReq);
}
