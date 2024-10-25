package com.ldb.truck.Model.Login.DocumentStorage;

import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeModel;
import com.ldb.truck.Model.Login.AssetsOffice.sumFooterGroupAsset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentStorageRes {
    private String status;
    private String message;
    private List<DocumentStorageModel> data;
//    private sumFooterGroupAsset resFooter;
}
