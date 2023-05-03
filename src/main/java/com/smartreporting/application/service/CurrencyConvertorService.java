package com.smartreporting.application.service;

import java.util.*;

import com.smartreporting.application.util.FixedSizeQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConvertorService {

	private FixedSizeQueue<String> queue = new FixedSizeQueue<>(10);

	@Autowired
    RestTemplate restTemplate;

	@Value("${url.openexchangerates}")
	private String openExchangeRatesUrl;

	@Value("${api.key.openexchangerates}")
	private String openExchangeRatesApiKey;

	public String getCurrencyRates() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		String jsonString = restTemplate.exchange(openExchangeRatesUrl + openExchangeRatesApiKey,
				HttpMethod.GET, entity, String.class).getBody();
		return jsonString;

	}

	public void saveConversionInHistory(String conversion) {
		queue.offer(conversion);
	}

	public List<String> getConversionHistoryList() {
		Iterator<String> itr = queue.iterator();
		List<String> historyList = new ArrayList<>();
		while(itr.hasNext()) {
			String item = itr.next();
			if (item != null) {
				historyList.add(item);
			}
		}
		List<String> shallowCopy = historyList.subList(0, historyList.size());
		Collections.reverse(shallowCopy);
		return shallowCopy;
	}

}