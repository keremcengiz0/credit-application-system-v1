package com.keremcengiz0.creditapplicationsystem;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditApplicationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditApplicationSystemApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String description,
								 @Value("${application-version}") String version) {
		return new OpenAPI()
				.info(new Info()
						.title("Credit Application System")
						.version(version)
						.description(description)
						.license(new License().name("Credit Application System Licence")));
	}

}
