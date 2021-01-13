package com.josephcalver.dealsservice.Services;

import com.josephcalver.dealsservice.Exceptions.DealNotFoundException;
import com.josephcalver.dealsservice.models.Deal;
import com.josephcalver.dealsservice.repositories.DealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealsService {

    @Autowired
    private DealsRepository dealsRepository;

    public DealsService(DealsRepository dealsRepository) {
        this.dealsRepository = dealsRepository;
    }

    public List<Deal> getAllDeals() {
        List<Deal> deals = dealsRepository.findAll();
        return deals;
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
        existingDeal.setStatus(deal.getStatus());
        existingDeal.setFundInvesting(deal.getFundInvesting());
        existingDeal.setDealTeamLead(deal.getDealTeamLead());
        existingDeal.setEquityRequired(deal.getEquityRequired());
        existingDeal.setCurrency(deal.getCurrency());

        return dealsRepository.save(existingDeal);
    }

    public void deleteDeal(String dealId) {
        dealsRepository.deleteByDealId(dealId);
    }
}
