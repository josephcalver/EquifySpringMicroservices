package com.josephcalver.dealsservice;

import com.josephcalver.dealsservice.events.model.CompanyChangeModel;
import com.josephcalver.dealsservice.utils.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@RefreshScope
@EnableFeignClients
@EnableBinding(Sink.class)
@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class DealsServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(DealsServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DealsServiceApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.UK);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		return messageSource;
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();
		if (interceptors==null){
			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		}
		else{
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}
		return template;
	}

	@StreamListener(Sink.INPUT)
	public void loggerSink(CompanyChangeModel companyChange) {
		logger.debug("Received {} event for the organization id {}", companyChange.getAction(), companyChange.getCompanyId());
	}

}
