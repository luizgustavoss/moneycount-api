package br.com.votti.api.moneycount.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@RunWith(MockitoJUnitRunner.class)
public class CurrencyMapTest {


	@Test
	public void testCalculateCurrencyMapForValidValueWithoutChange() throws JsonProcessingException {
		
		Currency currency = getCurrencyReal();
		CurrencyFilter filter = new CurrencyFilter(currency);
		BigDecimal value = new BigDecimal("197.00");
		
		CurrencyMap currencyMap = new CurrencyMap(value, currency, filter);
		
		Map<BigDecimal, Integer> map = currencyMap.getMap();
		BigDecimal changeValue = currencyMap.getRemainingValue();
		
		assertThat(0, is(equalTo(changeValue.compareTo(BigDecimal.ZERO))));
		
		assertThat(new Integer(0), is(equalTo(map.get(new BigDecimal("0.05")))));
		assertThat(new Integer(0), is(equalTo(map.get(new BigDecimal("0.10")))));
		assertThat(new Integer(0), is(equalTo(map.get(new BigDecimal("0.25")))));
		assertThat(new Integer(0), is(equalTo(map.get(new BigDecimal("0.5")))));
		assertThat(new Integer(0), is(equalTo(map.get(new BigDecimal("1")))));
		assertThat(new Integer(1), is(equalTo(map.get(new BigDecimal("2")))));
		assertThat(new Integer(1), is(equalTo(map.get(new BigDecimal("5")))));
		assertThat(new Integer(0), is(equalTo(map.get(new BigDecimal("10")))));
		assertThat(new Integer(2), is(equalTo(map.get(new BigDecimal("20")))));
		assertThat(new Integer(1), is(equalTo(map.get(new BigDecimal("50")))));
		assertThat(new Integer(1), is(equalTo(map.get(new BigDecimal("100")))));
		
	}
	
	
	
	@Test
	public void testCalculateCurrencyMapForValidValueWithChange() throws JsonProcessingException {
		
		Currency currency = getCurrencyReal();
		CurrencyFilter filter = new CurrencyFilter(currency);
		BigDecimal value = new BigDecimal("197.33");
		
		CurrencyMap map = new CurrencyMap(value, currency, filter);
		
		Map<BigDecimal, Integer> currencyMap = map.getMap();
		BigDecimal changeValue = map.getRemainingValue();
		
		assertThat(0, is(equalTo(changeValue.compareTo(new BigDecimal("0.03")))));
		
		assertThat(new Integer(1), is(equalTo(currencyMap.get(new BigDecimal("0.05")))));
		assertThat(new Integer(0), is(equalTo(currencyMap.get(new BigDecimal("0.10")))));
		assertThat(new Integer(1), is(equalTo(currencyMap.get(new BigDecimal("0.25")))));
		assertThat(new Integer(0), is(equalTo(currencyMap.get(new BigDecimal("0.5")))));
		assertThat(new Integer(0), is(equalTo(currencyMap.get(new BigDecimal("1")))));
		assertThat(new Integer(1), is(equalTo(currencyMap.get(new BigDecimal("2")))));
		assertThat(new Integer(1), is(equalTo(currencyMap.get(new BigDecimal("5")))));
		assertThat(new Integer(0), is(equalTo(currencyMap.get(new BigDecimal("10")))));
		assertThat(new Integer(2), is(equalTo(currencyMap.get(new BigDecimal("20")))));
		assertThat(new Integer(1), is(equalTo(currencyMap.get(new BigDecimal("50")))));
		assertThat(new Integer(1), is(equalTo(currencyMap.get(new BigDecimal("100")))));
		
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCurrencyMapWithoutValue() throws JsonProcessingException {
		
		Currency currency = getCurrencyReal();
		CurrencyFilter filter = new CurrencyFilter(currency);
		BigDecimal value = null;
		
		new CurrencyMap(value, currency, filter);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCurrencyMapWithAllInvalidParameters() throws JsonProcessingException {
		
		Currency currency = null;
		CurrencyFilter filter = null;
		BigDecimal value = null;
		
		new CurrencyMap(value, currency, filter);
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCurrencyMapWithoutFilter() throws JsonProcessingException {
		
		Currency currency = getCurrencyReal();
		CurrencyFilter filter = null;
		BigDecimal value = new BigDecimal("197.33");
		
		new CurrencyMap(value, currency, filter);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCurrencyMapWithoutCurrency() throws JsonProcessingException {
		
		Currency currency = null;
		CurrencyFilter filter = new CurrencyFilter(getCurrencyReal());
		BigDecimal value = new BigDecimal("197.33");
		
		new CurrencyMap(value, currency, filter);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCurrencyMapWithInvalidFilter() throws JsonProcessingException {
		
		Currency real = getCurrencyReal();
		Currency dollar = getCurrencyDollar();
		
		CurrencyFilter filter = new CurrencyFilter(dollar);
		BigDecimal value = new BigDecimal("197.33");
		
		new CurrencyMap(value, real, filter);
	}
	
	
	
	public Currency getCurrencyReal() {
		
		SortedSet<BigDecimal> currencyValues = new TreeSet<>();
		currencyValues.add(new BigDecimal("50"));
		currencyValues.add(new BigDecimal("0.05"));
		currencyValues.add(new BigDecimal("0.10"));
		currencyValues.add(new BigDecimal("0.25"));
		currencyValues.add(new BigDecimal("20"));
		currencyValues.add(new BigDecimal("0.5"));
		currencyValues.add(new BigDecimal("10"));
		currencyValues.add(new BigDecimal("1"));
		currencyValues.add(new BigDecimal("2"));
		currencyValues.add(new BigDecimal("5"));
		currencyValues.add(new BigDecimal("10"));
		currencyValues.add(new BigDecimal("50"));
		currencyValues.add(new BigDecimal("0.5"));
		currencyValues.add(new BigDecimal("100"));
		
		Currency currency = new Currency("BRL", "Real", "R$", currencyValues);
		
		return currency;
	}
	
	
	public Currency getCurrencyDollar() {
		
		SortedSet<BigDecimal> currencyValues = new TreeSet<>();
		currencyValues.add(new BigDecimal("50"));
		currencyValues.add(new BigDecimal("0.05"));
		currencyValues.add(new BigDecimal("0.10"));
		currencyValues.add(new BigDecimal("0.25"));
		currencyValues.add(new BigDecimal("20"));
		currencyValues.add(new BigDecimal("0.5"));
		currencyValues.add(new BigDecimal("0.01"));
		currencyValues.add(new BigDecimal("10"));
		currencyValues.add(new BigDecimal("1"));
		currencyValues.add(new BigDecimal("2"));
		currencyValues.add(new BigDecimal("5"));
		currencyValues.add(new BigDecimal("10"));
		currencyValues.add(new BigDecimal("50"));
		currencyValues.add(new BigDecimal("0.5"));
		currencyValues.add(new BigDecimal("100"));
		
		Currency currency = new Currency("USD", "Dollar", "$", currencyValues);
		
		return currency;
	}


}
