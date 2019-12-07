package br.com.votti.api.moneycount.domain;

import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;

public interface CurrencyRepository {
	
	SupportedCurrencies getSupportedCurrencies() throws ResourceNotFoundException;
	
}
