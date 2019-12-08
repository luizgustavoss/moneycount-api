package br.com.votti.api.moneycount.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyTest {

	@Test
	public void testSuccessfullyCreateCurrency() {
		
		Currency currency = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
		
		assertThat(currency, is(notNullValue()));
		assertThat(currency.getCode(), is(notNullValue()));
		assertThat("USD", is(equalTo(currency.getCode())));
		
		assertThat(currency.getName(), is(notNullValue()));
		assertThat("Dollar", is(equalTo(currency.getName())));
		
		assertThat(currency.getSymbol(), is(notNullValue()));
		assertThat("$", is(equalTo(currency.getSymbol())));
		
		assertThat(currency.getValues(), is(notNullValue()));
		assertThat(currency.getValues(), not(empty()));
		assertThat(12, is(equalTo(currency.getValues().size())));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrencyWithEmptyCode() {
		new Currency("", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrencyWithNullCode() {
		new Currency(null, "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrrencyWithEmptyDescription() {
		new Currency("USD", "", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrencyWithNullDescription() {
		new Currency("USD", null, "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrencyWithEmptySymbol() {
		new Currency("USD", "Dollar", "", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrencyWithNullSymbol() {
		new Currency("USD", "Dollar", null, CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrencyWithEmptyValues() {
		new Currency("USD", "Dollar", "$", new TreeSet<>());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCurrencyWithNullValues() {
		new Currency("USD", "Dollar", "$", null);
	}
	
	
	@Test
	public void testCompareEqualCurrencies() {
		
		Currency dollar1 = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
		Currency dollar2 = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
		
		assertThat(dollar1, is(equalTo(dollar2)));
		assertThat(dollar2, is(equalTo(dollar1)));
		assertThat(dollar2, is(not(equalTo(null))));
		assertThat(dollar2, is(not(equalTo("Moeda"))));
		assertThat(dollar2, is(equalTo(dollar2)));
		
		Set<Currency> currencies = new HashSet<>();
		currencies.add(dollar1);
		currencies.add(dollar2);
		
		assertThat(1, is(equalTo(currencies.size())));
	}

	
	@Test
	public void testCompareDifferentCurrencies() {
		
		Currency dollar = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
		Currency real = new Currency("BRL", "Real", "R$", CurrencyValueConstructorForTests.getCurrencyValuesInReal());
		
		Currency fakeDollar1 = new Currency("USD", "Real", "R$", CurrencyValueConstructorForTests.getCurrencyValuesInReal());
		Currency fakeDollar2 = new Currency("USD", "Dollar", "R$", CurrencyValueConstructorForTests.getCurrencyValuesInReal());
		Currency fakeDollar3 = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInReal());
		
		assertThat(dollar, is(not(equalTo(real))));
		assertThat(real, is(not(equalTo(dollar))));
		
		assertThat(dollar, is(not(equalTo(fakeDollar1))));
		assertThat(dollar, is(not(equalTo(fakeDollar2))));
		assertThat(dollar, is(not(equalTo(fakeDollar3))));
		
		assertThat(real, is(equalTo((real))));
		
		Set<Currency> currencies = new HashSet<>();
		currencies.add(dollar);
		currencies.add(real);
		
		assertThat(2, is(equalTo(currencies.size())));
	}
	
	

}
