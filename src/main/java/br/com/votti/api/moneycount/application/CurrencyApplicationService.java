package br.com.votti.api.moneycount.application;

import br.com.votti.api.moneycount.application.dto.CurrencyDTO;
import br.com.votti.api.moneycount.application.dto.assembler.CurrencyDTOAssembler;
import br.com.votti.api.moneycount.domain.CurrencyService;
import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class CurrencyApplicationService {

	private CurrencyService currencyService;

	@Autowired
	public CurrencyApplicationService(CurrencyService currencyService){
		this.currencyService = currencyService;
	}
	
	public CurrencyDTO getCurrency(String code) {
		if(!currencyService.getCurrency(code).isPresent())
			throw new ResourceNotFoundException(MessageFormat.format("Currency not found for code {0}", code));
		return new CurrencyDTOAssembler().assembly(currencyService.getCurrency(code).get());
	}
	
	public List<CurrencyDTO> getAvailableCurrencies(){
		return new CurrencyDTOAssembler().assembly(currencyService.getAvailableCurrenciesList());
	}
}
