package com.josephcalver.dealsservice.repository;

import com.josephcalver.dealsservice.model.Deal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DealsRepository extends CrudRepository<Deal, String> {

    @Transactional
    public Deal findByDealId(String dealId);

    @Transactional
    public void deleteByDealId(String dealId);

}
