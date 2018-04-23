package br.com.votti.api.moneycount.application.dto.assembler;

import br.com.votti.api.moneycount.application.dto.CurrencyFilterDTO;
import br.com.votti.api.moneycount.domain.CurrencyFilter;

public class CurrencyFilterDTOAssembler {

	public CurrencyFilterDTO assembly(CurrencyFilter filtroDeMoeda) {
		if(filtroDeMoeda == null)
			return null;
		
		CurrencyFilterDTO dto = CurrencyFilterDTO.builder()
				.values(filtroDeMoeda.getFilter()).build();
        return dto;
	}
}
