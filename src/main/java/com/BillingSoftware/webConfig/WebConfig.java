package com.BillingSoftware.webConfig; // Adjust the package to match your project structure

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map the /uploads/** URL path to the uploads directory
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
