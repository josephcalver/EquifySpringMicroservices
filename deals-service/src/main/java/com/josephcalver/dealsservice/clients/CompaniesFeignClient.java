package com.josephcalver.dealsservice.clients;

import com.josephcalver.dealsservice.models.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("companiesservice")
public interface CompaniesFeignClient {

    @GetMapping(value = "/v1/companies/{companyId}", consumes = "application/json")
    public Company getCompany(@PathVariable String companyId);

}
