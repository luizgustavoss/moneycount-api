package br.com.votti.api.moneycount.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.CurrencyMapDTO;
import br.com.votti.api.moneycount.application.dto.EntryProcessDTO;
import br.com.votti.api.moneycount.application.dto.EventEntryResponseDTO;
import br.com.votti.api.moneycount.domain.CurrencyMap;
import br.com.votti.api.moneycount.domain.CurrencyMapFactory;

@Service
public class EventEntryCurrencyMapProcessorService {

	@Autowired
	private CurrencyMapFactory currencyMapService;
	
		
	public EventEntryResponseDTO processMap(EntryProcessDTO entry) {
		
		EventEntryResponseDTO eventEntryResponse = mountEventEntryResponse(entry, 
				mountCurrencyMapResponse(currencyMapService.createCurrencyMap(
						entry.getValue(), entry.getCurrencyCode(), entry.getFilter().getValues())));
		return eventEntryResponse;
	}
	
	private EventEntryResponseDTO mountEventEntryResponse(EntryProcessDTO entry, CurrencyMapDTO currencyMapDTO) {
		
		EventEntryResponseDTO eventEntryResponse = EventEntryResponseDTO.builder()
				.code(entry.getCode())
				.description(entry.getDescription())
				.value(entry.getValue())
				.currencyMap(currencyMapDTO).build();
		return eventEntryResponse;
	}
	
	private CurrencyMapDTO mountCurrencyMapResponse(CurrencyMap currencyMap) {
		
		CurrencyMapDTO mapaDeNotaDTO = new CurrencyMapDTO();
		mapaDeNotaDTO.setMap(currencyMap.getMap());
		mapaDeNotaDTO.setRemainingValue(currencyMap.getRemainingValue());
		mapaDeNotaDTO.setTotalValue(currencyMap.getTotalValue());
		return mapaDeNotaDTO;
	}

}
