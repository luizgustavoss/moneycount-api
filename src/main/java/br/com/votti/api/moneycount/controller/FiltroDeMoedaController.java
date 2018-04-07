package br.com.votti.api.moneycount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.votti.api.moneycount.application.FiltroDeMoedaApplicationService;
import br.com.votti.api.moneycount.application.dto.FiltroDeMoedaDTO;
import br.com.votti.api.moneycount.error.ErrorDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/filtrosDeMoeda")
@Api(tags={"Filtro de Moeda"})
public class FiltroDeMoedaController {

	
	@Autowired
	private FiltroDeMoedaApplicationService service;
	
	/**
	 * 
	 * @param codigoDaMoeda
	 * @return
	 */
	@RequestMapping(value = "/{codigoDaMoeda}", method = RequestMethod.GET)
	@ApiOperation(value = "${FiltroDeMoedaController.obterFiltroParaMoeda.value}",
    	notes = "${FiltroDeMoedaController.obterFiltroParaMoeda.notes}", response = FiltroDeMoedaDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Filtro não encontrado para a moeda informada", response = ErrorDetail.class) })
	public ResponseEntity<FiltroDeMoedaDTO> obterFiltroParaMoeda(@ApiParam(name="Código da Moeda", required=true)
			@PathVariable String codigoDaMoeda) {

		FiltroDeMoedaDTO result = service.obterFiltroParaMoeda(codigoDaMoeda);
		return new ResponseEntity<FiltroDeMoedaDTO>(result, HttpStatus.OK);
	}
}
