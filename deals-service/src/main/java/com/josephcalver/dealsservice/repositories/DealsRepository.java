package com.josephcalver.dealsservice.repositories;

import com.josephcalver.dealsservice.models.Deal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealsRepository extends CrudRepository<Deal, String> {

    public Deal findByDealId(String dealId);
    public void deleteByDealId(String dealId);

}
