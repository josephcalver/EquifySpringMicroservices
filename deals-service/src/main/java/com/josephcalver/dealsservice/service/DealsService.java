package com.josephcalver.dealsservice.service;

import com.josephcalver.dealsservice.client.CompaniesFeignClient;
import com.josephcalver.dealsservice.exception.DealNotFoundException;
import com.josephcalver.dealsservice.model.Company;
import com.josephcalver.dealsservice.model.Deal;
import com.josephcalver.dealsservice.repository.DealsRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealsService {

    @Autowired
    private DealsRepository dealsRepository;

    @Autowired
    private CompaniesFeignClient companiesFeignClient;

//    public DealsService(DealsRepository dealsRepository) {
//        this.dealsRepository = dealsRepository;
//    }

    public Iterable<Deal> getAllDeals() {
        return dealsRepository.findAll();
    }

    public Deal getDeal(String dealId) {

        Deal deal = dealsRepository.findByDealId(dealId);

        if (deal != null) {

            Company company = retrieveCompanyInfo(deal.getCompanyId());

            if (company != null) {

                deal.setCompanyName(company.getCompanyName());
                deal.setFounded(company.getFounded());
                deal.setCountry(company.getCountry());
                deal.setRegion(company.getRegion());
                deal.setSector(company.getSector());
                deal.setEnterpriseValue(company.getEnterpriseValue());
            }

        }

        return deal;
    }

    @CircuitBreaker(name = "companiesservice", fallbackMethod = "fallback")
    @Bulkhead(name = "companiesservice", type = Bulkhead.Type.THREADPOOL)
    private Company retrieveCompanyInfo(String companyId) {

        Company company = companiesFeignClient.getCompany(companyId);

        return company;
    }

    private Company fallback(String companyId, RuntimeException e) {
        return null;
    }

    public Deal createDeal(Deal deal) {
        return dealsRepository.save(deal);
    }

    public Deal updateDeal(Deal deal, String dealId) {

        Deal existingDeal = dealsRepository.findByDealId(dealId);

        if (existingDeal == null) {
            throw new DealNotFoundException(dealId);
        }

        existingDeal.setDealName(deal.getDealName());
        existingDeal.setDealType(deal.getDealType());
        existingDeal.setOriginationDate(deal.getOriginationDate());
        existingDeal.setDealStatus(deal.getDealStatus());
        existingDeal.setFundInvesting(deal.getFundInvesting());
        existingDeal.setDealTeamLead(deal.getDealTeamLead());
        existingDeal.setEquityRequired(deal.getEquityRequired());
        existingDeal.setDealCurrency(deal.getDealCurrency());

        return dealsRepository.save(existingDeal);
    }

    public void deleteDeal(String dealId) {
        dealsRepository.deleteByDealId(dealId);
    }
}
