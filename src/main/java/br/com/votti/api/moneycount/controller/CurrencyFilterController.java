package br.com.votti.api.moneycount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.votti.api.moneycount.application.CurrencyFilterApplicationService;
import br.com.votti.api.moneycount.application.dto.CurrencyFilterDTO;
import br.com.votti.api.moneycount.error.ErrorDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/currency-filters")
@Api(tags={"Currency Filter"})
public class CurrencyFilterController {

	@Autowired
	private CurrencyFilterApplicationService service;
	
	@RequestMapping(value = "/{currencyCode}", method = RequestMethod.GET)
	@ApiOperation(value = "${CurrencyFilterController.getFilterForCurrency.value}",
    	notes = "${CurrencyFilterController.getFilterForCurrency.notes}", response = CurrencyFilterDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Filter not found for the entered currency", response = ErrorDetail.class) })
	public ResponseEntity<CurrencyFilterDTO> getFilterForCurrency(@ApiParam(name="currencyCode", value="Currency Code", required=true)
			@PathVariable String currencyCode) {
		CurrencyFilterDTO result = service.getFilterForCurrency(currencyCode);
		return new ResponseEntity<CurrencyFilterDTO>(result, HttpStatus.OK);
	}
}
