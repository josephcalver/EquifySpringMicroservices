package com.josephcalver.companiesservice.repositories;

import com.josephcalver.companiesservice.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRepository extends CrudRepository<Company, String> {

    public Company findByCompanyId(String companyId);
    public void deleteByCompanyId(String companyId);

}
