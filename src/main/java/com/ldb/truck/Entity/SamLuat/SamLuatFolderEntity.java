package com.ldb.truck.Entity.SamLuat;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SAM_LUAT_FOLDER")
public class SamLuatFolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "STATUS")
    private String status = "A";
}
