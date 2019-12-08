package br.com.votti.api.moneycount.domain;

import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class CurrencyFilterFactory {

	private CurrencyService currencyService;

	@Autowired
	public CurrencyFilterFactory(CurrencyService currencyService){
		this.currencyService = currencyService;
	}
	
	public CurrencyFilter getCurrencyFilter(String currencyCode) {
		Optional<Currency> opt = currencyService.getCurrency(currencyCode);
		if(!opt.isPresent())
			throw new ResourceNotFoundException(MessageFormat.format("Currency not found for code {0}", currencyCode));
		CurrencyFilter filter = new CurrencyFilter(opt.get());
		return filter;
	}
}
