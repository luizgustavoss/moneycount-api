package br.com.votti.api.moneycount.infrastructure;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import br.com.votti.api.moneycount.domain.MoedasSuportadas;
import br.com.votti.api.moneycount.domain.RepositorioDeMoedas;

@Repository
public class RepositorioDeMoedasJson implements RepositorioDeMoedas {

	
	public MoedasSuportadas obterMoedas() {
		
		MoedasSuportadas moedasSuportadas = null;
		
		try {
			File arquivo = lerArquivoDeDefinicaoDeMoedas();
			Gson gson = new Gson();
			moedasSuportadas = gson.fromJson(new FileReader(arquivo), MoedasSuportadas.class);
		} catch (IOException e) {
			moedasSuportadas = new MoedasSuportadas();
		}
		
		return moedasSuportadas;
	}
	
	
	private File lerArquivoDeDefinicaoDeMoedas() {
		ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("moedas.json").getFile());
		return file;
	}
}
