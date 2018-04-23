package br.com.votti.api.moneycount.infrastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import br.com.votti.api.moneycount.domain.SupportedCurrencies;
import br.com.votti.api.moneycount.domain.CurrencyRepository;
import br.com.votti.api.moneycount.exceptions.SupportedCurrencyDefNotFoundException;

@Repository
public class CurrencyRepositoryJson implements CurrencyRepository {

	public SupportedCurrencies getSupportedCurrencies() throws SupportedCurrencyDefNotFoundException {
		
		try {
			return loadSupportedCurrencies();
		} catch (FileNotFoundException | RuntimeException e) {
			throw new SupportedCurrencyDefNotFoundException(e.getMessage(), e);
		}
	}
	
	private SupportedCurrencies loadSupportedCurrencies() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		File file = readCurrencyDefinitionFile();
		Gson gson = new Gson();
		return gson.fromJson(new FileReader(file), SupportedCurrencies.class);
	}
	
	private File readCurrencyDefinitionFile() {
		ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("currencies.json").getFile());
		return file;
	}
}
