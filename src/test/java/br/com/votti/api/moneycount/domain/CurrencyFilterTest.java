package br.com.votti.api.moneycount.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyFilterTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCurrencyFilterForInvalidCurrency(){

        CurrencyFilter currencyFilter = new CurrencyFilter(null);
    }

    @Test
    public void testCreateCurrencyFilterForValidCurrency(){

        Currency currency = createValidCurrency();
        CurrencyFilter currencyFilter = new CurrencyFilter(currency);
        Assert.assertNotNull(currencyFilter.getCurrency());
        Assert.assertNotNull(currencyFilter.getFilter());
        Assert.assertEquals(currencyFilter.getCurrency().getCode(), currency.getCode());
    }

    private Currency createValidCurrency(){
        return new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
    }
}
