package br.com.votti.api.moneycount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.votti.api.moneycount.application.ProcessamentoDeMapaDeNotasDeEventoService;
import br.com.votti.api.moneycount.application.ProcessamentoDeMapaDeNotasDeLancamentoService;
import br.com.votti.api.moneycount.application.dto.EventoRespostaDTO;
import br.com.votti.api.moneycount.application.dto.EventoSolicitacaoDTO;
import br.com.votti.api.moneycount.application.dto.LancamentoRespostaDTO;
import br.com.votti.api.moneycount.application.dto.LancamentoSolicitacaoDTO;

@RestController
@CrossOrigin
@RequestMapping("/mapaDeNotas")
public class MapaDeNotasController {

	
	@Autowired
	private ProcessamentoDeMapaDeNotasDeEventoService processamentoDeMapaDeNotasDeEventoService;
	@Autowired
	private ProcessamentoDeMapaDeNotasDeLancamentoService processamentoDeMapaDeNotasDeLancamentoService;
	
	
	/**
	 * 
	 * @param evento
	 * @return
	 */
	@RequestMapping(value="/processarEvento", method = RequestMethod.POST)
	public ResponseEntity<EventoRespostaDTO> processarMapaDeNotasDoEvento(@RequestBody EventoSolicitacaoDTO evento) {

		EventoRespostaDTO result = processamentoDeMapaDeNotasDeEventoService
				.processarMapaDeNotasDoEvento(evento);
		
		return new ResponseEntity<EventoRespostaDTO>(result, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param lancamento
	 * @return
	 */
	@RequestMapping(value="/processarLancamento", method = RequestMethod.POST)
	public ResponseEntity<LancamentoRespostaDTO> processarMapaDeNotasDoLancamento(@RequestBody LancamentoSolicitacaoDTO lancamento) {

		LancamentoRespostaDTO result = processamentoDeMapaDeNotasDeLancamentoService
				.processarMapaDeNotasDoLancamento(lancamento);
		
		return new ResponseEntity<LancamentoRespostaDTO>(result, HttpStatus.OK);
	}
	
}
