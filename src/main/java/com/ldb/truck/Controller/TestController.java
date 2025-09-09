package com.ldb.truck.Controller;

import com.ldb.truck.Model.testModel.ItemTestModel;
import com.ldb.truck.Model.testModel.RequestTest;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Service.Vee.TestService;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Profile.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller สำหรับ API branch summary
 * - ใช้ POST method
 * - ตรวจสอบ token
 * - Return response แบบ DataResponse
 */
@RestController
//@RequestMapping("/api")  // base URL = /api
//@RequestMapping("${base_url}")
@RequestMapping("${base_url}")
public class TestController {

    private final TestService testService; // Service layer
    private final ProfileDao profileDao;   // DAO สำหรับตรวจสอบ token

    // Constructor injection → Spring Boot จะ inject bean ให้อัตโนมัติ
    public TestController(TestService testService, ProfileDao profileDao) {
        this.testService = testService;
        this.profileDao = profileDao;
    }

    @CrossOrigin(origins = "*") // อนุญาตให้ frontend จากทุก domain เรียก API
    @PostMapping("/branch-summary") // POST API endpoint = /api/branch-summary
    public ResponseEntity<DataResponse> getBranchSummary(
            @RequestBody RequestTest request // รับ JSON body: token, detailId, billNo
    ) {
        DataResponse response = new DataResponse(); // สร้าง response object

        // ------------------------
        // 1️⃣ ตรวจสอบ token
        // ------------------------
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(request.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // ------------------------
        // 2️⃣ ดึง userId และ role จาก token
        // ------------------------
        String userId = userProfiles.get(0).getUserName();
        String role = userProfiles.get(0).getRole();

        try {
            // ------------------------
            // 3️⃣ เรียก Service Layer ดึงข้อมูล branch summary
            // ------------------------
            List<ItemTestModel> data = testService.getBranchSummary();

            // ------------------------
            // 4️⃣ กำหนดค่า response (มาตรฐาน)
            // ------------------------
            response.setStatus("S");          // S = Success
            response.setMessage("Success");   // ข้อความ success
            response.setDataResponse(data);      // ส่ง data กลับ client

        } catch (Exception e) {
            // ------------------------
            // 5️⃣ กรณี error
            // ------------------------
            response.setStatus("EE");         // EE = Error
            response.setMessage("Data Error !!"); // ข้อความ error
        }

        // ------------------------
        // 6️⃣ ส่ง Response กลับ client
        // ------------------------
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
