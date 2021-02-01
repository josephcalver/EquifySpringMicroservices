package com.josephcalver.companiesservice.repositories;

import com.josephcalver.companiesservice.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CompaniesRepository extends CrudRepository<Company, String> {

    @Transactional
    public Optional<Company> findById(String companyId);

    @Transactional
    public void deleteById(String companyId);

}
