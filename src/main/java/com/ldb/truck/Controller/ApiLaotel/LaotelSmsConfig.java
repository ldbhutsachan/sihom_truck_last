package com.ldb.truck.Controller.ApiLaotel;

import org.springframework.beans.factory.annotation.Value;

public class LaotelSmsConfig {
    @Value("${jkurfS6hxJiyf9Ag6rAodo7AiU1rEda6}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
