package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

/**
 * LancamentoFinanceiro representa um valor com uma identificação única. <p>
 * 
 * Um LancamentoFinanceiro pode representar, por exemplo, um valor de aluguel, 
 * uma prestação do carro, ou uma compra. Cada um desses valores em meses consecutivos 
 * podem ou não apresentar um mesmo valor, mas cada um possui uma identificação única,
 * como o número da prestação ou o mês em que ocorrem.
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
public class LancamentoFinanceiro {

	private String id;
	private String codigo;
	private String descricao;
	private BigDecimal valor;
	
	public LancamentoFinanceiro(String id, String codigo, String descricao, BigDecimal valor) {
		
		if( StringUtils.isEmpty(id) || StringUtils.isEmpty(codigo) || StringUtils.isEmpty(descricao) ||
				valor == null || valor.equals(BigDecimal.ZERO)) {
			
			throw new IllegalArgumentException("[LancamentoFinanceiro] Invalid constructor argument!");
		}
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
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
	
	public BigDecimal getValor() {
		return valor;
	}
}
