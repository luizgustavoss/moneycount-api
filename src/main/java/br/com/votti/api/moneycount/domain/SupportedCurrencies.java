package br.com.votti.api.moneycount.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public class SupportedCurrencies {

	private Set<Currency> availableSupportedCurrencies = new HashSet<>();
	
	public void addCurrency(Currency currency) {
		this.availableSupportedCurrencies.add(currency);
	}
	
}
