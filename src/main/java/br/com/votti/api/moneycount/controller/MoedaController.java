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
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/moedas")
@Api(tags={"Moeda"})
public class MoedaController {

	@Autowired
	private MoedaApplicationService service;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MoedaDTO>> obterTodasMoedasDisponiveis() {

		List<MoedaDTO> result = service.obterMoedasDisponiveis();
		return new ResponseEntity<List<MoedaDTO>>(result, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<MoedaDTO> obterMoedaPorCodigo(@PathVariable String codigo) {

		MoedaDTO result = service.obterMoeda(codigo);
		return new ResponseEntity<MoedaDTO>(result, HttpStatus.OK);
	}

	
	
}
