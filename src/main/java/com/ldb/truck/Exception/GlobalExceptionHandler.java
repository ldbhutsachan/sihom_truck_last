package com.ldb.truck.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", ex.getMessage());   // ✅ ส่ง message กลับไปให้ client
        error.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(error); // ✅ 400 Bad Request ไม่ใช่ 500
    }
}