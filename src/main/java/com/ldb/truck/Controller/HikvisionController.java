package com.ldb.truck.Controller;

import com.ldb.truck.Service.HikvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
@RequiredArgsConstructor
public class HikvisionController {
    private final HikvisionService hikvisionService;

    @CrossOrigin(origins = "*")
    @PostMapping("/event")
    public ResponseEntity<?> receiveEvent(@RequestBody String rawData) {
        return ResponseEntity.ok(hikvisionService.processEvent(rawData));
    }
}
