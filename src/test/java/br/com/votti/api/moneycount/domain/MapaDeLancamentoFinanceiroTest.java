package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class MapaDeLancamentoFinanceiroTest {


	@Test
	public void testCalcularMapaDeNotasDoLancamento() {
		
		LancamentoFinanceiro lancamento = new LancamentoFinanceiro("28723470423", new BigDecimal("137.91"));
		Moeda moeda = obterMoeda();
		
		MapaDeLancamentoFinanceiro mapa = new MapaDeLancamentoFinanceiro(lancamento, moeda);
		
		List<BigDecimal> valoresMoeda = new ArrayList<>();
		valoresMoeda.add(new BigDecimal("0.5"));
		valoresMoeda.add(new BigDecimal("10"));
		valoresMoeda.add(new BigDecimal("20"));
		valoresMoeda.add(new BigDecimal("0.5"));
		
		Map<BigDecimal, BigDecimal> mapaDeNotas = mapa.calcularMapaDeNotasDoLancamento(valoresMoeda);
		
		System.out.println(mapaDeNotas);
		
	}
	
	
	public Moeda obterMoeda() {
		
		Moeda moeda = new Moeda("BRL", "Real", "R$");
		SortedSet<BigDecimal> valoresMoeda = new TreeSet<>();
		valoresMoeda.add(new BigDecimal("0.05"));
		valoresMoeda.add(new BigDecimal("0.10"));
		valoresMoeda.add(new BigDecimal("0.25"));
		valoresMoeda.add(new BigDecimal("0.5"));
		valoresMoeda.add(new BigDecimal("1"));
		valoresMoeda.add(new BigDecimal("2"));
		valoresMoeda.add(new BigDecimal("5"));
		valoresMoeda.add(new BigDecimal("10"));
		valoresMoeda.add(new BigDecimal("20"));
		valoresMoeda.add(new BigDecimal("50"));
		valoresMoeda.add(new BigDecimal("100"));
		moeda.setValores(valoresMoeda);
		
		return moeda;
	}


}
