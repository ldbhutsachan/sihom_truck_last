package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.Branch.BrachReq;
import com.ldb.truck.Model.Login.Branch.Branch;
import com.ldb.truck.Model.Login.Branch.BranchRes;
import com.ldb.truck.Model.Login.Task.TaskReq;
import com.ldb.truck.Model.Login.Task.TaskRes;
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
//    new show branch api
@CrossOrigin(origins = "*")
@PostMapping("/getShowBranchNew2024.service")
public BranchRes getShowBranchNew(@RequestBody BrachReq brachReq) {
    BranchRes result = new BranchRes();
    try {
        result = branchService.getShowBranchnewService(brachReq);
    } catch (Exception e) {
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exception");
    }
    return result;
}
    //    update branch
    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateBranch.service")
    public BranchRes UpdateBranch (@RequestBody BrachReq userReq){
        BranchRes result = new BranchRes();
        try{
            result = branchService.UpdateBranch(userReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //    del branch
    @CrossOrigin(origins = "*")
    @PostMapping("/DelBranchs.service")
    public BranchRes DelBranchs (@RequestBody BrachReq userReq){
        BranchRes result = new BranchRes();
        try{
            result = branchService.DeleteBranch(userReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
//    del task
@CrossOrigin(origins = "*")
@PostMapping("/DelTasks.service")
public TaskRes DelTasks (@RequestBody TaskReq taskReq){
    TaskRes result = new TaskRes();
    try{
        result = branchService.DeleteTasks(taskReq);
    }catch (Exception e){
        e.printStackTrace();
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
