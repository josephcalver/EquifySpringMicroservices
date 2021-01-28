package com.josephcalver.dealsservice.client;

import com.josephcalver.dealsservice.model.Company;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CompaniesRestTemplateClient {

    @Autowired
    private KeycloakRestTemplate restTemplate;

    public Company getCompany(String companyId){
        ResponseEntity<Company> restExchange =
                restTemplate.exchange(
                        "http://gatewayserver:5555/companiesservice/v1/companies/{companyId}",
                        HttpMethod.GET,
                        null, Company.class, companyId);

        return restExchange.getBody();
    }

//    @Autowired
//    RestTemplate restTemplate;
//
//    public Company getCompany(String companyId) {
//        ResponseEntity<Company> restExchange = restTemplate.exchange(
//                        "http://companiesservice/v1/companies/{companyId}",
//                        HttpMethod.GET, null, Company.class, companyId);
//
//        return restExchange.getBody();
//    }

}