package com.ldb.truck.Model.Login.Inventory.Report_Stock;

import lombok.Data;

import java.util.List;

@Data
public class ReportStockGroupRes {
    private String item_id;
    private String item_name;
    private String unit;
    private String img;

    private Double total_qty_stock;
    private Double total_qty_in;
    private Double total_qty_out;
    private Double totalyordyokma;

    private List<ReportstockModel> listDetail;
}
