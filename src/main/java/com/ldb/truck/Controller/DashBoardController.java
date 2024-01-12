package com.ldb.truck.Controller;

import com.ldb.truck.Model.DashBoard.DashBoardReq;
import com.ldb.truck.Model.DashBoard.DashBoardRes;
import com.ldb.truck.Service.DashBoardService.DashBoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class DashBoardController {
    private static final Logger logger = LogManager.getLogger(DashBoardController.class);
    @Autowired
    DashBoardService dashBoardService;
    @CrossOrigin(origins = "*")

    @PostMapping("/DashBoard")
    public DashBoardRes showDashBoard(@RequestBody DashBoardReq dashBoardReq){
        logger.info("============================DashBoard================");
        DashBoardRes result = new DashBoardRes();
        try{
            result = dashBoardService.DashBoard(dashBoardReq);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
        return result;
    }


}
