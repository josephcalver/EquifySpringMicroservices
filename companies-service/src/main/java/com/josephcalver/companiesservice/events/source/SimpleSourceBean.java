package com.josephcalver.companiesservice.events.source;

import com.josephcalver.companiesservice.events.model.CompanyChangeModel;
import com.josephcalver.companiesservice.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleSourceBean {

    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    public void publishCompanyChange(String action, String companyId) {
        logger.debug("Sending Kafka message {} for Company Id: {}", action, companyId);
        CompanyChangeModel change =  new CompanyChangeModel(
                CompanyChangeModel.class.getTypeName(),
                action,
                companyId,
                UserContext.getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }

}
