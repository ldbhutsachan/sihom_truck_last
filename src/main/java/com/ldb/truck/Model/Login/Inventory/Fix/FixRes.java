package com.ldb.truck.Model.Login.Inventory.Fix;

import com.ldb.truck.Model.Login.Inventory.Items.Items;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FixRes {
    private String status;
    private String message;
    private List<FixModel> data;
}
