package com.brooklyn;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App REST APIs",
				description = "Spring Boot Blog App REST APIs Documentation",
				version = "v0.0.0",
				contact = @Contact(
							name = "Brooklyn Lee",
							email = "thinhmnsd2002@gmail.com",
							url = "https://www.facebook.com/thinhmnsd2002"
						),
				license = @License(
							name = "Apache",
							url = "https://www.facebook.com/thinhmnsd2002"
						)
				
				),
		externalDocs = @ExternalDocumentation(
					description = "Spring Boot Blog App Documentation",
					url = "https://github.com/ThinhLe30/Spring-Boot-Rest-Api---Blog-Application"
				)
)
public class SpringBootRestApiBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiBlogApplication.class, args);
	}
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
