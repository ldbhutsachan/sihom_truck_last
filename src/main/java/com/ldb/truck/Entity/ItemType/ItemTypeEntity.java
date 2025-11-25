package com.ldb.truck.Entity.ItemType;

import lombok.Data;

import javax.lang.model.element.Name;
import javax.persistence.*;
//@Entity
//@Data
//@Table(name = "item_type")
//public class ItemTypeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name ="itemtypeid")
//    private String itemtypeid;
//
//    @Column(name = "itemtype_Name")
//    private String itemTypeName;
//}

@Entity
@Data
@Table(name = "item_type")
public class ItemTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="itemtypeid")
    private Long itemtypeid; // ✅ Long สำหรับ INT DB

    @Column(name = "itemtype_Name")
    private String itemTypeName;

    @Column(name = "bansi_use")
    private String bansiUse;

    @Transient
    private String toKen; // สำหรับส่ง token จาก client
}




