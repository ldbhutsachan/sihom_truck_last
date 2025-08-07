package com.ldb.truck.Entity.Bor;
import lombok.Data;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "v_bor") // Ensure this matches your actual DB table name
public class BorViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id") // Match the exact DB column name
    private int keyId;

    @Column(name = "b_name")
    private String bName;

    @Column(name = "b_tel")
    private String bTel;

    @Column(name = "b_location")
    private String bLocation;

    @Column(name = "email")
    private String email;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "create_date")
    private String createDate; // Consider using `LocalDateTime` for better time handling

    @Column(name = "brname")
    private String sortName;

    @Column(name = "brand_no")
    private String brandNo;

    @Column(name = "status")
    private String status;

    @Column(name = "type_bor")
    private String typeBor;
}