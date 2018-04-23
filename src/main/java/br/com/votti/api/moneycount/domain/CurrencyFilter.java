package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyFilter {
	
	private Currency currency;	
	private Map<BigDecimal, Boolean> filter;
	
	public CurrencyFilter() {
		super();
		this.filter = new HashMap<>();
	}

	public CurrencyFilter(Currency currency) {
		this();
		if(currency == null)
			throw new IllegalArgumentException("[CurrencyFilter] Invalid constructor argument!");
		
		this.currency = currency;
		this.currency.getValues().forEach(value -> this.filter.put(value, true));
	}
	
	public Currency getCurrency() {
		return this.currency;
	}
	
	public Map<BigDecimal, Boolean> getFilter(){
		return Collections.unmodifiableMap(this.filter);
	}
	
	public void alterFilter(BigDecimal value, Boolean use) {
		modifyFilterEntry(value, use);
	}
	
	public void alterFilter(Map<BigDecimal, Boolean> filter) {
		filter.keySet().forEach(value -> modifyFilterEntry(value, filter.get(value)));
	}
	
	private void modifyFilterEntry(BigDecimal value, Boolean use) {
		/* just alter values that exist, not adding invalid values */
		if(this.filter.containsKey(value))
			this.filter.put(value, use);
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
		
}
