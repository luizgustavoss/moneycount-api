package br.com.votti.api.moneycount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.votti.api.moneycount.application.EventEntryCurrencyMapProcessorService;
import br.com.votti.api.moneycount.application.dto.EntryProcessDTO;
import br.com.votti.api.moneycount.application.dto.EventEntryResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/event-entries")
@Api(tags={"Event Entries"})
public class EventEntryController {

	@Autowired
	private EventEntryCurrencyMapProcessorService processor;
	
	@PostMapping("/process-map")
	@ApiOperation(value = "${EventEntryController.processMap.value}", 
	notes = "${EventEntryController.processMap.notes}", response = EventEntryResponseDTO.class)
	public ResponseEntity<EventEntryResponseDTO> processMap(@RequestBody EntryProcessDTO entry) {
		EventEntryResponseDTO result = processor.processMap(entry);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
