package br.com.votti.api.moneycount.domain;

import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyMapFactoryTest {

    @InjectMocks
    private CurrencyMapFactory currencyMapFactory;

    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private CurrencyService currencyService;

    @Test
    public void testCreateValidCurrencyMap(){

        Currency dollar = createValidCurrency();
        Optional<Currency> usdCurrency = Optional.of( dollar );
        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());

        when(currencyService.getCurrency("USD")).thenReturn(usdCurrency);

        CurrencyMap currencyMap = currencyMapFactory.createCurrencyMap(new BigDecimal("245.55"), "USD", getCurrencyFilter(dollar));
        assertNotNull( currencyMap );
        assertEquals(new BigDecimal("245.55"), currencyMap.getTotalValue());
        assertEquals(new BigDecimal("0.00"), currencyMap.getRemainingValue());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateCurrencyMapWithInvalidValueZero(){

        Currency dollar = createValidCurrency();
        Optional<Currency> usdCurrency = Optional.of( dollar );
        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());

        when(currencyService.getCurrency("USD")).thenReturn(usdCurrency);

        CurrencyMap currencyMap = currencyMapFactory.createCurrencyMap(new BigDecimal("0.0"), "USD", getCurrencyFilter(dollar));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateCurrencyMapWithInvalidFilterMap(){

        Currency dollar = createValidCurrency();
        Optional<Currency> usdCurrency = Optional.of( dollar );
        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());

        when(currencyService.getCurrency("USD")).thenReturn(usdCurrency);

        CurrencyMap currencyMap = currencyMapFactory.createCurrencyMap(new BigDecimal("200.0"), "USD", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateCurrencyMapWithInvalidValueNegative(){

        Currency dollar = createValidCurrency();
        Optional<Currency> usdCurrency = Optional.of( dollar );
        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());

        when(currencyService.getCurrency("USD")).thenReturn(usdCurrency);

        CurrencyMap currencyMap = currencyMapFactory.createCurrencyMap(new BigDecimal("-234.50"), "USD", getCurrencyFilter(dollar));
    }


    @Test(expected = ResourceNotFoundException.class)
    public void testCreateCurrencyMapWithInvalidCurrencyCode(){

        Currency dollar = createValidCurrency();
        when(currencyRepository.getSupportedCurrencies()).thenReturn(createSupportedCurrencies());

        when(currencyService.getCurrency("FOO")).thenReturn(Optional.empty());

        CurrencyMap currencyMap = currencyMapFactory.createCurrencyMap(new BigDecimal("245.50"), "FOO", getCurrencyFilter(dollar));
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

    private Map<BigDecimal, Boolean> getCurrencyFilter(Currency currency){
        return new CurrencyFilter(currency).getFilter();
    }
}
