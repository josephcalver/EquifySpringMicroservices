package com.josephcalver.dealsservice.controllers;

import com.josephcalver.dealsservice.exceptions.DealNotFoundException;
import com.josephcalver.dealsservice.services.DealsService;
import com.josephcalver.dealsservice.models.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DealsController {

    @Autowired
    private DealsService dealsService;

//    private ServiceConfig serviceConfig;

    @GetMapping("/v1/deals")
    public Iterable<Deal> getAllDeals() {
        return dealsService.getAllDeals();
    }

    @GetMapping("/v1/deals/{dealId}")
    public Deal getDeal(@PathVariable String dealId) {

        Deal deal = dealsService.getDeal(dealId);

        if (deal == null) {
            throw new DealNotFoundException(dealId);
        } else
        return dealsService.getDeal(dealId);
    }

    @PostMapping("/v1/deals")
    public Deal createDeal(@RequestBody Deal deal) {
        return dealsService.createDeal(deal);
    }

    @PutMapping("/v1/deals/{dealId}")
    public Deal updateDeal(@RequestBody Deal deal, @PathVariable String dealId) {
        return dealsService.updateDeal(deal, dealId);
    }

    @DeleteMapping("/v1/deals/{dealId}")
    public void deleteDeal(@PathVariable String dealId) {
        dealsService.deleteDeal(dealId);
    }

}
