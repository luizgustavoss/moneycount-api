package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

public class Moeda {

	private String codigo;
	private String nome;
	private String simboloMonetario;
	private SortedSet<BigDecimal> valores;
	
	public Moeda(String codigo, String nome, String simboloMonetario) {
		this.codigo = codigo;
		this.nome = nome;
		this.simboloMonetario = simboloMonetario;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSimboloMonetario() {
		return simboloMonetario;
	}

	public void setSimboloMonetario(String simboloMonetario) {
		this.simboloMonetario = simboloMonetario;
	}

	public Set<BigDecimal> getValores() {
		return valores;
	}

	public void setValores(SortedSet<BigDecimal> valores) {
		this.valores = valores;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
