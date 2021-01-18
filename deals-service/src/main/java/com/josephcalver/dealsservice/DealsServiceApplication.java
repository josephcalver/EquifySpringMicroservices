package com.josephcalver.dealsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class DealsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealsServiceApplication.class, args);
	}

}
