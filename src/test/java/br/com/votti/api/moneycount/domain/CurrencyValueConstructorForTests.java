package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

public class CurrencyValueConstructorForTests {

	
	public static SortedSet<BigDecimal> getValoresMoedaDollar(){
		
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
		
		return valoresMoeda;
	}
	
	
	public static SortedSet<BigDecimal> getValoresMoedaReal(){
		
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
		
		return valoresMoeda;
	}
}
