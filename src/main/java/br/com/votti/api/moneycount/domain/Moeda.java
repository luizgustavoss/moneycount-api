package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Uma Moeda permite representar moedas atualmente em circulação em determinados países 
 * ou territórios dependentes.<p> 
 * 
 * Existem diferentes definições para "moeda": <p>
 * 
 * <ul>
 * 	<li>o dinheiro, que constitui as notas (geralmente em papel); 
 *  <li>a moeda (a peça metálica);
 *  <li>a moeda bancária ou escritural, admitidas em circulação;
 *	<li>a moeda no sentido mais amplo, que significa o dinheiro em circulação, a moeda nacional. 
 * </ul>
 * 
 * Neste caso, a classe Moeda representa a moeda bancária ou escritural, admitida em circulação.
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
public class Moeda {

	private String codigo;
	private String nome;
	private String simboloMonetario;
	private SortedSet<BigDecimal> valores;
	
	public Moeda(String codigo, String nome, String simboloMonetario, SortedSet<BigDecimal> valores) {
		
		if( StringUtils.isEmpty(codigo) || StringUtils.isEmpty(nome) ||
				StringUtils.isEmpty(simboloMonetario) || CollectionUtils.isEmpty(valores) ) {
			
			throw new IllegalArgumentException("[Moeda] Invalid constructor argument!");
		}
		
		this.codigo = codigo;
		this.nome = nome;
		this.simboloMonetario = simboloMonetario;
		this.valores = valores;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public String getSimboloMonetario() {
		return simboloMonetario;
	}

	public Set<BigDecimal> getValores() {
		return valores;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo.hashCode();
		result = prime * result + nome.hashCode();
		result = prime * result + simboloMonetario.hashCode();
		result = prime * result + valores.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moeda other = (Moeda) obj;
		if (!codigo.equals(other.codigo)) {
			return false;
		}
		if (!nome.equals(other.nome)) {
			return false;
		}
		if (!simboloMonetario.equals(other.simboloMonetario)) {
			return false;
		}
		if (!valores.equals(other.valores)) {
			return false;
		}
		return true;
	}

}
