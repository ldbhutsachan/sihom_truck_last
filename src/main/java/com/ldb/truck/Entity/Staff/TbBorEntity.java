package com.ldb.truck.Entity.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_bors")
public class TbBorEntity {
    @Id
    @Column(name = "key_id")
    private Integer keyId;

    @Column(name = "b_name")
    private String bName;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;  // 'A' = Active
}
