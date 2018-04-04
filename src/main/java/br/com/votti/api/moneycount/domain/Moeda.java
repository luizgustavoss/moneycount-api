package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.SortedSet;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;

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
@Getter
@EqualsAndHashCode(of={"codigo", "nome", "simboloMonetario", "valores"})
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

}
