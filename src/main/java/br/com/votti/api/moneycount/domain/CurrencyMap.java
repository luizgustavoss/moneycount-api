package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CurrencyMap {

	private BigDecimal totalValue;
	private Map<BigDecimal, Integer> map;
	private BigDecimal remainingValue = BigDecimal.ZERO;
	private Currency currency;
	private CurrencyFilter filter;
	
	
	public CurrencyMap(BigDecimal totalValue, Currency currency, CurrencyFilter filter) {
		
		if(totalValue == null || currency == null || filter == null) {
			throw new IllegalArgumentException("[CurrencyMap] Invalid constructor argument!");
		}
		
		if(!filter.getCurrency().equals(currency)) {
			throw new IllegalArgumentException("Invalid filter!");
		}
		
		this.map = new HashMap<>();
		
		this.totalValue = totalValue;
		this.currency = currency;
		this.filter = filter;
		
		this.currency.getValues().forEach(valor -> {
			map.put(valor, 0);
		});
		
		calculateCurrencyMapForValue();
	}
	
	
	public void calculateCurrencyMapForValue(){
		
		BigDecimal value = new BigDecimal(totalValue.toString());

		for(BigDecimal filterValue : this.filter.getValidValuesFromFilter()) {
			BigDecimal quantity = calculateFilterValueQuantity(value, filterValue);
			value = calculateValueLeft(value, filterValue);
			storeFilterValueQuantity(filterValue, quantity);
		}
		this.remainingValue = value;
	}


	private void storeFilterValueQuantity(BigDecimal filterValue, BigDecimal quantity) {
		this.map.put(filterValue, quantity.intValue());
	}

	private BigDecimal calculateValueLeft(BigDecimal value, BigDecimal filterValue) {
		return value.remainder(filterValue, MathContext.DECIMAL64);
	}

	private BigDecimal calculateFilterValueQuantity(BigDecimal value, BigDecimal filterValue) {
		return value.divideToIntegralValue(filterValue, MathContext.DECIMAL64);
	}
	
	public Map<BigDecimal, Integer> getMap(){
		return Collections.unmodifiableMap(this.map);
	}
	
	public BigDecimal getRemainingValue() {
		return remainingValue;
	}

	public BigDecimal getTotalValue() {
		return this.totalValue;
	}
	
}
