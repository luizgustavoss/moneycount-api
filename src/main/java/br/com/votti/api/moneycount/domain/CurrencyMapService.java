package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyMapService {

	@Autowired
	private CurrencyService currencyService;
	
	
	public CurrencyMap getCurrencyMap(BigDecimal totalValue, String currencyCode, Map<BigDecimal, Boolean> filterMap) {
		Currency currency = currencyService.getCurrency(currencyCode);
		CurrencyFilter filter = new CurrencyFilter(currency);
		filter.alterFilter(filterMap);
		CurrencyMap currencyMap = new CurrencyMap(totalValue, currency, filter);
		return currencyMap;
	}
}
