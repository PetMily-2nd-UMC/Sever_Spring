package com.petmily.petmily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class PetmilyApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:application-aws.yml";
	public static void main(String[] args) {
		new SpringApplicationBuilder(PetmilyApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
		//SpringApplication.run(PetmilyApplication.class, args);
	}

}
