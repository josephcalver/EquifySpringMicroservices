//package com.josephcalver.dealsservice.repositories;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Value("${example.property}")
//    private String exampleProp;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String myDriverClassName;
//
//    @Value("${spring.datasource.url}")
//    private String myDbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String myDbUsername;
//
//    @Value("${spring.datasource.password}")
//    private String myDbPassword;
//
//    @Bean
//    public DataSource datasource() {
//        return DataSourceBuilder.create()
//                .driverClassName(myDriverClassName)
//                .url(myDbUrl)
//                .username(myDbUsername)
//                .password(myDbPassword)
//                .build();
//    }
//
//}
