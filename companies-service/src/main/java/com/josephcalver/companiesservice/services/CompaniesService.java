package com.josephcalver.companiesservice.services;

import com.josephcalver.companiesservice.exceptions.CompanyNotFoundException;
import com.josephcalver.companiesservice.models.Company;
import com.josephcalver.companiesservice.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompaniesService {

    @Autowired
    private CompaniesRepository companiesRepository;

    public CompaniesService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    public Iterable<Company> getAllCompanies() {
        return companiesRepository.findAll();
    }

    public Company getCompany(String companyId) {
        return companiesRepository.findByCompanyId(companyId);
    }

    public Company createCompany(Company company) {
        return companiesRepository.save(company);
    }

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

    public void deleteCompany(String companyId) {
        companiesRepository.deleteByCompanyId(companyId);
    }
}
