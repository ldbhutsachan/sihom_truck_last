package com.ldb.truck.Model.Login.Branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchRes {
    private String status;
    private String message;
    public List<Branch> data;

}
