package com.smartreporting.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Locale;

import com.smartreporting.application.service.CurrencyConvertorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyConvertorServiceTests {

	@Autowired
	private CurrencyConvertorService currencyConvertorService;
	
	@Test
	public void testConversionHistory() {
		String his1 = "his1";
		String his2 = "his2";
		currencyConvertorService.saveConversionInHistory(his1);
		currencyConvertorService.saveConversionInHistory(his2);

		List<String> history = currencyConvertorService.getConversionHistoryList();

		assertThat(history.size()).isEqualTo(2);
	}

}
