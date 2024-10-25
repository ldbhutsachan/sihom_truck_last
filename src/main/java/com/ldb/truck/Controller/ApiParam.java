package com.ldb.truck.Controller;

public @interface ApiParam {
    boolean required();

    String value();

    String name();
}
