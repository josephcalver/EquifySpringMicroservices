package com.josephcalver.dealsservice.service;

import com.josephcalver.dealsservice.client.CompaniesDiscoveryClient;
import com.josephcalver.dealsservice.client.CompaniesFeignClient;
import com.josephcalver.dealsservice.client.CompaniesRestTemplateClient;
import com.josephcalver.dealsservice.exception.DealNotFoundException;
import com.josephcalver.dealsservice.model.Company;
import com.josephcalver.dealsservice.model.Deal;
import com.josephcalver.dealsservice.repository.DealsRepository;
import com.josephcalver.dealsservice.utils.UserContextHolder;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealsService {

    @Autowired
    private DealsRepository dealsRepository;

    @Autowired
    private CompaniesRestTemplateClient companiesRestTemplateClient;

    @Autowired
    CompaniesDiscoveryClient companiesDiscoveryClient;

    @Autowired
    private CompaniesFeignClient companiesFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(DealsService.class);


//    public DealsService(DealsRepository dealsRepository) {
//        this.dealsRepository = dealsRepository;
//    }

    @CircuitBreaker(name = "dealsService", fallbackMethod = "dealsDataUnavailable")
    @Bulkhead(name = "bulkheadDealsService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "dealsDataUnavailable")
    public Iterable<Deal> getAllDeals() {
        logger.debug("getAllDeals - Correlation id: {}",
                UserContextHolder.getContext().getCorrelationId());
        return dealsRepository.findAll();
    }

    private Iterable<Deal> dealsDataUnavailable() {
        Deal dummyDeal = new Deal();
        dummyDeal.setCompanyName("Deals Service data is currently unavailable");
        List<Deal> dummyList = new ArrayList<Deal>();
        dummyList.add(dummyDeal);
        return dummyList;
    }

    @CircuitBreaker(name = "dealsService", fallbackMethod = "dealDataUnavailable")
    @Bulkhead(name = "bulkheadDealsService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "dealDataUnavailable")
    public Deal getDeal(String dealId) {

        logger.debug("getDeal - Correlation id: {}",
                UserContextHolder.getContext().getCorrelationId());

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

    private Deal dealDataUnavailable() {
        Deal dummy = new Deal();
        dummy.setCompanyName("Deal Service data is currently unavailable");
        return dummy;
    }

    @Retry(name = "retryCompaniesService", fallbackMethod = "companiesServiceUnavailable")
    @RateLimiter(name = "rateLimitCompaniesService", fallbackMethod = "companiesServiceUnavailable")
    @CircuitBreaker(name = "companiesService", fallbackMethod = "companiesServiceUnavailable")
    @Bulkhead(name = "bulkheadCompaniesService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "companiesServiceUnavailable")
    private Company retrieveCompanyInfo(String companyId) {

//        Company company = companiesFeignClient.getCompany(companyId);
        Company company = companiesDiscoveryClient.getCompany(companyId);

        return company;
    }

    private Company companiesServiceUnavailable() {
        Company dummy = new Company();
        dummy.setCompanyName("The Companies Service is currently unavailable");
        return dummy;
    }

    @CircuitBreaker(name = "dealsService", fallbackMethod = "dealDataUnavailable")
    @Bulkhead(name = "bulkheadDealsService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "dealDataUnavailable")
    public Deal createDeal(Deal deal) {
        return dealsRepository.save(deal);
    }

    @CircuitBreaker(name = "dealsService", fallbackMethod = "dealDataUnavailable")
    @Bulkhead(name = "bulkheadDealsService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "dealDataUnavailable")
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

    @CircuitBreaker(name = "dealsService")
    @Bulkhead(name = "bulkheadDealsService", type = Bulkhead.Type.THREADPOOL)
    public void deleteDeal(String dealId) {
        dealsRepository.deleteByDealId(dealId);
    }

}
