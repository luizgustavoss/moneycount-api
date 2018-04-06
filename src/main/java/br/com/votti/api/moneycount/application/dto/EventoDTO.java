package br.com.votti.api.moneycount.application.dto;

import java.util.List;

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
public class EventoDTO {

	private String codigo;
	private String descricao;
	private List<LancamentoDTO> lancamentos;
	private MoedaDTO moeda;
	private FiltroDeMoedaDTO filtro;
}
