package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

public class CurrencyValueConstructorForTests {

	
	public static SortedSet<BigDecimal> getCurrencyValuesInDollar(){
		
		SortedSet<BigDecimal> currencyValues = new TreeSet<>();

		currencyValues.add(new BigDecimal("0.01"));
		currencyValues.add(new BigDecimal("0.05"));
		currencyValues.add(new BigDecimal("0.10"));
		currencyValues.add(new BigDecimal("0.25"));
		currencyValues.add(new BigDecimal("0.5"));
		currencyValues.add(new BigDecimal("1"));
		currencyValues.add(new BigDecimal("2"));
		currencyValues.add(new BigDecimal("5"));
		currencyValues.add(new BigDecimal("10"));
		currencyValues.add(new BigDecimal("20"));
		currencyValues.add(new BigDecimal("50"));
		currencyValues.add(new BigDecimal("100"));
		
		return currencyValues;
	}
	
	
	public static SortedSet<BigDecimal> getCurrencyValuesInReal(){
		
		SortedSet<BigDecimal> currencyValues = new TreeSet<>();

		currencyValues.add(new BigDecimal("0.05"));
		currencyValues.add(new BigDecimal("0.10"));
		currencyValues.add(new BigDecimal("0.25"));
		currencyValues.add(new BigDecimal("0.5"));
		currencyValues.add(new BigDecimal("1"));
		currencyValues.add(new BigDecimal("2"));
		currencyValues.add(new BigDecimal("5"));
		currencyValues.add(new BigDecimal("10"));
		currencyValues.add(new BigDecimal("20"));
		currencyValues.add(new BigDecimal("50"));
		currencyValues.add(new BigDecimal("100"));
		
		return currencyValues;
	}
}
