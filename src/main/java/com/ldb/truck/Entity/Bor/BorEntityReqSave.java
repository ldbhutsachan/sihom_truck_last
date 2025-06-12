package com.ldb.truck.Entity.Bor;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorEntityReqSave {
        private String bname;
        private String btel;
        private String blocation;
        private String email;
        private String userId;
        private String sortName;
        private String brandNo;
        private String status;
        private String toKen;
        private Long keyId;
    }