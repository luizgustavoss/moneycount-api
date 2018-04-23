package br.com.votti.api.moneycount.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.CurrencyDTO;
import br.com.votti.api.moneycount.application.dto.assembler.CurrencyDTOAssembler;
import br.com.votti.api.moneycount.domain.CurrencyService;

@Service
public class CurrencyApplicationService {

	@Autowired
	private CurrencyService currencyService;
	
	public CurrencyDTO getCurrency(String code) {
		return new CurrencyDTOAssembler().assembly(currencyService.getCurrency(code));
	}
	
	public List<CurrencyDTO> getAvailableCurrencies(){
		return new CurrencyDTOAssembler().assembly(currencyService.getAvailableCurrencies());
	}
}
