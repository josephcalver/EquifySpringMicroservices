package com.josephcalver.dealsservice.client;

import brave.ScopedSpan;
import brave.Tracer;
import com.josephcalver.dealsservice.model.Company;
import com.josephcalver.dealsservice.repository.CompaniesRedisRepository;
import com.josephcalver.dealsservice.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CompaniesRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CompaniesRedisRepository redisRepository;

    @Autowired
    Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(CompaniesRestTemplateClient.class);

    public Company getCompany(String companyId){

        logger.debug("In Deals Service.getOrganization: {}", UserContext.getCorrelationId());

        Company company = checkRedisCache(companyId);

        if (company != null){
            logger.debug("Successfully retrieved company {} from Redis cache: {}", companyId, company);
            return company;
        }

        logger.debug("Unable to locate company in Redis cache: {}", companyId);

        ResponseEntity<Company> restExchange = restTemplate.exchange(
                            "http://gatewayserver:5555/companiesservice/v1/companies/{companyId}",
                            HttpMethod.GET,
                            null, Company.class, companyId);

        // Save company record to cache
        company = restExchange.getBody();
        if (company != null) {
            cacheOrganizationObject(company);
        }

        return restExchange.getBody();
    }

    private Company checkRedisCache(String companyId) {

        ScopedSpan customSpan = tracer.startScopedSpan("readCompanyDataFromRedis");

        try {
            return redisRepository.findById(companyId).orElse(null);
        } catch (Exception ex) {
            logger.error("Error encountered while trying to retrieve company {} check Redis cache.  Exception {}", companyId, ex);
            return null;
        } finally {
            customSpan.tag("peer.service", "redis");
            customSpan.annotate("Client received");
            customSpan.finish();
        }
    }

    private void cacheOrganizationObject(Company company) {

        ScopedSpan customSpan = tracer.startScopedSpan("writeCompanyDataToRedis");

        try {
            redisRepository.save(company);
            logger.debug("Caching company {} in Redis. Exception {}", company.getId());
        } catch (Exception ex) {
            logger.error("Unable to cache company {} in Redis. Exception {}", company.getId(), ex);
        } finally {
            customSpan.tag("peer.service", "redis");
            customSpan.annotate("Client sent");
            customSpan.finish();
        }
    }

//    public Company getCompany(String companyId) {
//        ResponseEntity<Company> restExchange = restTemplate.exchange(
//                "http://companiesservice/v1/companies/{companyId}",
//                HttpMethod.GET, null, Company.class, companyId);
//
//        return restExchange.getBody();
//    }

//    @Autowired
//    private KeycloakRestTemplate restTemplate;
//
//    public Company getCompany(String companyId){
//        ResponseEntity<Company> restExchange =
//                restTemplate.exchange(
//                        "http://gatewayserver:5555/companiesservice/v1/companies/{companyId}",
//                        HttpMethod.GET,
//                        null, Company.class, companyId);
//
//        return restExchange.getBody();
//    }

}