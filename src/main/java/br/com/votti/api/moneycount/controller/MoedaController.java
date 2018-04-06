package br.com.votti.api.moneycount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.votti.api.moneycount.application.MoedaApplicationService;
import br.com.votti.api.moneycount.application.dto.MoedaDTO;

@RestController
@CrossOrigin
@RequestMapping("/moedas")
public class MoedaController {

	@Autowired
	private MoedaApplicationService service;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> obterTodasMoedasDisponiveis() {

		List<MoedaDTO> result = service.obterMoedasDisponiveis();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> obterMoedaPorCodigo(@PathVariable String codigo) {

		MoedaDTO result = service.obterMoeda(codigo);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	
}
