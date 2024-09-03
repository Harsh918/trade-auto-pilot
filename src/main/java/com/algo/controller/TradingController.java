package com.algo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algo.service.TradingService;

@RestController
@RequestMapping("/api/trading")
public class TradingController {

	@Autowired
	private TradingService tradingService;

	@PostMapping("/update-config")
	public void updateConfig(@RequestParam double minValuePercentage, @RequestParam double maxValuePercentage,
			@RequestParam String symbol) {
		tradingService.updateConfig(minValuePercentage, maxValuePercentage, symbol);
	}
}