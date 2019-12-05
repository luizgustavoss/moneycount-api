package br.com.votti.api.moneycount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.votti.api.moneycount.application.CurrencyApplicationService;
import br.com.votti.api.moneycount.application.dto.CurrencyDTO;
import br.com.votti.api.moneycount.error.ErrorDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/currencies")
@Api(tags = { "Currency" })
public class CurrencyController {

	@Autowired
	private CurrencyApplicationService service;

	@GetMapping
	@ApiOperation(value = "${CurrencyController.getAllAvailableCurrencies.value}", 
		notes = "${CurrencyController.getAllAvailableCurrencies.notes}", response = CurrencyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "No currencies could be found", response = ErrorDetail.class) })
	public ResponseEntity<List<CurrencyDTO>> getAllAvailableCurrencies() {
		List<CurrencyDTO> result = service.getAvailableCurrencies();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{code}")
	@ApiOperation(value = "${CurrencyController.getCurrencyByCode.value}", 
		notes = "${CurrencyController.getCurrencyByCode.notes}", response = CurrencyDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Currency not found", response = ErrorDetail.class) })
	public ResponseEntity<CurrencyDTO> getCurrencyByCode(@PathVariable String code) {
		CurrencyDTO result = service.getCurrency(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
