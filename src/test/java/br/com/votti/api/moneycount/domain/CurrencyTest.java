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

import br.com.votti.api.moneycount.infrastructure.CurrencyRepositoryJson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={
		CurrencyService.class, 
		CurrencyRepository.class, 
		CurrencyRepositoryJson.class})
public class CurrencyTest {
	
	@Autowired
	private CurrencyService currencyService;
	
	@Test
	public void testSuccessfullyCreateCurrency() {
		
		Currency moeda = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
		
		assertThat(moeda, is(notNullValue()));
		assertThat(moeda.getCode(), is(notNullValue()));
		assertThat("USD", is(equalTo(moeda.getCode())));
		
		assertThat(moeda.getName(), is(notNullValue()));
		assertThat("Dollar", is(equalTo(moeda.getName())));
		
		assertThat(moeda.getSymbol(), is(notNullValue()));
		assertThat("$", is(equalTo(moeda.getSymbol())));
		
		assertThat(moeda.getValues(), is(notNullValue()));
		assertThat(moeda.getValues(), not(empty()));
		assertThat(12, is(equalTo(moeda.getValues().size())));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComCodigoVazio() {
		new Currency("", "Dollar", "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComCodigoNulo() {
		new Currency(null, "Dollar", "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComDescricaoVazia() {
		new Currency("USD", "", "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComDescricaoNula() {
		new Currency("USD", null, "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComSimboloMonetarioVazio() {
		new Currency("USD", "Dollar", "", CurrencyValueConstructorForTests.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComSimboloMonetarioNulo() {
		new Currency("USD", "Dollar", null, CurrencyValueConstructorForTests.getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComValoresVazios() {
		new Currency("USD", "Dollar", "$", new TreeSet<>());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComValoresNulo() {
		new Currency("USD", "Dollar", "$", null);
	}
	
	
	@Test
	public void testarComparacaoDeMoedasIguais() {
		
		Currency dollar1 = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
		Currency dollar2 = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
		
		assertThat(dollar1, is(equalTo(dollar2)));
		assertThat(dollar2, is(equalTo(dollar1)));
		assertThat(dollar2, is(not(equalTo(null))));
		assertThat(dollar2, is(not(equalTo("Moeda"))));
		assertThat(dollar2, is(equalTo(dollar2)));
		
		Set<Currency> moedas = new HashSet<>();
		moedas.add(dollar1);
		moedas.add(dollar2);
		
		assertThat(1, is(equalTo(moedas.size())));
	}
	
	
	
	@Test
	public void testarComparacaoDeMoedasDiferentes() {
		
		Currency dollar = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getValoresMoedaDollar());
		Currency real = new Currency("BRL", "Real", "R$", CurrencyValueConstructorForTests.getValoresMoedaReal());
		
		Currency fakedollar1 = new Currency("USD", "Real", "R$", CurrencyValueConstructorForTests.getValoresMoedaReal());
		Currency fakedollar2 = new Currency("USD", "Dollar", "R$", CurrencyValueConstructorForTests.getValoresMoedaReal());
		Currency fakedollar3 = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getValoresMoedaReal());
		
		assertThat(dollar, is(not(equalTo(real))));
		assertThat(real, is(not(equalTo(dollar))));
		
		assertThat(dollar, is(not(equalTo(fakedollar1))));
		assertThat(dollar, is(not(equalTo(fakedollar2))));
		assertThat(dollar, is(not(equalTo(fakedollar3))));
		
		assertThat(real, is(equalTo((real))));
		
		Set<Currency> moedas = new HashSet<>();
		moedas.add(dollar);
		moedas.add(real);
		
		assertThat(2, is(equalTo(moedas.size())));
	}
	
	
	@Test
	public void testarCriacaoDeListadeMoedasSuportadas() throws JsonProcessingException {
		
		Currency dollar = currencyService.getCurrency("USD");
		Currency real = currencyService.getCurrency("BRL");
		
		SupportedCurrencies moedasSuportadas = new SupportedCurrencies();
		moedasSuportadas.addCurrency(dollar);
		moedasSuportadas.addCurrency(real);
		
		ObjectMapper mapper = new ObjectMapper();

		String moedasJson = mapper.writeValueAsString(moedasSuportadas);
		
		System.out.println(moedasJson);
	}
}
