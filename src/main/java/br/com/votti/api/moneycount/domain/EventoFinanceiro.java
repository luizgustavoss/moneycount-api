package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventoFinanceiro {

	private String id;
	private String codigo;
	private String descricao;
	private List<LancamentoFinanceiro> lancamentos;
	private Moeda moeda;
	
	private EventoFinanceiro(String id, String codigo, String descricao, Moeda moeda) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.moeda = moeda;
		this.lancamentos = new ArrayList<>();
	}
	
	public void adicionarLancamentoFinanceiro(LancamentoFinanceiro lancamento) {
		this.lancamentos.add(lancamento);
	}
	
	public List<LancamentoFinanceiro> getLancamentos(){
		return Collections.unmodifiableList(lancamentos);
	}
	
	public BigDecimal getValor() {
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		
		for(LancamentoFinanceiro lf : lancamentos) {
			valorTotal = valorTotal.add(lf.getValor());
		}
		
		return valorTotal;
	}

	public String getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Moeda getMoeda() {
		return moeda;
	}
}
