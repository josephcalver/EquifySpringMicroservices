package com.josephcalver.dealsservice.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @Column(name = "deal_id", nullable = false)
    private String dealId;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "deal_name", nullable = false)
    private String dealName;

    @Column(name = "deal_type", nullable = false)
    private String dealType;

    @Column(name = "origination_date", nullable = false)
    private String originationDate;

    @Column(name = "deal_status", nullable = false)
    private String dealStatus;

    @Column(name = "fund_investing", nullable = false)
    private String fundInvesting;

    @Column(name = "deal_team_lead", nullable = false)
    private String dealTeamLead;

    @Column(name = "equity_required", nullable = false)
    private int equityRequired;

    @Column(name = "deal_currency", nullable = false)
    private String dealCurrency;

    public Deal() {

    }

    public Deal(String dealId, String companyId, String dealName, String dealType, String originationDate,
                String dealStatus, String fundInvesting, String dealTeamLead, int equityRequired, String dealCurrency) {
        this.dealId = dealId;
        this.companyId = companyId;
        this.dealName = dealName;
        this.dealType = dealType;
        this.originationDate = originationDate;
        this.dealStatus = dealStatus;
        this.fundInvesting = fundInvesting;
        this.dealTeamLead = dealTeamLead;
        this.equityRequired = equityRequired;
        this.dealCurrency = dealCurrency;
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

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
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

    public String getDealCurrency() {
        return dealCurrency;
    }

    public void setDealCurrency(String dealCurrency) {
        this.dealCurrency = dealCurrency;
    }

    @Override
    public String toString() {
        return this.dealName;
    }
}
