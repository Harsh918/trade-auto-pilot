package com.algo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TradingScheduler {

	private final TradingService tradingService;

	@Value("${scheduler.interval}")
	private long interval;

	public TradingScheduler(TradingService tradingService) {
		this.tradingService = tradingService;
	}

	@Scheduled(fixedRateString = "${scheduler.interval}")
	public void performTrading() {
		String symbol = "AAPL"; // Example symbol
		double quantity = 10; // Example quantity
		tradingService.executeTrade(quantity);
	}
}