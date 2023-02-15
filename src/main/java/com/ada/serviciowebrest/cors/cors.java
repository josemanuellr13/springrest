package com.ada.serviciowebrest.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class cors { 

    @Bean
    public WebMvcConfigurer CORSConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                 registry.addMapping("/*")
                 .allowedOriginPatterns("")
                 .allowedMethods("GET", "POST", "PUT", "DELETE")
                 .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                 .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                 .allowCredentials(true)
                 .maxAge(3600);

            }
        };
    }
}
