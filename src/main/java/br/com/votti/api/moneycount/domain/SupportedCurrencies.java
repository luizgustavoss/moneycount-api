package br.com.votti.api.moneycount.domain;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class SupportedCurrencies {

	private Set<Currency> availableSupportedCurrencies = new HashSet<>();
	
	public void addCurrency(Currency currency) {
		if(currency == null)
			throw new IllegalArgumentException("Invalid constructor argument!");
		this.availableSupportedCurrencies.add(currency);
	}
	
}
