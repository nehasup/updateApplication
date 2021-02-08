package com.upskillutoday.crmRoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class UpskillcrmApplication {

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5252", "http://localhost:4000"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
//		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
//				"Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin",
//				"Cache-Control", "Content-Type"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

//	@PostConstruct
//	public void init(){
//		TimeZone.setDefault(TimeZone.getTimeZone("UT"));   // It will set UTC timezone
//		System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/app/v1/login").allowedOrigins("http://localhost:4200");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(UpskillcrmApplication.class, args);
	}
}
