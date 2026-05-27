package com.ldb.truck.Entity.Bansi;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pay_type_group")
@Data
public class PayTypeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid")
    private Integer gid;

    @Column(name = "group_name", nullable = false, length = 255)
    private String groupName;

    @Column(name = "date_create", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreate;

    @Column(name = "pid", nullable = false)
    private Integer pid;
}