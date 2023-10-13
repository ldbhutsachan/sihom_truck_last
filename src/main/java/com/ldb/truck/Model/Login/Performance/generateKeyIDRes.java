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
public class generateKeyIDRes {
    public String message;
    public String status;
    public List<generateKeyID> data;
}
