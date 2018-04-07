package br.com.votti.api.moneycount.application;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.LancamentoRespostaDTO;
import br.com.votti.api.moneycount.application.dto.LancamentoSolicitacaoDTO;
import br.com.votti.api.moneycount.application.dto.MapaDeNotaDTO;
import br.com.votti.api.moneycount.domain.FiltroDeMoeda;
import br.com.votti.api.moneycount.domain.MapaDeNota;
import br.com.votti.api.moneycount.domain.Moeda;
import br.com.votti.api.moneycount.domain.ServicoDeMoeda;

/**
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
@Service
public class ProcessamentoDeMapaDeNotasDeLancamentoService {

	@Autowired
	private ServicoDeMoeda servicoMoeda;
	
		
	/**
	 * 
	 * @param lancamento
	 * @return
	 */
	public LancamentoRespostaDTO processarMapaDeNotasDoLancamento(LancamentoSolicitacaoDTO lancamento) {
		
		BigDecimal valorTotal = lancamento.getValor();
		Moeda moeda = servicoMoeda.obterMoeda(lancamento.getCodigoMoeda());
		
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		filtro.alterarFiltro(lancamento.getFiltro().getNotas());
		MapaDeNota mapaDeNota = new MapaDeNota(valorTotal, moeda, filtro);
		
		MapaDeNotaDTO mapaDeNotaDTO = montarMapaDeNotaDTO(mapaDeNota);		
		LancamentoRespostaDTO lancamentoResposta = montarLancamentoDeResposta(lancamento, mapaDeNotaDTO);
		
		return lancamentoResposta;
	}
	
	/**
	 * 
	 * @param lancamento
	 * @param mapaDeNotaDTO
	 * @return
	 */
	private LancamentoRespostaDTO montarLancamentoDeResposta(LancamentoSolicitacaoDTO lancamento, MapaDeNotaDTO mapaDeNotaDTO) {
		
		LancamentoRespostaDTO lancamentoResposta = LancamentoRespostaDTO.builder()
				.codigo(lancamento.getCodigo())
				.descricao(lancamento.getDescricao())
				.valor(lancamento.getValor())
				.mapaDeMoedas(mapaDeNotaDTO).build();
		
		return lancamentoResposta;
	}
	
	/**
	 * 
	 * @param mapaDeNota
	 * @return
	 */
	private MapaDeNotaDTO montarMapaDeNotaDTO(MapaDeNota mapaDeNota) {
		
		MapaDeNotaDTO mapaDeNotaDTO = MapaDeNotaDTO.builder()
				.mapa(mapaDeNota.obterMapa())
				.valorRestante(mapaDeNota.obterValorRestante())
				.valorTotal(mapaDeNota.obterValorTotal()).build();
		
		return mapaDeNotaDTO;
	}

}
