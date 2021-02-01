package com.josephcalver.companiesservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @Column(name = "company_id", nullable = false)
    private String id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "founded", nullable = false)
    private int founded;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "sector", nullable = false)
    private String sector;

    @Column(name = "enterprise_value", nullable = false)
    private BigDecimal enterpriseValue;

    public Company() {

    }

    public Company(String companyId, String companyName, int founded, String country, String region, String sector, BigDecimal enterpriseValue) {
        this.id = companyId;
        this.companyName = companyName;
        this.founded = founded;
        this.country = country;
        this.region = region;
        this.sector = sector;
        this.enterpriseValue = enterpriseValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String companyId) {
        this.id = companyId;
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
        return this.companyName;
    }
}
