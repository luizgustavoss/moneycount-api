package br.com.votti.api.moneycount.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.votti.api.moneycount.infrastructure.RepositorioDeMoedasJson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={
		FabricaDeMoeda.class, 
		RepositorioDeMoedas.class, 
		RepositorioDeMoedasJson.class})
public class MoedaTest {
	
	@Autowired
	private FabricaDeMoeda fabricaDeMoeda;
	
	@Test
	public void testarCriacaoDeMoedaComSucesso() {
		
		Moeda moeda = new Moeda("USD", "Dollar", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
		
		assertThat(moeda, is(notNullValue()));
		assertThat(moeda.getCodigo(), is(notNullValue()));
		assertThat("USD", is(equalTo(moeda.getCodigo())));
		
		assertThat(moeda.getNome(), is(notNullValue()));
		assertThat("Dollar", is(equalTo(moeda.getNome())));
		
		assertThat(moeda.getSimboloMonetario(), is(notNullValue()));
		assertThat("$", is(equalTo(moeda.getSimboloMonetario())));
		
		assertThat(moeda.getValores(), is(notNullValue()));
		assertThat(moeda.getValores(), not(empty()));
		assertThat(12, is(equalTo(moeda.getValores().size())));
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComCodigoVazio() {
		
		new Moeda("", "Dollar", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComCodigoNulo() {
		
		new Moeda(null, "Dollar", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComDescricaoVazia() {
		
		new Moeda("USD", "", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComDescricaoNula() {
		
		new Moeda("USD", null, "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComSimboloMonetarioVazio() {
		
		new Moeda("USD", "Dollar", "", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComSimboloMonetarioNulo() {
		
		new Moeda("USD", "Dollar", null, ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComValoresVazios() {
		
		new Moeda("USD", "Dollar", "$", new TreeSet<>());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComValoresNulo() {
		
		new Moeda("USD", "Dollar", "$", null);
	}
	
	
	@Test
	public void testarComparacaoDeMoedasIguais() {
		
		Moeda dollar1 = new Moeda("USD", "Dollar", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
		Moeda dollar2 = new Moeda("USD", "Dollar", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
		
		assertThat(dollar1, is(equalTo(dollar2)));
		assertThat(dollar2, is(equalTo(dollar1)));
		assertThat(dollar2, is(not(equalTo(null))));
		assertThat(dollar2, is(not(equalTo("Moeda"))));
		assertThat(dollar2, is(equalTo(dollar2)));
		
		Set<Moeda> moedas = new HashSet<>();
		moedas.add(dollar1);
		moedas.add(dollar2);
		
		assertThat(1, is(equalTo(moedas.size())));
	}
	
	
	
	@Test
	public void testarComparacaoDeMoedasDiferentes() {
		
		Moeda dollar = new Moeda("USD", "Dollar", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaDollar());
		Moeda real = new Moeda("BRL", "Real", "R$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaReal());
		
		Moeda fakedollar1 = new Moeda("USD", "Real", "R$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaReal());
		Moeda fakedollar2 = new Moeda("USD", "Dollar", "R$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaReal());
		Moeda fakedollar3 = new Moeda("USD", "Dollar", "$", ConstrutorDeValoresDeMoedaParaTeste.getValoresMoedaReal());
		
		assertThat(dollar, is(not(equalTo(real))));
		assertThat(real, is(not(equalTo(dollar))));
		
		assertThat(dollar, is(not(equalTo(fakedollar1))));
		assertThat(dollar, is(not(equalTo(fakedollar2))));
		assertThat(dollar, is(not(equalTo(fakedollar3))));
		
		assertThat(real, is(equalTo((real))));
		
		Set<Moeda> moedas = new HashSet<>();
		moedas.add(dollar);
		moedas.add(real);
		
		assertThat(2, is(equalTo(moedas.size())));
	}
	
	
	@Test
	public void testarCriacaoDeListadeMoedasSuportadas() throws JsonProcessingException {
		
		Moeda dollar = fabricaDeMoeda.obterMoeda("USD");
		Moeda real = fabricaDeMoeda.obterMoeda("BRL");
		
		MoedasSuportadas moedasSuportadas = new MoedasSuportadas();
		moedasSuportadas.adicionarMoeda(dollar);
		moedasSuportadas.adicionarMoeda(real);
		
		ObjectMapper mapper = new ObjectMapper();

		String moedasJson = mapper.writeValueAsString(moedasSuportadas);
		
		System.out.println(moedasJson);
	}
}
