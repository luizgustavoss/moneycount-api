package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * MapaDeNota representa um mapa de notas para um dado valor com base em uma {@link Moeda} e em um
 * {@link FiltroDeMoeda}.<p>
 * 
 * De acordo com as configurações de filtro, valor e moeda é possível obter 
 * um mapa das notas e suas quantidades após a divisão do valor total pelos valores configurados no filtro.<br>
 * 
 * Caso exista também será possível obter o restante da divisão que não pode ser dividido.
 * 
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
public class MapaDeNota {

	private BigDecimal valorTotal;
	private Map<BigDecimal, Integer> mapa;
	private BigDecimal valorRestante = BigDecimal.ZERO;
	private Moeda moeda;
	private FiltroDeMoeda filtro;
	
	
	public MapaDeNota(BigDecimal valorTotal, Moeda moeda, FiltroDeMoeda filtro) {
		
		if(valorTotal == null || moeda == null || filtro == null) {
			throw new IllegalArgumentException("[MapaDeMoeda] Invalid constructor argument!");
		}
		
		if(!filtro.obterMoeda().equals(moeda)) {
			throw new IllegalArgumentException("Invalid filter!");
		}
		
		this.mapa = new HashMap<>();
		
		this.valorTotal = valorTotal;
		this.moeda = moeda;
		this.filtro = filtro;
		
		this.moeda.getValores().stream().forEach(valor -> {
			mapa.put(valor, 0);
		});
		
		calcularMapaDeMoedasDoLancamento();
	}
	
	
	/**
	 * Faz o cálculo do mapa de notas para o valor total do mapa e para a moeda configurada, de 
	 * acordo com o filtro configurado.
	 */
	public void calcularMapaDeMoedasDoLancamento(){
		
		BigDecimal valorLancamento = new BigDecimal(valorTotal.toString());
		
		for(BigDecimal valorFiltro : this.filtro.obterValoresValidosDoFiltro()) {
			
			BigDecimal quantidade = valorLancamento.divideToIntegralValue(valorFiltro, MathContext.DECIMAL64);
			valorLancamento = valorLancamento.remainder(valorFiltro, MathContext.DECIMAL64);
			
			this.mapa.put(valorFiltro, quantidade.intValue());
		}
		
		this.valorRestante = valorLancamento;
	}
	
	
	/**
	 * Retorna o mapa das notas e suas quantidades para a moeda indicada.<p>
	 * 
	 * Por exemplo, assuma o valor 197.33 que precisa ser mapeado para as notas 
	 * de uma determinada moeda, cujo filtro de notas é o seguinte:<p>
	 * 
	 * <ul>
	 * 	<li> 100.00 - false
	 *  <li> 50.00 - true
	 *  <li> 20.00 - false
	 *  <li> 10.00 - true
	 *  <li> 5.00 - true
	 *  <li> 2.00 - true
	 *  <li> 1.00 - true
	 *  <li> 0.50 - true
	 *  <li> 0.25 - true
	 *  <li> 0.10 - true
	 *  <li> 0.05 - true
	 * </ul>
	 * 
	 * O mapa resultante será o seguinte:<p>
	 * 
	 * * <ul>
	 * 	<li> 100.00 - 0
	 *  <li> 50.00 - 3
	 *  <li> 20.00 - 0
	 *  <li> 10.00 - 4
	 *  <li> 5.00 - 1
	 *  <li> 2.00 - 1
	 *  <li> 1.00 - 0
	 *  <li> 0.50 - 0
	 *  <li> 0.25 - 1
	 *  <li> 0.10 - 0
	 *  <li> 0.05 - 1
	 * </ul>
	 * 
	 * @return o mapa das notas e suas quantidades para a moeda indicada.
	 */
	public Map<BigDecimal, Integer> obterMapa(){
		return Collections.unmodifiableMap(this.mapa);
	}
	
	
	/**
	 * Retorna o valor restante da divisão do valor total do mapa
	 * pelas notas configuradas para a moeda, caso exista.<p>
	 * 
	 * Por exemplo, assuma o valor 197.33 que precisa ser mapeado para as notas 
	 * de uma determinada moeda, cujo filtro de notas é o seguinte:<p>
	 * 
	 * <ul>
	 * 	<li> 100.00 - false
	 *  <li> 50.00 - true
	 *  <li> 20.00 - false
	 *  <li> 10.00 - true
	 *  <li> 5.00 - true
	 *  <li> 2.00 - true
	 *  <li> 1.00 - true
	 *  <li> 0.50 - true
	 *  <li> 0.25 - true
	 *  <li> 0.10 - true
	 *  <li> 0.05 - true
	 * </ul>
	 * 
	 * O mapa resultante será o seguinte:<p>
	 * 
	 * * <ul>
	 * 	<li> 100.00 - 0
	 *  <li> 50.00 - 3
	 *  <li> 20.00 - 0
	 *  <li> 10.00 - 4
	 *  <li> 5.00 - 1
	 *  <li> 2.00 - 1
	 *  <li> 1.00 - 0
	 *  <li> 0.50 - 0
	 *  <li> 0.25 - 1
	 *  <li> 0.10 - 0
	 *  <li> 0.05 - 1
	 * </ul>
	 * 
	 * A soma total das notas é 197.30, e o valor restante é 0.03, 
	 * porque não existe nota ou moeda pequena o bastante para dividir o valor restante.
	 * 
	 * @return o valor restante da divisão do valor total pelo filtro de notas da moeda, quando este existir
	 */
	public BigDecimal obterValorRestante() {
		return valorRestante;
	}
	
	/**
	 * 
	 */
	public BigDecimal obterValorTotal() {
		return this.valorTotal;
	}
	
}
