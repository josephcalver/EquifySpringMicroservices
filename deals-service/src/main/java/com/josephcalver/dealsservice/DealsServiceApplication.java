package com.josephcalver.dealsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableFeignClients
@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class DealsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealsServiceApplication.class, args);
	}

}
