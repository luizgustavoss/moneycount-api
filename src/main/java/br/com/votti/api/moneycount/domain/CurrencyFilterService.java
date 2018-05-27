package br.com.votti.api.moneycount.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyFilterService {
	
	@Autowired
	private CurrencyService currencyService;
	
	public CurrencyFilter getCurrencyFilter(String currencyCode) {
		
		Currency currency = currencyService.getCurrency(currencyCode);
		CurrencyFilter filter = new CurrencyFilter(currency);
		return filter;
	}
}
