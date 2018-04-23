package br.com.votti.api.moneycount.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Currency> currencies = new ArrayList<>();
		currencies.addAll(getAvailableCurrenciesMap().values());
		return currencies;
	}
	
	private Map<String, Currency> getAvailableCurrenciesMap(){
		if(CurrencyService.availableCurrencies == null)
			initializeAvailableCurrenciesMap();
			
		return CurrencyService.availableCurrencies;
	}
	
	private void initializeAvailableCurrenciesMap() {
		CurrencyService.availableCurrencies = new HashMap<String, Currency>();
		synchronized (CurrencyService.availableCurrencies) {
			SupportedCurrencies supportedCurrencies = getSupportedCurrencies();
			for(Currency currency : supportedCurrencies.getAvailableSupportedCurrencies()) {
				CurrencyService.availableCurrencies.put(currency.getCode(), currency);
			}
		}
	}
	
	private SupportedCurrencies getSupportedCurrencies() {
		return currencyRepository.getSupportedCurrencies();
	}
	
}
