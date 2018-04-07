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
public class MapaDeNotaDTO {

	private BigDecimal valorTotal;
	private Map<BigDecimal, Integer> mapa;
	
	@Builder.Default
	private BigDecimal valorRestante = BigDecimal.ZERO;
}
