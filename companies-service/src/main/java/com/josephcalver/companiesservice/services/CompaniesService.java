package com.josephcalver.companiesservice.services;

import brave.ScopedSpan;
import brave.Tracer;
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
import java.util.Optional;

@Service
public class CompaniesService {

    private static final Logger logger = LoggerFactory.getLogger(CompaniesService.class);

    @Autowired
    private CompaniesRepository companiesRepository;

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    @Autowired
    Tracer tracer;

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
    public Optional<Company> getCompany(String companyId) {

        Optional<Company> company;

        ScopedSpan customSpan = tracer.startScopedSpan("getCompanyDbCall");

        try {
            company = companiesRepository.findById(companyId);
            simpleSourceBean.publishCompanyChange("GET", companyId);
            if (!company.isPresent()) {
                String message = String.format("Unable to find company with ID: %s", companyId);
                logger.error(message);
            }
            logger.debug("Retrieved Company information: " + company.get().toString());
        } finally {
            customSpan.tag("peer.service", "postgres");
            customSpan.annotate("Client received");
            customSpan.finish();
        }
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
        simpleSourceBean.publishCompanyChange("CREATED", newCompany.getId());
        return newCompany;
    }

    @CircuitBreaker(name = "companiesService", fallbackMethod = "companyDataUnavailable")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "companyDataUnavailable")
    public Company updateCompany(String companyId, Company company) {

        Optional<Company> existingCompany = companiesRepository.findById(companyId);

        if (existingCompany == null) {
            throw new CompanyNotFoundException(companyId);
        }

        existingCompany.get().setCompanyName(company.getCompanyName());
        existingCompany.get().setFounded(company.getFounded());
        existingCompany.get().setCountry(company.getCountry());
        existingCompany.get().setRegion(company.getRegion());
        existingCompany.get().setSector(company.getSector());
        existingCompany.get().setEnterpriseValue(company.getEnterpriseValue());

        Company updatedCompany = companiesRepository.save(existingCompany.get());

        simpleSourceBean.publishCompanyChange("UPDATED", updatedCompany.getId());

        return updatedCompany;
    }

    @CircuitBreaker(name = "companiesService")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL)
    public void deleteCompany(String companyId) {
        companiesRepository.deleteById(companyId);
        simpleSourceBean.publishCompanyChange("DELETED", companyId);
    }

}
