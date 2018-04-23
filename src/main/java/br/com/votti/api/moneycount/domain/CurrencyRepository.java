package br.com.votti.api.moneycount.domain;

import br.com.votti.api.moneycount.exceptions.SupportedCurrencyDefNotFoundException;

public interface CurrencyRepository {
	
	SupportedCurrencies getSupportedCurrencies() throws SupportedCurrencyDefNotFoundException;
	
}
