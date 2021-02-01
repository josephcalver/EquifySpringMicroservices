package com.josephcalver.dealsservice.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @Column(name = "deal_id", nullable = false)
    private String dealId;

    @Column(name = "company_id", nullable = false)
    private String id;

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

    @Transient
    private String companyName;

    @Transient
    private int founded;

    @Transient
    private String country;

    @Transient
    private String region;

    @Transient
    private String sector;

    @Transient
    private BigDecimal enterpriseValue;

    public Deal() {

    }

    public Deal(String dealId, String companyId, String dealName, String dealType, String originationDate,
                String dealStatus, String fundInvesting, String dealTeamLead, int equityRequired, String dealCurrency) {
        this.dealId = dealId;
        this.id = companyId;
        this.dealName = dealName;
        this.dealType = dealType;
        this.originationDate = originationDate;
        this.dealStatus = dealStatus;
        this.fundInvesting = fundInvesting;
        this.dealTeamLead = dealTeamLead;
        this.equityRequired = equityRequired;
        this.dealCurrency = dealCurrency;
    }

    public Deal(String dealId, String companyId, String dealName, String dealType, String originationDate,
                String dealStatus, String fundInvesting, String dealTeamLead, int equityRequired, String dealCurrency,
                String companyName, int founded, String country, String region, String sector, BigDecimal enterpriseValue) {
        this.dealId = dealId;
        this.id = companyId;
        this.dealName = dealName;
        this.dealType = dealType;
        this.originationDate = originationDate;
        this.dealStatus = dealStatus;
        this.fundInvesting = fundInvesting;
        this.dealTeamLead = dealTeamLead;
        this.equityRequired = equityRequired;
        this.dealCurrency = dealCurrency;
        this.companyName = companyName;
        this.founded = founded;
        this.country = country;
        this.region = region;
        this.sector = sector;
        this.enterpriseValue = enterpriseValue;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getCompanyId() {
        return id;
    }

    public void setCompanyId(String id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public BigDecimal getEnterpriseValue() {
        return enterpriseValue;
    }

    public void setEnterpriseValue(BigDecimal enterpriseValue) {
        this.enterpriseValue = enterpriseValue;
    }

    @Override
    public String toString() {
        return this.dealName;
    }
}
