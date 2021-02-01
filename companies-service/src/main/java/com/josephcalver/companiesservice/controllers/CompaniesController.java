package com.josephcalver.companiesservice.controllers;

import com.josephcalver.companiesservice.models.Company;
import com.josephcalver.companiesservice.services.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CompaniesController {

    @Autowired
    private CompaniesService companiesService;

//    public CompaniesController(CompaniesService companiesService) {
//        this.companiesService = companiesService;
//    }

    @GetMapping("/v1/companies")
    public Iterable<Company> getAllCompanies() {
        return companiesService.getAllCompanies();
    }

    @GetMapping("/v1/companies/{companyId}")
    public Optional<Company> getCompany(@PathVariable String companyId) {
        return companiesService.getCompany(companyId);
    }

    @PostMapping("v1/companies")
    public Company createCompany(@RequestBody Company company) {
        return companiesService.createCompany(company);
    }

    @PutMapping("v1/companies/{companyId}")
    public Company updateCompany(@PathVariable String companyId, @RequestBody Company company) {
        return companiesService.updateCompany(companyId, company);
    }

    @DeleteMapping("v1/companies/{companyId}")
    public void deleteCompany(@PathVariable String companyId) {
        companiesService.deleteCompany(companyId);
    }

}
