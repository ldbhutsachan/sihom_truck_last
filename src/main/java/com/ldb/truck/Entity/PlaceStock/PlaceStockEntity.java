package com.ldb.truck.Entity.PlaceStock;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "stock_house") // Update this name to match your actual DB table name
public class PlaceStockEntity {

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

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "key_id")
    private String borNo;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "userid")
    private String userId;
}