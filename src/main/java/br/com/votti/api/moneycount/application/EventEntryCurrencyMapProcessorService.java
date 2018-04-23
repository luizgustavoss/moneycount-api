package br.com.votti.api.moneycount.application;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.EventEntryResponseDTO;
import br.com.votti.api.moneycount.application.dto.EntryProcessDTO;
import br.com.votti.api.moneycount.application.dto.CurrencyMapDTO;
import br.com.votti.api.moneycount.domain.CurrencyFilter;
import br.com.votti.api.moneycount.domain.CurrencyMap;
import br.com.votti.api.moneycount.domain.Currency;
import br.com.votti.api.moneycount.domain.CurrencyService;

@Service
public class EventEntryCurrencyMapProcessorService {

	@Autowired
	private CurrencyService currencyService;
		
	public EventEntryResponseDTO processMap(EntryProcessDTO entry) {
		
		EventEntryResponseDTO eventEntryResponse = mountEventEntryResponse(entry, 
				mountCurrencyMapResponse(getCurrencyMapForFilter(entry)));
		return eventEntryResponse;
	}
	
	private CurrencyMap getCurrencyMapForFilter(EntryProcessDTO entry) {
		
		BigDecimal totalValue = entry.getValue();
		Currency currency = currencyService.getCurrency(entry.getCurrencyCode());
		
		CurrencyFilter filter = new CurrencyFilter(currency);
		filter.alterFilter(entry.getFilter().getValues());
		CurrencyMap currencyMap = new CurrencyMap(totalValue, currency, filter);
		return currencyMap;
	}
	
	private EventEntryResponseDTO mountEventEntryResponse(EntryProcessDTO entry, CurrencyMapDTO currencyMapDTO) {
		
		EventEntryResponseDTO eventEntryResponse = EventEntryResponseDTO.builder()
				.code(entry.getCode())
				.description(entry.getDescription())
				.value(entry.getValue())
				.currencyMap(currencyMapDTO).build();
		return eventEntryResponse;
	}
	
	private CurrencyMapDTO mountCurrencyMapResponse(CurrencyMap currencymap) {
		
		CurrencyMapDTO mapaDeNotaDTO = CurrencyMapDTO.builder()
				.map(currencymap.getMap())
				.remainingValue(currencymap.getRemainingValue())
				.totalValue(currencymap.getTotalValue()).build();
		return mapaDeNotaDTO;
	}

}
