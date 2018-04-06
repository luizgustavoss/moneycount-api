package br.com.votti.api.moneycount.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.votti.api.moneycount.application.dto.MoedaDTO;
import br.com.votti.api.moneycount.application.dto.assembler.MoedaDTOAssembler;
import br.com.votti.api.moneycount.domain.ServicoDeMoeda;

/**
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
@Service
public class MoedaApplicationService {

	@Autowired
	private ServicoDeMoeda servicoDeMoeda;
	
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public MoedaDTO obterMoeda(String codigo) {
		
		return new MoedaDTOAssembler()
			.assembleDTO(servicoDeMoeda.obterMoeda(codigo));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<MoedaDTO> obterMoedasDisponiveis(){
		
		return new MoedaDTOAssembler()
			.assembleManyDTO(servicoDeMoeda.obterMoedasDisposiveis());
	}
}
