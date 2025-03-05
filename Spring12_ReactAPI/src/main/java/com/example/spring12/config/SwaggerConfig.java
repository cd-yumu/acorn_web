package com.example.spring12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		
		Info info = new Info()
				.title("Open API 문서다")
				.version("9999999.0")
				.description("배 불러 너무나");
		
		return new OpenAPI().info(info);
	}
}
