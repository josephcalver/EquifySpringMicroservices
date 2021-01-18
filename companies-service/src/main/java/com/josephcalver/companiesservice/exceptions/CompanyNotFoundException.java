package com.josephcalver.companiesservice.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(String companyId) {
        super("Could not find Company with ID: " + companyId);
    }
}
