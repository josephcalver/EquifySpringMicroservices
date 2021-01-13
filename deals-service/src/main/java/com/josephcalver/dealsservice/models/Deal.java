package com.josephcalver.dealsservice.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Deal {

    @Id
    @Column(name = "id", nullable = false)
    private String dealId;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "deal_name", nullable = false)
    private String dealName;

    @Column(name = "deal_type", nullable = false)
    private String dealType;

    @Column(name = "origination_date", nullable = false)
    private String originationDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "fund_investing", nullable = false)
    private String fundInvesting;

    @Column(name = "deal_team_lead", nullable = false)
    private String dealTeamLead;

    @Column(name = "equity_required", nullable = false)
    private int equityRequired;

    @Column(name = "currency", nullable = false)
    private String currency;

    public Deal() {

    }

    public Deal(String dealId, String companyId, String dealType, String dealName, String originationDate,
                String status, String fundInvesting, String dealTeamLead, int equityRequired, String currency) {
        this.dealId = dealId;
        this.companyId = companyId;
        this.dealType = dealType;
        this.dealName = dealName;
        this.originationDate = originationDate;
        this.status = status;
        this.fundInvesting = fundInvesting;
        this.dealTeamLead = dealTeamLead;
        this.equityRequired = equityRequired;
        this.currency = currency;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public String getOriginationDate() {
        return originationDate;
    }

    public void setOriginationDate(String originationDate) {
        this.originationDate = originationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFundInvesting() {
        return fundInvesting;
    }

    public void setFundInvesting(String fundInvesting) {
        this.fundInvesting = fundInvesting;
    }

    public String getDealTeamLead() {
        return dealTeamLead;
    }

    public void setDealTeamLead(String dealTeamLead) {
        this.dealTeamLead = dealTeamLead;
    }

    public int getEquityRequired() {
        return equityRequired;
    }

    public void setEquityRequired(int equityRequired) {
        this.equityRequired = equityRequired;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return this.dealName;
    }
}
