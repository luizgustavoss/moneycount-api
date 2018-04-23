package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of={"code"})
public class FinancialEntry {

	private String code;
	private String description;
	private BigDecimal value;
	
	public FinancialEntry(String code, String description, BigDecimal value) {
		
		if( StringUtils.isEmpty(code) || StringUtils.isEmpty(description) ||
				value == null || value.equals(BigDecimal.ZERO))
			throw new IllegalArgumentException("[FinancialEntry] Invalid constructor argument!");
		
		this.code = code;
		this.description = description;
		this.value = value;
	}

}
