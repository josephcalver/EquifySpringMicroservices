package com.josephcalver.dealsservice.repository;

import com.josephcalver.dealsservice.model.Deal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealsRepository extends CrudRepository<Deal, String> {

    public Deal findByDealId(String dealId);
    public void deleteByDealId(String dealId);

}
