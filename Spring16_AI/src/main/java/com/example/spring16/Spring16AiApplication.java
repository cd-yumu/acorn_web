package com.example.spring16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySource({"classpath:custom.properties", "classpath:gemini.properties"})
@SpringBootApplication
public class Spring16AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring16AiApplication.class, args);
	}

}
