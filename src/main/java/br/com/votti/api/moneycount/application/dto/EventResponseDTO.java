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
public class EventResponseDTO {

	private String code;
	private String description;
	private List<EventEntryResponseDTO> entries;
	private CurrencyDTO currency;
	private CurrencyMapDTO currencyMap;
}
