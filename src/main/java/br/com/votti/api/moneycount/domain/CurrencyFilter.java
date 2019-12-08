package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.*;

public class CurrencyFilter {
	
	private Currency currency;	
	private Map<BigDecimal, Boolean> filter;
	
	private CurrencyFilter() {
		this.filter = new HashMap<>();
	}

	public CurrencyFilter(Currency currency) {
		this();
		if(currency == null)
			throw new IllegalArgumentException("Invalid constructor argument!");
		
		this.currency = currency;
		this.currency.getValues().forEach(value -> this.filter.put(value, true));
	}

	public List<BigDecimal> getValidValuesFromFilter(){

		List<BigDecimal> validValues = new ArrayList<>();

		this.filter.keySet().stream().forEach( value -> {
			if(this.filter.get(value))
				validValues.add(value);
		});
		Collections.sort(validValues);
		Collections.reverse(validValues);
		return validValues;
	}
	
	public Currency getCurrency() {
		return this.currency;
	}
	
	public Map<BigDecimal, Boolean> getFilter(){

		return Collections.unmodifiableMap(this.filter);
	}
	
	public void alterFilter(Map<BigDecimal, Boolean> filter) {

		filter.keySet().forEach(value -> modifyFilterEntry(value, filter.get(value)));
	}
	
	private void modifyFilterEntry(BigDecimal value, Boolean use) {
		if(this.filter.containsKey(value))
			this.filter.put(value, use);
	}
}
