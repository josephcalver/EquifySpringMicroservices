package com.josephcalver.dealsservice.controller;

import com.josephcalver.dealsservice.exception.DealNotFoundException;
import com.josephcalver.dealsservice.model.Deal;
import com.josephcalver.dealsservice.service.DealsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DealsController {

    private DealsService dealsService;
    //    private ServiceConfig serviceConfig;

    public DealsController(DealsService dealsService) {
        this.dealsService = dealsService;
    }


//    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/v1/deals")
    public Iterable<Deal> getAllDeals() {
        return dealsService.getAllDeals();
    }

//    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/v1/deals/{dealId}")
    public Deal getDeal(@PathVariable String dealId) {

        Deal deal = dealsService.getDeal(dealId);

        if (deal == null) {
            throw new DealNotFoundException(dealId);
        }

        return deal;
    }

//    @RolesAllowed({"ADMIN"})
    @PostMapping("/v1/deals")
    public Deal createDeal(@RequestBody Deal deal) {
        return dealsService.createDeal(deal);
    }

//    @RolesAllowed({"ADMIN"})
    @PutMapping("/v1/deals/{dealId}")
    public Deal updateDeal(@RequestBody Deal deal, @PathVariable String dealId) {
        return dealsService.updateDeal(deal, dealId);
    }

//    @RolesAllowed({"ADMIN"})
    @DeleteMapping("/v1/deals/{dealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeal(@PathVariable String dealId) {
        dealsService.deleteDeal(dealId);
    }

}
