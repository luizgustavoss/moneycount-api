package br.com.votti.api.moneycount.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CurrencyMapTest {


	@Test
	public void testarCalculoDeMapaDeNotasDoLancamentoComValorValidoSemResto() throws JsonProcessingException {
		
		Currency moeda = obterMoedaReal();
		CurrencyFilter filtro = new CurrencyFilter(moeda);
		BigDecimal valor = new BigDecimal("197.00");
		
		CurrencyMap mapa = new CurrencyMap(valor, moeda, filtro);
		
		Map<BigDecimal, Integer> mapaMoeda = mapa.getMap();
		BigDecimal valorRestante = mapa.getRemainingValue();
		
		assertThat(0, is(equalTo(valorRestante.compareTo(BigDecimal.ZERO))));
		
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("0.05")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("0.10")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("0.25")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("0.5")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("1")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("2")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("5")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("10")))));
		assertThat(new Integer(2), is(equalTo(mapaMoeda.get(new BigDecimal("20")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("50")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("100")))));
		
	}
	
	
	
	@Test
	public void testarCalculoDeMapaDeNotasDoLancamentoComValorValidoComResto() throws JsonProcessingException {
		
		Currency moeda = obterMoedaReal();
		CurrencyFilter filtro = new CurrencyFilter(moeda);
		BigDecimal valor = new BigDecimal("197.33");
		
		CurrencyMap mapa = new CurrencyMap(valor, moeda, filtro);
		
		Map<BigDecimal, Integer> mapaMoeda = mapa.getMap();
		BigDecimal valorRestante = mapa.getRemainingValue();
		
		assertThat(0, is(equalTo(valorRestante.compareTo(new BigDecimal("0.03")))));
		
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("0.05")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("0.10")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("0.25")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("0.5")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("1")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("2")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("5")))));
		assertThat(new Integer(0), is(equalTo(mapaMoeda.get(new BigDecimal("10")))));
		assertThat(new Integer(2), is(equalTo(mapaMoeda.get(new BigDecimal("20")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("50")))));
		assertThat(new Integer(1), is(equalTo(mapaMoeda.get(new BigDecimal("100")))));
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemValor() throws JsonProcessingException {
		
		Currency moeda = obterMoedaReal();
		CurrencyFilter filtro = new CurrencyFilter(moeda);
		BigDecimal valor = null;
		
		new CurrencyMap(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaComTodosParametrosInvalidos() throws JsonProcessingException {
		
		Currency moeda = null;
		CurrencyFilter filtro = null;
		BigDecimal valor = null;
		
		new CurrencyMap(valor, moeda, filtro);
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemFiltro() throws JsonProcessingException {
		
		Currency moeda = obterMoedaReal();
		CurrencyFilter filtro = null;
		BigDecimal valor = new BigDecimal("197.33");
		
		new CurrencyMap(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemMoeda() throws JsonProcessingException {
		
		Currency moeda = null;
		CurrencyFilter filtro = new CurrencyFilter(obterMoedaReal());
		BigDecimal valor = new BigDecimal("197.33");
		
		new CurrencyMap(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaComFiltroInvalido() throws JsonProcessingException {
		
		Currency real = obterMoedaReal();
		Currency dollar = obterMoedaDollar();
		
		CurrencyFilter filtro = new CurrencyFilter(dollar);
		BigDecimal valor = new BigDecimal("197.33");
		
		new CurrencyMap(valor, real, filtro);
	}
	
	
	
	public Currency obterMoedaReal() {
		
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
		
		Currency moeda = new Currency("BRL", "Real", "R$", valoresMoeda);
		
		return moeda;
	}
	
	
	public Currency obterMoedaDollar() {
		
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
		
		Currency moeda = new Currency("USD", "Dollar", "$", valoresMoeda);
		
		return moeda;
	}


}
