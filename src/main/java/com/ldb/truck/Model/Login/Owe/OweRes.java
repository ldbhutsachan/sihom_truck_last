package com.ldb.truck.Model.Login.Owe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OweRes {
    private String status;
    private String message;
    private List<Owe> data;
}
