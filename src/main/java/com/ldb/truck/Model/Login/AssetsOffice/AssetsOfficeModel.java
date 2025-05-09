package com.ldb.truck.Model.Login.AssetsOffice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetsOfficeModel {
    private String key_id;
    private String code;
    private String img;
    private String name;
    private String group_type;
    private String owner;
    private String qty;
    private String branch_office;
    private String currency;
    private String department;
    private String brand;
    private String model;
    private String location_room;
    private String date_getin;
    private String unit;
    private String colors;
    private Double price;
    private String dateExpire;
    private String life_service;
//    private double total_price_THB;
//    private double total_price_USD;
//    private double total_price_LAK;
}
