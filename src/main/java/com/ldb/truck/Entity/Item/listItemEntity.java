package com.ldb.truck.Entity.Item;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name="v_items_list")
public class listItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private String itemId; // Auto-increment primary key

    @Column(name = "item_name", length = 255, nullable = false)
    private String item_name;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "size", nullable = false)
    private String size; // Size might need renaming if backticks are causing issues

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "branch_no", nullable = false)
    private Integer branchNo;

    @Column(name = "khid", nullable = false)
    private String khid;

    @Column(name = "khno", nullable = false)
    private String khno;

    @Column(name = "khname", nullable = false)
    private String khname;


    @Column(name = "bor_no", nullable = false)
    private String borNo;

    @Column(name = "bor_name", nullable = false)
    private String borName;

    @Column(name = "token", nullable = false)
    private String toKen;
}
