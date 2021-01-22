package com.josephcalver.dealsservice.exception;

public class DealNotFoundException extends RuntimeException {

    public DealNotFoundException(String dealId) {
        super("Could not find deal with ID: " + dealId);
    }

}
