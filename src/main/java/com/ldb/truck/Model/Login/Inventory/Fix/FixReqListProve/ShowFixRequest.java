package com.ldb.truck.Model.Login.Inventory.Fix.FixReqListProve;

import com.ldb.truck.Model.Login.Inventory.Fix.ShowFixModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowFixRequest {
    private String status;
    private String message;
    private List<ReqListOfFixModel> data;
}
