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
public class MapaDeNotaTest {


	@Test
	public void testarCalculoDeMapaDeNotasDoLancamentoComValorValidoSemResto() throws JsonProcessingException {
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		BigDecimal valor = new BigDecimal("197.00");
		
		MapaDeNota mapa = new MapaDeNota(valor, moeda, filtro);
		
		Map<BigDecimal, Integer> mapaMoeda = mapa.obterMapa();
		BigDecimal valorRestante = mapa.obterValorRestante();
		
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
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		BigDecimal valor = new BigDecimal("197.33");
		
		MapaDeNota mapa = new MapaDeNota(valor, moeda, filtro);
		
		Map<BigDecimal, Integer> mapaMoeda = mapa.obterMapa();
		BigDecimal valorRestante = mapa.obterValorRestante();
		
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
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		BigDecimal valor = null;
		
		new MapaDeNota(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaComTodosParametrosInvalidos() throws JsonProcessingException {
		
		Moeda moeda = null;
		FiltroDeMoeda filtro = null;
		BigDecimal valor = null;
		
		new MapaDeNota(valor, moeda, filtro);
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemFiltro() throws JsonProcessingException {
		
		Moeda moeda = obterMoedaReal();
		FiltroDeMoeda filtro = null;
		BigDecimal valor = new BigDecimal("197.33");
		
		new MapaDeNota(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaSemMoeda() throws JsonProcessingException {
		
		Moeda moeda = null;
		FiltroDeMoeda filtro = new FiltroDeMoeda(obterMoedaReal());
		BigDecimal valor = new BigDecimal("197.33");
		
		new MapaDeNota(valor, moeda, filtro);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMapaDeNotaComFiltroInvalido() throws JsonProcessingException {
		
		Moeda real = obterMoedaReal();
		Moeda dollar = obterMoedaDollar();
		
		FiltroDeMoeda filtro = new FiltroDeMoeda(dollar);
		BigDecimal valor = new BigDecimal("197.33");
		
		new MapaDeNota(valor, real, filtro);
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
