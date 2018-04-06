package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FiltroDeMoeda permite configurar quais valores de uma {@link Moeda} 
 * serão consideradas no momento de montar um mapa de moedas para os lançamentos de um evento.<p>
 * 
 * Por padrão todos os valores de uma moeda serão considerados no filtro, a menos que configurados
 * para serem desconsiderados.
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
public class FiltroDeMoeda {
	
	private Moeda moeda;	
	private Map<BigDecimal, Boolean> filtro;
	
	public FiltroDeMoeda() {
		
		super();
		
		this.filtro = new HashMap<>();
	}

	/**
	 * Uma instância de FiltroDeMoeda só faz sentido se relacionada a uma {@link Moeda}
	 * @param moeda
	 */
	public FiltroDeMoeda(Moeda moeda) {
		
		this();
		
		if(moeda == null) {
			throw new IllegalArgumentException("[FiltroDeMoedas] Invalid constructor argument!");
		}
		
		this.moeda = moeda;
		
		for (BigDecimal valor : this.moeda.getValores()) {
			this.filtro.put(valor, true);
		}
	}
	
	
	/**
	 * Retorna a {@link Moeda} configurada para o filtro.
	 * 
	 * @return
	 */
	public Moeda obterMoeda() {
		return this.moeda;
	}
	
	
	
	/**
	 * Retorna o estado do filtro de moeda
	 * 
	 * @return
	 */
	public Map<BigDecimal, Boolean> obterFiltro(){
		return Collections.unmodifiableMap(this.filtro);
	}
	
	
	/**
	 * Utilizado para alterações individuais de valores do filtro
	 * 
	 * @param valor o valor da moeda a ser ou não utilizado
	 * @param utilizar indica se o valor será utilizado no filtro
	 */
	public void alterarFiltro(BigDecimal valor, Boolean utilizar) {
		
		/* só alterar valores que de fato existam no filtro, 
		 * para não permitir incluir valores inválidos para a moeda */
		if(this.filtro.containsKey(valor)) {
			this.filtro.put(valor, utilizar);
		}
	}
	
	
	/**
	 * Utilizado para alterações em lote de valores do filtro
	 * 
	 * @param filtro mapa com as definições a serem alteradas no filtro
	 */
	public void alterarFiltro(Map<BigDecimal, Boolean> filtro) {
		for(BigDecimal valor : filtro.keySet()) {
			
			/* só alterar valores que de fato existam no filtro, 
			 * para não permitir incluir valores inválidos para a moeda */
			if(this.filtro.containsKey(valor)) {
				this.filtro.put(valor, filtro.get(valor));
			}
		}		
	}
	
	/**
	 * Retorna os valores configurados como válidos para o filtro em ordem decrescente de valor
	 * 
	 * @return
	 */
	public List<BigDecimal> obterValoresValidosDoFiltro(){

		List<BigDecimal> valoresValidos = new ArrayList<>();
		
		this.filtro.keySet().stream().forEach( value -> {
			if(this.filtro.get(value)) {
				valoresValidos.add(value);
			}
		});
		
		Collections.sort(valoresValidos);
		Collections.reverse(valoresValidos);
		
		return valoresValidos;
	}
		
}
