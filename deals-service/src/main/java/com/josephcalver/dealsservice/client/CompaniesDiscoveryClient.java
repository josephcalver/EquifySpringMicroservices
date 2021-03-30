package com.josephcalver.dealsservice.client;

import com.josephcalver.dealsservice.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CompaniesDiscoveryClient {

    @Autowired
    RestTemplate restTemplate;

    public Company getCompany(String companyId) {

        ResponseEntity<Company> restExchange = restTemplate.exchange(
                "http://companiesservice/v1/companies/{companyId}",
                HttpMethod.GET, null, Company.class, companyId);

        return restExchange.getBody();
    }

}
