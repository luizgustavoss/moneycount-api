package br.com.votti.api.moneycount.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class MoedaTest {

	@Test
	public void testarCriacaoDeMoedaComSucesso() {
		
		Moeda moeda = new Moeda("USD", "Dollar", "$", getValoresMoedaDollar());
		
		Assert.assertNotNull(moeda);
		Assert.assertNotNull(moeda.getCodigo());
		Assert.assertEquals("USD", moeda.getCodigo());
		
		Assert.assertNotNull(moeda.getNome());
		Assert.assertEquals("Dollar", moeda.getNome());
		
		Assert.assertNotNull(moeda.getSimboloMonetario());
		Assert.assertEquals("$", moeda.getSimboloMonetario());
		
		Assert.assertNotNull(moeda.getValores());
		Assert.assertFalse(moeda.getValores().isEmpty());
		Assert.assertEquals(12, moeda.getValores().size());
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComCodigoVazio() {
		
		new Moeda("", "Dollar", "$", getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComCodigoNulo() {
		
		new Moeda(null, "Dollar", "$", getValoresMoedaDollar());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComDescricaoVazia() {
		
		new Moeda("USD", "", "$", getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComDescricaoNula() {
		
		new Moeda("USD", null, "$", getValoresMoedaDollar());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComSimboloMonetarioVazio() {
		
		new Moeda("USD", "Dollar", "", getValoresMoedaDollar());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testarCriacaoDeMoedaComSimboloMonetarioNulo() {
		
		new Moeda("USD", "Dollar", null, getValoresMoedaDollar());
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
		
		Moeda dollar1 = new Moeda("USD", "Dollar", "$", getValoresMoedaDollar());
		Moeda dollar2 = new Moeda("USD", "Dollar", "$", getValoresMoedaDollar());
		
		Assert.assertTrue(dollar1.equals(dollar2));
		Assert.assertTrue(dollar2.equals(dollar1));
		
		Assert.assertFalse(dollar2.equals(null));
		
		Assert.assertFalse(dollar2.equals("Moeda"));
		
		Assert.assertTrue(dollar2.equals(dollar2));
		
		Set<Moeda> moedas = new HashSet<>();
		moedas.add(dollar1);
		moedas.add(dollar2);
		
		Assert.assertEquals(1, moedas.size());
	}
	
	
	
	@Test
	public void testarComparacaoDeMoedasDiferentes() {
		
		Moeda dollar = new Moeda("USD", "Dollar", "$", getValoresMoedaDollar());
		Moeda real = new Moeda("BRL", "Real", "R$", getValoresMoedaReal());
		
		Moeda fakedollar1 = new Moeda("USD", "Real", "R$", getValoresMoedaReal());
		Moeda fakedollar2 = new Moeda("USD", "Dollar", "R$", getValoresMoedaReal());
		Moeda fakedollar3 = new Moeda("USD", "Dollar", "$", getValoresMoedaReal());
		
		Assert.assertFalse(dollar.equals(real));
		Assert.assertFalse(real.equals(dollar));
		
		Assert.assertFalse(dollar.equals(fakedollar1));
		Assert.assertFalse(dollar.equals(fakedollar2));
		Assert.assertFalse(dollar.equals(fakedollar3));
		
		Assert.assertTrue(real.equals(real));
		
		Set<Moeda> moedas = new HashSet<>();
		moedas.add(dollar);
		moedas.add(real);
		
		Assert.assertEquals(2, moedas.size());
	}
	
	
	
	private SortedSet<BigDecimal> getValoresMoedaDollar(){
		
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
	
	
	private SortedSet<BigDecimal> getValoresMoedaReal(){
		
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
