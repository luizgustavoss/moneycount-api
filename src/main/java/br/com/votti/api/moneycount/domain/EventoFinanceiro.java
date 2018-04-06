package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of={"codigo"})
public class EventoFinanceiro {

	private String codigo;
	private String descricao;
	private List<LancamentoFinanceiro> lancamentos;
	private Moeda moeda;
	
	private EventoFinanceiro(String codigo, String descricao, Moeda moeda) {
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

}
