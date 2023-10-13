package com.ldb.truck.Model.Login.Performance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class v_performanceRes {
    private String status;
    private String message;
    private List<v_performance> data;
}
