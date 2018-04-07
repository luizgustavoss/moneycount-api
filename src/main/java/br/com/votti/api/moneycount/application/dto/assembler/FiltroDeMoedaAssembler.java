package br.com.votti.api.moneycount.application.dto.assembler;

import br.com.votti.api.moneycount.application.dto.FiltroDeMoedaDTO;
import br.com.votti.api.moneycount.domain.FiltroDeMoeda;

/**
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
public class FiltroDeMoedaAssembler {

	/**
	 * 
	 * @param filtroDeMoeda
	 * @return
	 */
	public FiltroDeMoedaDTO assembleDTO(FiltroDeMoeda filtroDeMoeda) {

		if(filtroDeMoeda == null) {
			return null;
		}
		
		FiltroDeMoedaDTO dto = FiltroDeMoedaDTO.builder()
				.notas(filtroDeMoeda.obterFiltro()).build();
		
        return dto;
	}
}
