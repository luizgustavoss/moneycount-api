package br.com.votti.api.moneycount.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.CurrencyFilterDTO;
import br.com.votti.api.moneycount.application.dto.assembler.CurrencyFilterDTOAssembler;
import br.com.votti.api.moneycount.domain.CurrencyFilterFactory;

@Service
public class CurrencyFilterApplicationService {

	@Autowired
	private CurrencyFilterFactory currencyFilterService;
	
	public CurrencyFilterDTO getFilterForCurrency(String currencyCode) {
		CurrencyFilterDTO dto = new CurrencyFilterDTOAssembler()
				.assembly(currencyFilterService.getCurrencyFilter(currencyCode));
		return dto;
	}
}
