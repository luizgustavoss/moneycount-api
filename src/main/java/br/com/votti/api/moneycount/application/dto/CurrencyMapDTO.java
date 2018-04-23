package br.com.votti.api.moneycount.application.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyMapDTO {

	private BigDecimal totalValue;
	private Map<BigDecimal, Integer> map;
	
	@Builder.Default
	private BigDecimal remainingValue = BigDecimal.ZERO;
}
