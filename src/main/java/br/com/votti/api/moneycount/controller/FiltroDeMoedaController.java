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

@RestController
@CrossOrigin
@RequestMapping("/filtrosDeMoeda")
public class FiltroDeMoedaController {

	
	@Autowired
	private FiltroDeMoedaApplicationService service;
	
	
	@RequestMapping(value = "/{codigoDaMoeda}", method = RequestMethod.GET)
	public ResponseEntity<?> obterFiltroParaMoeda(@PathVariable String codigoDaMoeda) {

		FiltroDeMoedaDTO result = service.obterFiltroParaMoeda(codigoDaMoeda);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
