package br.com.votti.api.moneycount.application;

import br.com.votti.api.moneycount.application.dto.*;
import br.com.votti.api.moneycount.application.dto.assembler.CurrencyDTOAssembler;
import br.com.votti.api.moneycount.domain.Currency;
import br.com.votti.api.moneycount.domain.CurrencyService;
import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventCurrencyMapProcessorService {

	private CurrencyService currencyService;
	private EventEntryCurrencyMapProcessorService processor;

	@Autowired
	public EventCurrencyMapProcessorService(CurrencyService currencyService, EventEntryCurrencyMapProcessorService processor){
		this.currencyService = currencyService;
		this.processor = processor;
	}

	public EventResponseDTO processMap(EventProcessDTO event) {

		Optional<Currency> opt = currencyService.getCurrency(event.getCurrencyCode());
		if(!opt.isPresent())
			throw new ResourceNotFoundException(MessageFormat.format("Currency not found for code {0}", event.getCurrencyCode()));
		Currency currency = opt.get();
		
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
		return buildEventResponse(event, currency, entries);
	}


	private EventResponseDTO buildEventResponse(EventProcessDTO event, Currency currency,
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
