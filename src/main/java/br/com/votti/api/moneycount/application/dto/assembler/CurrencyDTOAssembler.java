package br.com.votti.api.moneycount.application.dto.assembler;

import java.util.ArrayList;
import java.util.List;

import br.com.votti.api.moneycount.application.dto.CurrencyDTO;
import br.com.votti.api.moneycount.domain.Currency;

public class CurrencyDTOAssembler {

	public CurrencyDTO assembly(Currency moeda) {

		if(moeda == null)
			return null;

		CurrencyDTO dto = CurrencyDTO.builder()
			.code(moeda.getCode())
			.name(moeda.getName())
			.symbol(moeda.getSymbol())
			.build();
		
        return dto;
	}
	
	public List<CurrencyDTO> assembly(List<Currency> moedas){
		
		List<CurrencyDTO> dtos = new ArrayList<>();
		for(Currency moeda : moedas)
			dtos.add(assembly(moeda));
		return dtos;
	}

}
