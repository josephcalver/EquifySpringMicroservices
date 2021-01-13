package com.josephcalver.dealsservice.repositories;

import com.josephcalver.dealsservice.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealsRepository extends JpaRepository<Deal, Long> {

    public Deal findByDealId(String dealId);
    public void deleteByDealId(String dealId);

}
