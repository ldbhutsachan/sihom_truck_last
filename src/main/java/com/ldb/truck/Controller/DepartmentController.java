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
    public ResponseEntity<DataResponse> getAdjustment(  //  method
                                                        @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.getAdjustments(body));
    }
    // สร้างกะใหม่
    @CrossOrigin(origins = "*")
    @PostMapping("/shift/create")
    public ResponseEntity<DataResponse> createShift(
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.createShift(body));
    }

    // ดูกะทั้งหมด
    @CrossOrigin(origins = "*")
    @PostMapping("/shift/get")
    public ResponseEntity<DataResponse> getShifts(
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.getShifts(body));
    }

    // กำหนดกะให้ staff
    @CrossOrigin(origins = "*")
    @PostMapping("/shift/assign-staff")
    public ResponseEntity<DataResponse> assignShift(
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.assignShift(body));
    }

    // ดูกะของ staff
    @CrossOrigin(origins = "*")
    @PostMapping("/shift/get-staff-shift")
    public ResponseEntity<DataResponse> getStaffShift(
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(departmentService.getStaffShift(body));
    }

}