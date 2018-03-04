package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class MapaDeMoedaTest {


	@Test
	public void testarCalculoDeMapaDeNotasDoLancamentoValorValidoSemResto() throws JsonProcessingException {
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		BigDecimal valor = new BigDecimal("197.00");
		
		MapaDeMoeda mapa = new MapaDeMoeda(valor, moeda, filtro);
		
		Map<BigDecimal, Integer> mapaMoeda = mapa.obterMapa();
		BigDecimal valorRestante = mapa.obterValorRestante();
		
		Assert.assertEquals(0, valorRestante.compareTo(BigDecimal.ZERO));
		
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("0.05")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("0.10")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("0.25")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("0.5")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("1")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("2")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("5")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("10")));
		Assert.assertEquals(new Integer(2), mapaMoeda.get(new BigDecimal("20")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("50")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("100")));
		
	}
	
	
	
	@Test
	public void testarCalculoDeMapaDeNotasDoLancamentoValorValidoComResto() throws JsonProcessingException {
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		BigDecimal valor = new BigDecimal("197.33");
		
		MapaDeMoeda mapa = new MapaDeMoeda(valor, moeda, filtro);
		
		Map<BigDecimal, Integer> mapaMoeda = mapa.obterMapa();
		BigDecimal valorRestante = mapa.obterValorRestante();
		
		Assert.assertEquals(0, valorRestante.compareTo(new BigDecimal("0.03")));
		
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("0.05")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("0.10")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("0.25")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("0.5")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("1")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("2")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("5")));
		Assert.assertEquals(new Integer(0), mapaMoeda.get(new BigDecimal("10")));
		Assert.assertEquals(new Integer(2), mapaMoeda.get(new BigDecimal("20")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("50")));
		Assert.assertEquals(new Integer(1), mapaMoeda.get(new BigDecimal("100")));
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemValor() throws JsonProcessingException {
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		BigDecimal valor = null;
		
		new MapaDeMoeda(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaComTodosParametrosInvalidos() throws JsonProcessingException {
		
		Moeda moeda = null;
		FiltroDeMoeda filtro = null;
		BigDecimal valor = null;
		
		new MapaDeMoeda(valor, moeda, filtro);
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemFiltro() throws JsonProcessingException {
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = null;
		BigDecimal valor = new BigDecimal("197.33");
		
		new MapaDeMoeda(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemMoeda() throws JsonProcessingException {
		
		Moeda moeda = null;
		FiltroDeMoeda filtro = new FiltroDeMoeda(obterMoedaReal());
		BigDecimal valor = new BigDecimal("197.33");
		
		new MapaDeMoeda(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaComFiltroInvalido() throws JsonProcessingException {
		
		Moeda real = obterMoedaReal();
		Moeda dollar = obterMoedaDollar();
		
		FiltroDeMoeda filtro = new FiltroDeMoeda(dollar);
		BigDecimal valor = new BigDecimal("197.33");
		
		new MapaDeMoeda(valor, real, filtro);
	}
	
	
	
	public Moeda obterMoedaReal() {
		
		SortedSet<BigDecimal> valoresMoeda = new TreeSet<>();
		valoresMoeda.add(new BigDecimal("50"));
		valoresMoeda.add(new BigDecimal("0.05"));		
		valoresMoeda.add(new BigDecimal("0.10"));
		valoresMoeda.add(new BigDecimal("0.25"));		
		valoresMoeda.add(new BigDecimal("20"));
		valoresMoeda.add(new BigDecimal("0.5"));
		valoresMoeda.add(new BigDecimal("10"));
		valoresMoeda.add(new BigDecimal("1"));
		valoresMoeda.add(new BigDecimal("2"));
		valoresMoeda.add(new BigDecimal("5"));
		valoresMoeda.add(new BigDecimal("10"));		
		valoresMoeda.add(new BigDecimal("50"));
		valoresMoeda.add(new BigDecimal("0.5"));
		valoresMoeda.add(new BigDecimal("100"));
		
		Moeda moeda = new Moeda("BRL", "Real", "R$", valoresMoeda);
		
		return moeda;
	}
	
	
	public Moeda obterMoedaDollar() {
		
		SortedSet<BigDecimal> valoresMoeda = new TreeSet<>();
		valoresMoeda.add(new BigDecimal("50"));
		valoresMoeda.add(new BigDecimal("0.05"));		
		valoresMoeda.add(new BigDecimal("0.10"));
		valoresMoeda.add(new BigDecimal("0.25"));		
		valoresMoeda.add(new BigDecimal("20"));
		valoresMoeda.add(new BigDecimal("0.5"));
		valoresMoeda.add(new BigDecimal("0.01"));
		valoresMoeda.add(new BigDecimal("10"));
		valoresMoeda.add(new BigDecimal("1"));
		valoresMoeda.add(new BigDecimal("2"));
		valoresMoeda.add(new BigDecimal("5"));
		valoresMoeda.add(new BigDecimal("10"));		
		valoresMoeda.add(new BigDecimal("50"));
		valoresMoeda.add(new BigDecimal("0.5"));
		valoresMoeda.add(new BigDecimal("100"));
		
		Moeda moeda = new Moeda("USD", "Dollar", "$", valoresMoeda);
		
		return moeda;
	}


}
