package com.ldb.truck.Model.Login.Login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserLoginOut {
    private String staftName;
    private String role;
    private String staftId;

    private String status;
    private String toKen;

}
