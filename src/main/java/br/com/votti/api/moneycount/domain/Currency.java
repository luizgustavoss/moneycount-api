package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.SortedSet;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of={"code", "name", "symbol", "values"})
public class Currency {

	private String code;
	private String name;
	private String symbol;
	private SortedSet<BigDecimal> values;
	
	public Currency(String code, String name, String symbol, SortedSet<BigDecimal> values) {
		if( StringUtils.isEmpty(code) || StringUtils.isEmpty(name) ||
				StringUtils.isEmpty(symbol) || CollectionUtils.isEmpty(values) )
			throw new IllegalArgumentException("[Currency] Invalid constructor argument!");
		
		this.code = code;
		this.name = name;
		this.symbol = symbol;
		this.values = values;
	}

}
