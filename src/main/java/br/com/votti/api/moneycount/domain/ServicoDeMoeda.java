package br.com.votti.api.moneycount.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

/**
 * Serviço responsável pela criação de instâncias de moedas 
 * a partir de seu código.
 * 
 * Nesta primeira versão da aplicação, as definições de moeda 
 * não serão mantidas em uma base de dados, portanto o serviço 
 * tem por objetivo ocultar das classes cliente a forma como as
 * moedas são armazenadas, recuperadas e criadas.
 * 
 * Apenas algumas moedas serão suportadas na versão inicial de teste,
 * e posteriormente outras moedas poderão ser suportadas.
 * 
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
@Service
public class ServicoDeMoeda {

	@Autowired
	private RepositorioDeMoedas repositorioDeMoedas;
	
	private static Map<String, Moeda> moedasDisponiveis = null;
	
	/**
	 * Obtém uma moeda a partir de seu código
	 * 
	 * @param codigo código da moeda
	 * 
	 * @return uma instância de {@link Moeda}
	 * 
	 */
	public Moeda obterMoeda(String codigo) {
		
		if(Strings.isNullOrEmpty(codigo) || !obterMapaDeMoedasDisponiveis().containsKey(codigo)) {
			return null;
		}
		
		return obterMapaDeMoedasDisponiveis().get(codigo);
	}
	
	
	/**
	 * Obtém uma lista das moedas disponíveis (suportadas) pela aplicação.
	 * 
	 * @return uma lista de instâncias de {@link Moeda} suportadas pela aplicação.
	 * 
	 */
	public List<Moeda> obterMoedasDisposiveis(){
		
		List<Moeda> moedas = new ArrayList<>();
		
		moedas.addAll(obterMapaDeMoedasDisponiveis().values());
		
		return moedas;
	}
	
	
	/**
	 * Obtém um mapa das moedas suportadas pela aplicação.
	 * 
	 * @return um mapa das moedas suportadas pela aplicação, onde a chave é
	 * o código da moeda e o valor uma instâncias de {@link Moeda}
	 */
	private Map<String, Moeda> obterMapaDeMoedasDisponiveis(){
		
		if(ServicoDeMoeda.moedasDisponiveis == null) {
			
			ServicoDeMoeda.moedasDisponiveis = new HashMap<String, Moeda>();
			MoedasSuportadas moedasSuportadas = repositorioDeMoedas.obterMoedas();
			
			if(moedasSuportadas == null) {
				return ServicoDeMoeda.moedasDisponiveis;
			}
			
			for(Moeda moeda : moedasSuportadas.getMoedasSuportadas()){
				ServicoDeMoeda.moedasDisponiveis.put(moeda.getCodigo(), moeda);
			}
		}
		
		return ServicoDeMoeda.moedasDisponiveis;
	}
	
	
	
}
