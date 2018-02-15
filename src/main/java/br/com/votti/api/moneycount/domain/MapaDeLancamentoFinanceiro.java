package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaDeLancamentoFinanceiro {

	private LancamentoFinanceiro lancamento;
	private Map<BigDecimal, BigDecimal> mapaMoedasLancamento;
	private BigDecimal valorNaoDividido;
	
	
	public MapaDeLancamentoFinanceiro(LancamentoFinanceiro lancamento, Moeda moeda) {
		this.mapaMoedasLancamento = new HashMap<>();
		
		for (BigDecimal valor : moeda.getValores()) {
			mapaMoedasLancamento.put(valor, BigDecimal.ZERO);
		}
		
		this.lancamento = lancamento;
	}
	
	
	public Map<BigDecimal, BigDecimal> calcularMapaDeNotasDoLancamento(List<BigDecimal> valoresMoeda){
		
		BigDecimal valorLancamento = lancamento.getValor();
		
		for(BigDecimal valor : valoresMoeda) {
			
			valorLancamento = valorLancamento.remainder(valor, MathContext.DECIMAL64);
			BigDecimal quantidade = valorLancamento.divideToIntegralValue(valor, MathContext.DECIMAL64);
			
			mapaMoedasLancamento.put(valor, quantidade);
		}
		
		this.valorNaoDividido = valorLancamento;
		
		return mapaMoedasLancamento;
	}
	
	
	public BigDecimal getValorNaoDividido() {
		return valorNaoDividido;
	}
}
