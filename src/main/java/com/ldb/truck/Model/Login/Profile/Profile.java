package com.ldb.truck.Model.Login.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private String userId;
    private String userName;
    private String role;
    private String branchNo;
    private String branchName;
}
