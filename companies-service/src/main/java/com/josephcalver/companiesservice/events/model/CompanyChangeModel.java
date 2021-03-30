package com.josephcalver.companiesservice.events.model;

public class CompanyChangeModel {

    private String type;
    private String action;
    private String companyId;
    private String correlationId;

    public CompanyChangeModel(String type, String action, String companyId, String correlationId) {
        this.type = type;
        this.action = action;
        this.companyId = companyId;
        this.correlationId = correlationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "CompanyChangeModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", companyId='" + companyId + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }

}
