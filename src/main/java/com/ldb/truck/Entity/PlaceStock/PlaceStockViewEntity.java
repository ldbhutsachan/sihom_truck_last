package com.ldb.truck.Entity.PlaceStock;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "v_stock_house") // Update this name to match your actual DB table name
public class PlaceStockViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khid")
    private Long khId; // Changed from int(100) to Integer for auto-increment handling

    @Column(name = "khno", length = 200)
    private String khNo;

    @Column(name = "khname", length = 200)
    private String khName;

    @Column(name = "sole")
    private String sole;

    @Column(name = "solestep", length = 500)
    private String soleStep;

    @Column(name = "blockno", length = 200)
    private String blockNo;

    @Column(name = "bor_no")
    private String borNo;

    @Column(name = "bo_name")
    private String borName;

    @Column(name = "tel")
    private String tel;

    @Column(name = "location")
    private String location;

    @Column(name = "branch_no")
    private String branchNo;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "userid")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "update_date")
    private String updateDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_byname")
    private String updateByName;

    @Column(name = "stock_status")
    private String stockSatus;

    @Column(name = "not_for_alai")
    private String notForAlai;

}