package com.fabianopontes.ecommercesb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fabianopontes.ecommercesb.domain.PaymentWithBankSlip;
import com.fabianopontes.ecommercesb.domain.PaymentWithCreditCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentWithCreditCard.class);
				objectMapper.registerSubtypes(PaymentWithBankSlip.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}