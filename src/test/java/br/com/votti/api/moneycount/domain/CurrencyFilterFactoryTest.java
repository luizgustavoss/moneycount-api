package br.com.votti.api.moneycount.domain;

import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyFilterFactoryTest {

    @InjectMocks
    private CurrencyFilterFactory currencyFilterFactory;

    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private CurrencyService currencyService;

    @Test
    public void testCreationForAValidCurrencyCode(){

        Optional<Currency> usdCurrency = Optional.of( createValidCurrency() );
        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());

        when(currencyService.getCurrency("USD")).thenReturn(usdCurrency);
        CurrencyFilter cFilter = currencyFilterFactory.getCurrencyFilter("USD");
        assertEquals(cFilter.getCurrency().getCode(), "USD" );
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testCreationForAInvalidCurrencyCode(){

        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());
        Optional<Currency> usdCurrency = Optional.of( createValidCurrency() );
        when(currencyService.getCurrency("FOO")).thenReturn(Optional.empty());
        CurrencyFilter cFilter = currencyFilterFactory.getCurrencyFilter("FOO");
    }

    private Currency createValidCurrency(){
        return new Currency("USD", "Dollar", "$", CurrencyValueConstructorForTests.getCurrencyValuesInDollar());
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
