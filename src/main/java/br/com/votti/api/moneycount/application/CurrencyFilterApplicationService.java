package br.com.votti.api.moneycount.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.CurrencyFilterDTO;
import br.com.votti.api.moneycount.application.dto.assembler.CurrencyFilterDTOAssembler;
import br.com.votti.api.moneycount.domain.CurrencyFilter;
import br.com.votti.api.moneycount.domain.Currency;
import br.com.votti.api.moneycount.domain.CurrencyService;

@Service
public class CurrencyFilterApplicationService {

	@Autowired
	private CurrencyService currencyService;
	
	public CurrencyFilterDTO getFilterForCurrency(String currencyCode) {
		Currency currency = currencyService.getCurrency(currencyCode);
		CurrencyFilter filter = new CurrencyFilter(currency);
		CurrencyFilterDTO dto = new CurrencyFilterDTOAssembler().assembly(filter);
		return dto;
	}
}
