package com.ldb.truck.Controller;

import com.ldb.truck.Service.HikvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${base_url}")
@RequiredArgsConstructor
public class HikvisionController {
    private final HikvisionService hikvisionService;

    @CrossOrigin(origins = "*")
    @PostMapping("/event")
    public ResponseEntity<?> receiveEvent(@RequestParam Map<String, String> params) {

        try {
            System.out.println("🔥 HIT API FROM HIKVISION 🔥");

            String eventLog = params.get("event_log");

            if (eventLog == null) {
                System.out.println("No event_log found");
                return ResponseEntity.ok("NO DATA");
            }

            System.out.println("EVENT_LOG: " + eventLog);

            return ResponseEntity.ok(hikvisionService.processEvent(eventLog));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("ERROR");
        }
    }
}
