package com.josephcalver.dealsservice.model;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.math.BigDecimal;

@RedisHash("companies")
public class Company {

    @Id
    private String id;

    private String companyName;

    private int founded;

    private String country;

    private String region;

    private String sector;

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

    public void setId(String id) {
        this.id = id;
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
