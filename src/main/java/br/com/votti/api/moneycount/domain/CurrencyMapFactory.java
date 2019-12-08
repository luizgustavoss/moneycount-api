package br.com.votti.api.moneycount.domain;

import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyMapFactory {

	private CurrencyService currencyService;

	@Autowired
	public CurrencyMapFactory(CurrencyService currencyService){
		this.currencyService = currencyService;
	}

	public CurrencyMap createCurrencyMap(BigDecimal totalValue, String currencyCode, Map<BigDecimal, Boolean> filterMap) {
		Optional<Currency> opt = currencyService.getCurrency(currencyCode);
		if(!opt.isPresent())
			throw new ResourceNotFoundException(MessageFormat.format("Currency not found for code {0}", currencyCode));

		if(totalValue.compareTo(BigDecimal.ZERO) <= 0 )
			throw new IllegalArgumentException("Invalid value. Must be greater than zero!");

		if(filterMap == null )
			throw new IllegalArgumentException("Invalid filter map!");

		Currency currency = opt.get();
		CurrencyFilter filter = new CurrencyFilter(currency);
		filter.alterFilter(filterMap);
		CurrencyMap currencyMap = new CurrencyMap(totalValue, currency, filter);
		return currencyMap;
	}
}
