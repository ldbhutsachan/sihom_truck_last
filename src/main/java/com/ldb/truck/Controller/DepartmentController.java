package com.ldb.truck.Controller;

import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Staffs.AdjustmentApproveDTO;
import com.ldb.truck.Model.Staffs.AdjustmentRequestDTO;
import com.ldb.truck.Service.staft.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${base_url}")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @CrossOrigin(origins = "*")
    @PostMapping("/createDepartment")
    public ResponseEntity<DataResponse> create(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.createDepartment(body));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getDepartment")
    public ResponseEntity<DataResponse> getDepartment(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.getDepartment(body));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateDepartment")
    public ResponseEntity<DataResponse> update(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.updateDepartment(body));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/position/create")
    public ResponseEntity<DataResponse> createPosition(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.createPosition(body));
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/position/get")
    public ResponseEntity<DataResponse> getPosition(
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.getPosition(body));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/position/update")
    public ResponseEntity<DataResponse> updatePosition(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.updatePosition(body));
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/adjustment/request")
    public ResponseEntity<DataResponse> request(
            @RequestBody AdjustmentRequestDTO dto) {
        return ResponseEntity.ok(departmentService.requestAdjustment(dto));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/adjustment/approve")
    public ResponseEntity<DataResponse> approve(
            @RequestBody AdjustmentApproveDTO dto) {
        return ResponseEntity.ok(departmentService.approveAdjustment(dto));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/adjustment/get")
    public ResponseEntity<DataResponse> getAdjustment(  // ✅ เปลี่ยนชื่อ method
                                                        @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.getAdjustments(body));
    }

}