package br.com.votti.api.moneycount.application.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyMapDTO {

	private BigDecimal totalValue;
	private Map<BigDecimal, Integer> map;
	
	@Builder.Default
	private BigDecimal remainingValue = BigDecimal.ZERO;
	
	public CurrencyMapDTO() {
		this.totalValue = BigDecimal.ZERO;
		this.map = new HashMap<>();
	}
	
	public void merge(CurrencyMapDTO currencyMap) {
		
		if(currencyMap == null)
			return;
		
		if(currencyMap.getTotalValue() != null)
			this.totalValue = this.totalValue.add(currencyMap.getTotalValue());
		
		if(currencyMap.getRemainingValue() != null)
			this.remainingValue = this.remainingValue.add(currencyMap.getRemainingValue());
		
		currencyMap.map.keySet().stream().forEach( key ->  {
			if(this.map.containsKey(key))
				this.map.put(key, this.map.get(key) + currencyMap.map.get(key));
			else
				this.map.put(key, currencyMap.map.get(key));
		});
	}
}
