package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Branch.Branch;
import com.ldb.truck.Model.Login.Branch.BranchRes;
import com.ldb.truck.Model.Login.User.UserReq;
import com.ldb.truck.Model.Login.User.UserRes;
import com.ldb.truck.Service.BranchService.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class BranchController {
    @Autowired
    BranchService branchService;

    @CrossOrigin(origins = "*")
    @PostMapping("/getShowBranch.service")
    public BranchRes getShowBranch(@RequestBody BrachReq brachReq) {
        BranchRes result = new BranchRes();
        try {
            result = branchService.getShowBranch(brachReq);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exception");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeBranch.service")
    public BranchRes storeUserLogin (@RequestBody BrachReq userReq){
        BranchRes result = new BranchRes();
        try{
            result = branchService.saveBranch(userReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
