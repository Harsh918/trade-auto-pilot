package com.algo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AngelBrokingConfig {
	@Value("${angelbroking.api.key}")
	private String apiKey;

	@Value("${angelbroking.api.secret}")
	private String apiSecret;

	@Value("${angelbroking.client.code}")
	private String clientCode;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	// Bean for RestTemplate or WebClient
}
