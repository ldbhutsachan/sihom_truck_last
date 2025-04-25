package com.ldb.truck.Model.Login.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkModel {
    private String id;
    private String source;
    private String target;
    private String type;
}
