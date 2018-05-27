package br.com.votti.api.moneycount.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.CurrencyMapDTO;
import br.com.votti.api.moneycount.application.dto.EntryProcessDTO;
import br.com.votti.api.moneycount.application.dto.EventEntryResponseDTO;
import br.com.votti.api.moneycount.application.dto.EventProcessDTO;
import br.com.votti.api.moneycount.application.dto.EventResponseDTO;
import br.com.votti.api.moneycount.application.dto.assembler.CurrencyDTOAssembler;
import br.com.votti.api.moneycount.domain.Currency;
import br.com.votti.api.moneycount.domain.CurrencyService;

@Service
public class EventCurrencyMapProcessorService {

	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private EventEntryCurrencyMapProcessorService processor;
	
	
	public EventResponseDTO processMap(EventProcessDTO event) {
		
		Currency currency = currencyService.getCurrency(event.getCurrencyCode());
		
		List<EventEntryResponseDTO> entries = new ArrayList<>();
		
		event.getEntries().forEach(entry -> {
			
			EntryProcessDTO entryDTO = EntryProcessDTO.builder()
					.code(entry.getCode())
					.currencyCode(event.getCurrencyCode())
					.description(entry.getDescription())
					.value(entry.getValue())
					.filter(event.getFilter()).build();
			
			entries.add(processor.processMap(entryDTO));
		});
		return mountEventResponse(event, currency, entries);
	}


	private EventResponseDTO mountEventResponse(EventProcessDTO event, Currency currency,
			List<EventEntryResponseDTO> entries) {
		
		CurrencyMapDTO currencyMapDTO = new CurrencyMapDTO();
		
		entries.stream().map(EventEntryResponseDTO::getCurrencyMap)
		.forEach(cm -> currencyMapDTO.merge(cm));
		
		EventResponseDTO eventoResposta = EventResponseDTO.builder()
				.code(event.getCode())
				.description(event.getDescription())
				.currency(new CurrencyDTOAssembler().assembly(currency))
				.currencyMap(currencyMapDTO)
				.entries(entries).build();
		return eventoResposta;
	}

}
