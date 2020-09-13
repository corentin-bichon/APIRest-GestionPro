package com.Project.MoveInMed.GestionPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Start the application
 */
@SpringBootApplication
@EnableSwagger2
public class GestionProApplication {

	public static void main(String[] args) {

		SpringApplication.run(GestionProApplication.class, args);
	}

}
