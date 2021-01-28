package com.josephcalver.companiesservice.services;

import com.josephcalver.companiesservice.events.source.SimpleSourceBean;
import com.josephcalver.companiesservice.exceptions.CompanyNotFoundException;
import com.josephcalver.companiesservice.models.Company;
import com.josephcalver.companiesservice.repositories.CompaniesRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompaniesService {

    private static final Logger logger = LoggerFactory.getLogger(CompaniesService.class);

    @Autowired
    private CompaniesRepository companiesRepository;

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public CompaniesService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @CircuitBreaker(name = "companiesService", fallbackMethod = "companiesDataUnavailable")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "companiesDataUnavailable")
    public Iterable<Company> getAllCompanies() {
        return companiesRepository.findAll();
    }

    private Iterable<Company> companiesDataUnavailable() {
        Company dummyCompany = new Company();
        dummyCompany.setCompanyName("Companies data is currently unavailable");
        List<Company> dummyList = new ArrayList<Company>();
        dummyList.add(dummyCompany);
        return dummyList;
    }

    @CircuitBreaker(name = "companiesService", fallbackMethod = "companyDataUnavailable")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "companyDataUnavailable")
    public Company getCompany(String companyId) {
        Company company = companiesRepository.findByCompanyId(companyId);
        simpleSourceBean.publishCompanyChange("GET", company.getCompanyId());
        return company;
    }

    private Company companyDataUnavailable() {
        Company dummy = new Company();
        dummy.setCompanyName("Company data is currently unavailable");
        return dummy;
    }

    @CircuitBreaker(name = "companiesService", fallbackMethod = "companyDataUnavailable")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "companyDataUnavailable")
    public Company createCompany(Company company) {
        Company newCompany = companiesRepository.save(company);
        simpleSourceBean.publishCompanyChange("CREATED", newCompany.getCompanyId());
        return newCompany;
    }

    @CircuitBreaker(name = "companiesService", fallbackMethod = "companyDataUnavailable")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "companyDataUnavailable")
    public Company updateCompany(String companyId, Company company) {

        Company existingCompany = companiesRepository.findByCompanyId(companyId);

        if (existingCompany == null) {
            throw new CompanyNotFoundException(companyId);
        }

        existingCompany.setCompanyName(company.getCompanyName());
        existingCompany.setFounded(company.getFounded());
        existingCompany.setCountry(company.getCountry());
        existingCompany.setRegion(company.getRegion());
        existingCompany.setSector(company.getSector());
        existingCompany.setEnterpriseValue(company.getEnterpriseValue());

        Company updatedCompany = companiesRepository.save(existingCompany);

        simpleSourceBean.publishCompanyChange("UPDATED", updatedCompany.getCompanyId());

        return updatedCompany;
    }

    @CircuitBreaker(name = "companiesService")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL)
    public void deleteCompany(String companyId) {
        companiesRepository.deleteByCompanyId(companyId);
        simpleSourceBean.publishCompanyChange("DELETED", companyId);
    }

}
