package br.com.votti.api.moneycount.domain;

import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyMapService {

	private CurrencyService currencyService;

	@Autowired
	public CurrencyMapService(CurrencyService currencyService){
		this.currencyService = currencyService;
	}

	public CurrencyMap getCurrencyMap(BigDecimal totalValue, String currencyCode, Map<BigDecimal, Boolean> filterMap) {
		Optional<Currency> opt = currencyService.getCurrency(currencyCode);
		if(!opt.isPresent())
			throw new ResourceNotFoundException(MessageFormat.format("Currency not found for code {0}", currencyCode));
		Currency currency = opt.get();
		CurrencyFilter filter = new CurrencyFilter(currency);
		filter.alterFilter(filterMap);
		CurrencyMap currencyMap = new CurrencyMap(totalValue, currency, filter);
		return currencyMap;
	}
}
