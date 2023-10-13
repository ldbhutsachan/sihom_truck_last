package com.ldb.truck.Model.Login.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    private String status;
    private String message;
    private List<UserLogin> data;
}
