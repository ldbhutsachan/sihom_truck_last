package com.ldb.truck.Model.Login.userData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class user {
    private String key_id;
    private String token;
    private String branch;
    private String username;
    private String location;
    private String email;
    private String tel;
}
