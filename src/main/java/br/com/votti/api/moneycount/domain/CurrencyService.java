package br.com.votti.api.moneycount.domain;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

	private CurrencyRepository currencyRepository;
	private static Map<String, Currency> availableCurrencies;

	@Autowired
	private CurrencyService(CurrencyRepository currencyRepository){
		this.currencyRepository = currencyRepository;
	}

	public Optional<Currency> getCurrency(String code) {
		if(Strings.isNullOrEmpty(code) || !getAvailableCurrenciesMap().containsKey(code))
			return Optional.empty();
		return Optional.ofNullable(getAvailableCurrenciesMap().get(code));
	}
	
	public List<Currency> getAvailableCurrenciesList(){
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
