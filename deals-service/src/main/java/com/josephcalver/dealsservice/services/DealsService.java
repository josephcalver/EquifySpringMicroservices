package com.josephcalver.dealsservice.services;

import com.josephcalver.dealsservice.exceptions.DealNotFoundException;
import com.josephcalver.dealsservice.models.Deal;
import com.josephcalver.dealsservice.repositories.DealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealsService {

    @Autowired
    private DealsRepository dealsRepository;

    public DealsService(DealsRepository dealsRepository) {
        this.dealsRepository = dealsRepository;
    }

    public Iterable<Deal> getAllDeals() {
        return dealsRepository.findAll();
    }

    public Deal getDeal(String dealId) {
        return dealsRepository.findByDealId(dealId);
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
