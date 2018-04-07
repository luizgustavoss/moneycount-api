package br.com.votti.api.moneycount.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.EventoRespostaDTO;
import br.com.votti.api.moneycount.application.dto.EventoSolicitacaoDTO;
import br.com.votti.api.moneycount.application.dto.LancamentoRespostaDTO;
import br.com.votti.api.moneycount.application.dto.LancamentoSolicitacaoDTO;
import br.com.votti.api.moneycount.application.dto.assembler.MoedaDTOAssembler;
import br.com.votti.api.moneycount.domain.Moeda;
import br.com.votti.api.moneycount.domain.ServicoDeMoeda;

/**
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
@Service
public class ProcessamentoDeMapaDeNotasDeEventoService {

	@Autowired
	private ServicoDeMoeda servicoMoeda;
	
	@Autowired
	private ProcessamentoDeMapaDeNotasDeLancamentoService processamentoDeMapaDeNotasDeLancamentoService;
	
	
	/**
	 * 
	 * @param evento
	 * @return
	 */
	public EventoRespostaDTO processarMapaDeNotasDoEvento(EventoSolicitacaoDTO evento) {
		
		
		Moeda moeda = servicoMoeda.obterMoeda(evento.getCodigoMoeda());
		
		List<LancamentoRespostaDTO> lancamentos = new ArrayList<>();
		
		evento.getLancamentos().forEach(lancamentoDoEvento -> {
			
			LancamentoSolicitacaoDTO lancamento = LancamentoSolicitacaoDTO.builder()
					.codigo(lancamentoDoEvento.getCodigo())
					.codigoMoeda(evento.getCodigoMoeda())
					.descricao(lancamentoDoEvento.getDescricao())
					.valor(lancamentoDoEvento.getValor())
					.filtro(evento.getFiltro()).build();
			
			lancamentos.add(
					processamentoDeMapaDeNotasDeLancamentoService.processarMapaDeNotasDoLancamento(lancamento));
		});
		
		EventoRespostaDTO eventoResposta = EventoRespostaDTO.builder()
				.codigo(evento.getCodigo())
				.descricao(evento.getDescricao())
				.moeda(new MoedaDTOAssembler().assembleDTO(moeda))
				.lancamentos(lancamentos).build();
		
		
		return eventoResposta;
	}

}
