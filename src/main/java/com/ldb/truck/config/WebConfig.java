package com.ldb.truck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/batery/**")
                .addResourceLocations("file:/images/batery/");
        registry.addResourceHandler("/images/car/**")
                .addResourceLocations("file:/images/car/");
        registry.addResourceHandler("/images/machine/**")
                .addResourceLocations("file:/images/machine/");
        registry.addResourceHandler("/images/staff/**")
                .addResourceLocations("file:/images/staff/");
    }
}
