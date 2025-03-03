package com.jb.petTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PetTrackerApplication {
	@Configuration
	public class WebConfig implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/api/**") // Enable CORS for all API endpoints
	                .allowedOrigins("http://localhost:8080") // Your Flutter app's URL
	                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
	                .allowedHeaders("*") // Allow all headers
	                .allowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
	    }
	}
	public static void main(String[] args) {
		SpringApplication.run(PetTrackerApplication.class, args);
	}

}
