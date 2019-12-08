package br.com.votti.api.moneycount.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    public void testGetValidCurrency()  {

        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());
        Optional<Currency> opt = currencyService.getCurrency("BRL");
        assertTrue(opt.isPresent());
        Currency currency = opt.get();
        assertEquals("BRL", currency.getCode());
    }


    @Test
    public void testGetInvalidCurrency()  {

        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());
        Optional<Currency> opt = currencyService.getCurrency("FOO");
        assertFalse(opt.isPresent());

    }


    private SupportedCurrencies createSupportedCurrencies(){

        Currency dollar = new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
        Currency real = new Currency("BRL", "Real", "R$", CurrencyValueConstructorForTests.getCurrencyValuesInReal());

        SupportedCurrencies supportedCurrencies = new SupportedCurrencies();
        supportedCurrencies.addCurrency(dollar);
        supportedCurrencies.addCurrency(real);

        return supportedCurrencies;
    }
}
