package br.com.votti.api.moneycount.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * Classe de fábrica responsável pela criação de instâncias de moedas 
 * a partir de seu código.
 * 
 * Nesta primeira versão da aplicação, as definições de moeda 
 * não serão mantidas em uma base de dados, portanto a fábrica 
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
@Component
public class FabricaDeMoeda {

	@Autowired
	private RepositorioDeMoedas repositorioDeMoedas;
	
	private static Map<String, Moeda> moedasDisponiveis = null;
	
	public Moeda obterMoeda(String codigo) {
		
		if(Strings.isNullOrEmpty(codigo) || !obterMoedasDisponiveis().containsKey(codigo)) {
			return null;
		}
		
		return obterMoedasDisponiveis().get(codigo);
	}
	
	
	private Map<String, Moeda> obterMoedasDisponiveis(){
		
		if(FabricaDeMoeda.moedasDisponiveis == null) {
			
			FabricaDeMoeda.moedasDisponiveis = new HashMap<String, Moeda>();
			MoedasSuportadas moedasSuportadas = repositorioDeMoedas.obterMoedas();
			
			if(moedasSuportadas == null) {
				return FabricaDeMoeda.moedasDisponiveis;
			}
			
			for(Moeda moeda : moedasSuportadas.getMoedasSuportadas()){
				FabricaDeMoeda.moedasDisponiveis.put(moeda.getCodigo(), moeda);
			}
		}
		
		return FabricaDeMoeda.moedasDisponiveis;
	}
	
	
	
}
