package com.josephcalver.dealsservice.repository;

import com.josephcalver.dealsservice.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRedisRepository extends CrudRepository<Company, String> {

//    @Transactional
//    public Company findByCompanyId(String companyId);
//
//    @Transactional
//    public void deleteByCompanyId(String companyId);

}
