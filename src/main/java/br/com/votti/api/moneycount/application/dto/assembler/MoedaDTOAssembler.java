package br.com.votti.api.moneycount.application.dto.assembler;

import java.util.ArrayList;
import java.util.List;

import br.com.votti.api.moneycount.application.dto.MoedaDTO;
import br.com.votti.api.moneycount.domain.Moeda;

/**
 * Assembler de DTO para Moeda
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
public class MoedaDTOAssembler {

	/**
	 * Cria uma instância de {@link MoedaDTO} a partir de uma instância de {@link Moeda).
	 *  
	 * @param moeda instância de moeda.
	 * 
	 * @return uma instância de MoedaDTO para a moeda informada.
	 */
	public MoedaDTO assembleDTO(Moeda moeda) {

		if(moeda == null) {
			return null;
		}
		
		MoedaDTO dto = MoedaDTO.builder()
			.codigo(moeda.getCodigo())
			.nome(moeda.getNome())
			.simboloMonetario(moeda.getSimboloMonetario())
			.build();
		
        return dto;
	}
	
	
	/**
	 * Cria uma lista de instâncias de {@link MoedaDTO} a partir de uma lista de instâncias de {@link Moeda}
	 * 
	 * @param moedas lista de instâncias de {@link Moeda}
	 * 
	 * @return uma lista de instâncias de {@link MoedaDTO}
	 */
	public List<MoedaDTO> assembleManyDTO(List<Moeda> moedas){
		
		List<MoedaDTO> dtos = new ArrayList<>();
		
		for(Moeda moeda : moedas) {
			dtos.add(assembleDTO(moeda));
		}
		
		return dtos;
	}

}
