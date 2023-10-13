package com.ldb.truck.Model.Login.RevertModel;

import com.ldb.truck.Model.Login.Performance.Performance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceModelRes {
    private String status;
    private String message;
     private List<Performance> data;
}
