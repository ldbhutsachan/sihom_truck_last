package com.ldb.truck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/batery/**")
                .addResourceLocations("file:src/main/resources/images/batery/");
        registry.addResourceHandler("/images/car/**")
                .addResourceLocations("file:src/main/resources/images/car/");
        registry.addResourceHandler("/images/machine/**")
                .addResourceLocations("file:src/main/resources/images/machine/");
        registry.addResourceHandler("/images/staff/**")
                .addResourceLocations("file:src/main/resources/images/staff/");
    }
}
