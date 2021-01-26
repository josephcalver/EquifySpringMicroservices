package com.josephcalver.companiesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class CompaniesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompaniesServiceApplication.class, args);
	}

}
