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
public class FiltroDeMoedaDTO {
	
	private MoedaDTO moeda;	
	private Map<BigDecimal, Boolean> notas;
}
