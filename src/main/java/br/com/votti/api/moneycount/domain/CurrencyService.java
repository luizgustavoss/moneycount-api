package br.com.votti.api.moneycount.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;
	
	private static Map<String, Currency> availableCurrencies;
	

	public Currency getCurrency(String code) {
		if(Strings.isNullOrEmpty(code) || !getAvailableCurrenciesMap().containsKey(code))
			return null;
		return getAvailableCurrenciesMap().get(code);
	}
	
	public List<Currency> getAvailableCurrencies(){
		return getAvailableCurrenciesMap().values().stream().collect(Collectors.toList());
	}
	
	private Map<String, Currency> getAvailableCurrenciesMap(){
		if(CurrencyService.availableCurrencies == null)
			initializeAvailableCurrenciesMap();
		return CurrencyService.availableCurrencies;
	}
	
	private void initializeAvailableCurrenciesMap() {
		CurrencyService.availableCurrencies = new HashMap<>();
		synchronized (CurrencyService.availableCurrencies) {
			CurrencyService.availableCurrencies = 
				getSupportedCurrencies().getAvailableSupportedCurrencies()
				.stream().collect(Collectors.toMap(
					c -> c.getCode(), 
					c -> c));
		}
	}
	
	private SupportedCurrencies getSupportedCurrencies() {
		return currencyRepository.getSupportedCurrencies();
	}
	
}
