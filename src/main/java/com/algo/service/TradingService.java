package com.algo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.algo.dto.OrderRequest;

@Service
public class TradingService {

	@Value("${angelbroking.api.key}")
	private String apiKey;

	@Value("${angelbroking.api.url}")
	private String apiUrl;

	@Value("${marketdata.api.url}")
	private String marketDataUrl;

	private double minValuePercentage;
	private double maxValuePercentage;
	private String symbol;

	private final RestTemplate restTemplate;

	public TradingService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		// Initialize with default values
		this.minValuePercentage = -5.0;
		this.maxValuePercentage = 5.0;
		this.symbol = "AAPL";
	}

	public void updateConfig(double minValuePercentage, double maxValuePercentage, String symbol) {
		this.minValuePercentage = minValuePercentage;
		this.maxValuePercentage = maxValuePercentage;
		this.symbol = symbol;
	}

	public double getCurrentValue(String symbol) {
		String url = marketDataUrl + "?symbol=" + symbol;
		ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
		return response.getBody();
	}

	public void placeOrder(String symbol, double quantity, String orderType) {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setSymbol(symbol);
		orderRequest.setQuantity(quantity);
		orderRequest.setOrderType(orderType);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-API-KEY", apiKey);
		HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);

		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Order placed successfully: " + response.getBody());
		} else {
			System.out.println("Error placing order: " + response.getStatusCode());
		}
	}

	public void executeTrade(double quantity) {
		double currentValue = getCurrentValue(symbol);
		double minValueThreshold = currentValue * (1 + minValuePercentage / 100);
		double maxValueThreshold = currentValue * (1 + maxValuePercentage / 100);

		if (currentValue <= minValueThreshold) {
			placeOrder(symbol, quantity, "BUY");
		} else if (currentValue >= maxValueThreshold) {
			placeOrder(symbol, quantity, "SELL");
		}
	}
}