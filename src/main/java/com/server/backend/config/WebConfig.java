package com.server.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for web-specific Spring settings, including CORS configuration.
 * This class defines bean methods to set up global CORS settings for the application.
 */
@Configuration
public class WebConfig {
    /**
     * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
     * Allows web applications hosted on specified origins to interact with the application.
     * 
     * @return WebMvcConfigurer with CORS mappings configured.
     */
    @Bean
    WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://kth-recruitment-frontend-3b6226a80ec7.herokuapp.com/")
                        .allowedMethods(HttpMethod.GET.name(),
                                HttpMethod.POST.name()).allowedHeaders(HttpHeaders.CONTENT_TYPE,
                                HttpHeaders.AUTHORIZATION);
            }
        };
    }
}
/*    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();
        corsConfig.addAllowedOrigin("http://localhost:3000");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }*/