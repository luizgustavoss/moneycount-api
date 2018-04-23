package br.com.votti.api.moneycount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.votti.api.moneycount.application.EventCurrencyMapProcessorService;
import br.com.votti.api.moneycount.application.dto.EventProcessDTO;
import br.com.votti.api.moneycount.application.dto.EventResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/events")
@Api(tags={"Events"})
public class EventController {

	@Autowired
	private EventCurrencyMapProcessorService processor;

	@RequestMapping(value="/processMap", method = RequestMethod.POST)
	@ApiOperation(value = "${EventController.processMap.value}", 
		notes = "${EventController.processMap.notes}", response = EventResponseDTO.class)
	public ResponseEntity<EventResponseDTO> processMap(@RequestBody EventProcessDTO event) {
		EventResponseDTO result = processor.processMap(event);
		return new ResponseEntity<EventResponseDTO>(result, HttpStatus.OK);
	}
}
