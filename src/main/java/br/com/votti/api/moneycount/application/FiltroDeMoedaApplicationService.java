package br.com.votti.api.moneycount.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.FiltroDeMoedaDTO;
import br.com.votti.api.moneycount.application.dto.assembler.FiltroDeMoedaAssembler;
import br.com.votti.api.moneycount.domain.FiltroDeMoeda;
import br.com.votti.api.moneycount.domain.Moeda;
import br.com.votti.api.moneycount.domain.ServicoDeMoeda;

/**
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
@Service
public class FiltroDeMoedaApplicationService {

	
	@Autowired
	private ServicoDeMoeda servicoMoeda;
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public FiltroDeMoedaDTO obterFiltroParaMoeda(String codigoDaMoeda) {
		
		Moeda moeda = servicoMoeda.obterMoeda(codigoDaMoeda);
		FiltroDeMoeda filtro = new FiltroDeMoeda(moeda);
		FiltroDeMoedaDTO dto = new FiltroDeMoedaAssembler().assembleDTO(filtro);
		return dto;
	}
}
