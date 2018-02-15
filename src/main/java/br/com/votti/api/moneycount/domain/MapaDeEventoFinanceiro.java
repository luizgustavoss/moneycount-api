package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaDeEventoFinanceiro {

	private EventoFinanceiro evento;
	private Map<BigDecimal, Boolean> filtroMoeda;
	private Map<BigDecimal, BigDecimal> mapaMoedasEvento;
	private List<Map<BigDecimal, BigDecimal>> mapaMoedasLancamentos;

	
	
	public MapaDeEventoFinanceiro(EventoFinanceiro evento) {
		this.evento = evento;

		this.filtroMoeda = new HashMap<>();
		this.mapaMoedasLancamentos = new ArrayList<>();

		for (BigDecimal valor : this.evento.getMoeda().getValores()) {
			filtroMoeda.put(valor, false);
		}

		for (BigDecimal valor : this.evento.getMoeda().getValores()) {
			mapaMoedasEvento.put(valor, BigDecimal.ZERO);
		}
	}

	
	
	public Map<BigDecimal, BigDecimal> getMapaMoedasEvento() {
		return mapaMoedasEvento;
	}

	
	
	public void gerarMapaDeMoedasDoEvento() {

	}

	/**
	 * 
	 * @return
	 */
	public List<BigDecimal> obterValoresValidosParaOMapa() {
		
		List<BigDecimal> valoresMoeda = new ArrayList<>();

		/* usar apenas valores selecionados */
		for (BigDecimal valor : filtroMoeda.keySet()) {
			if (filtroMoeda.get(valor)) {
				valoresMoeda.add(valor);
			}
		}
		/* ordena de forma ascendente */
		Collections.sort(valoresMoeda);
		/* inverte a ordenação */
		Collections.reverse(valoresMoeda);
		
		return valoresMoeda;
	}
}
