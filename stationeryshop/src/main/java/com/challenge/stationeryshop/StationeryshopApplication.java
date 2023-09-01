package com.challenge.stationeryshop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Papelaria API", version = "1", description = "Api para gerenciamento de papelaria"))
public class StationeryshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationeryshopApplication.class, args);
	}

}
