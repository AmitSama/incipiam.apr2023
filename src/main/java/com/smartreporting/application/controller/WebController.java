package com.smartreporting.application.controller;

import java.util.List;

import com.smartreporting.application.service.CurrencyConvertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myapi")
public class WebController {

	@Autowired
	private CurrencyConvertorService currencyConvertorService;

	@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type")
	@GetMapping("/getCurrencyRates")
	public String getCurrencyRates() {
		return currencyConvertorService.getCurrencyRates();
	}

	@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type")
	@PostMapping("/saveConversionInHistory")
	public void saveConversionInHistory(String conversion) {
		currencyConvertorService.saveConversionInHistory(conversion);
	}

	@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type")
	@GetMapping("/getConversionHistoryList")
	public List<String> getConversionHistoryList() {
		return currencyConvertorService.getConversionHistoryList();
	}

}