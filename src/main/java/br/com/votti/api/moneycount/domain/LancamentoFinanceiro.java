package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;

public class LancamentoFinanceiro {

	private String id;
	private BigDecimal valor;
	
	public LancamentoFinanceiro(String id, BigDecimal valor) {
		
		this.id = id;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
}
