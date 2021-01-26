package com.josephcalver.companiesservice.services;

import com.josephcalver.companiesservice.exceptions.CompanyNotFoundException;
import com.josephcalver.companiesservice.models.Company;
import com.josephcalver.companiesservice.repositories.CompaniesRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompaniesService {

    @Autowired
    private CompaniesRepository companiesRepository;

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
        return companiesRepository.findByCompanyId(companyId);
    }

    private Company companyDataUnavailable() {
        Company dummy = new Company();
        dummy.setCompanyName("Company data is currently unavailable");
        return dummy;
    }

    @CircuitBreaker(name = "companiesService", fallbackMethod = "companyDataUnavailable")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "companyDataUnavailable")
    public Company createCompany(Company company) {
        return companiesRepository.save(company);
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

        return companiesRepository.save(existingCompany);
    }

    @CircuitBreaker(name = "companiesService")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL)
    public void deleteCompany(String companyId) {
        companiesRepository.deleteByCompanyId(companyId);
    }

}
