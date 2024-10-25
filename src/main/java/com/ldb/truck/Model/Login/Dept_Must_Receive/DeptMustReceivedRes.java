package com.ldb.truck.Model.Login.Dept_Must_Receive;

import com.ldb.truck.Model.Login.DocumentStorage.DocumentStorageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeptMustReceivedRes {
    private String status;
    private String message;
    private List<DeptMustReceivedModel> data;
//    private sumFooterGroupAsset resFooter;
}
