package com.josephcalver.dealsservice.events.handler;

import com.josephcalver.dealsservice.events.CustomChannels;
import com.josephcalver.dealsservice.events.model.CompanyChangeModel;
import com.josephcalver.dealsservice.repository.CompaniesRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomChannels.class)
public class CompanyChangeHandler {

    private static final Logger logger = LoggerFactory.getLogger(CompanyChangeHandler.class);

    @Autowired
    CompaniesRedisRepository redisRepository;

    @StreamListener("inboundCompanyChanges")
    public void loggerSink(CompanyChangeModel company) {

        logger.debug("Received a message of type " + company.getType());

        switch (company.getAction()) {
            case "GET":
                logger.debug("Received a GET event from the Companies Service for Company ID: {}", company.getCompanyId());
                break;
            case "CREATED":
                logger.debug("Received a CREATED event from the Companies Service for Company ID: {}", company.getCompanyId());
                break;
            case "UPDATED":
                logger.debug("Received a UPDATED event from the Companies Service for Company ID: {}", company.getCompanyId());
                logger.debug("Evicting record with Company ID {} from the cache", company.getCompanyId());
                redisRepository.deleteById(company.getCompanyId());
                break;
            case "DELETED":
                logger.debug("Received a DELETED event from the Companies Service for Company ID {}", company.getCompanyId());
                logger.debug("Evicting record with Company ID {} from the cache", company.getCompanyId());
                redisRepository.deleteById(company.getCompanyId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the Companies Service of type {}", company.getType());
                break;
        }
    }

}