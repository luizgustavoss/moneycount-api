package br.com.votti.api.moneycount.application.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class LancamentoDTO {

	private String codigo;
	private String descricao;
	private BigDecimal valor;
	private MapaDeMoedaDTO mapaDeMoedas;
}
