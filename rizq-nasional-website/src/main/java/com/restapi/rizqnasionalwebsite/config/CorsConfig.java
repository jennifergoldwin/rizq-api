package com.restapi.rizqnasionalwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public CorsConfig() {
        System.out.println("CorsConfig bean is being created");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("Registry is being created");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // You can specify specific origins instead of "*"
                .allowedMethods("*")
                .allowedHeaders("*");
                // .allowCredentials(true)
                // .maxAge(3600);
    }
    
}
