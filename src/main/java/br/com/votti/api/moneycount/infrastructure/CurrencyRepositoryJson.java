package br.com.votti.api.moneycount.infrastructure;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import br.com.votti.api.moneycount.domain.CurrencyRepository;
import br.com.votti.api.moneycount.domain.SupportedCurrencies;
import br.com.votti.api.moneycount.exceptions.SupportedCurrencyDefNotFoundException;

@Repository
public class CurrencyRepositoryJson implements CurrencyRepository {

	@Autowired
    private ResourceLoader resourceLoader;
	
	public SupportedCurrencies getSupportedCurrencies() throws SupportedCurrencyDefNotFoundException {
		
		try {
			return loadSupportedCurrencies();
		} catch (IOException | RuntimeException e) {
			throw new SupportedCurrencyDefNotFoundException(e.getMessage(), e);
		}
	}
	
	private SupportedCurrencies loadSupportedCurrencies() throws JsonSyntaxException, JsonIOException, IOException {
//		File file = readCurrencyDefinitionFile();
		Gson gson = new Gson();
		return gson.fromJson(readCurrencyDefinitionFile(), SupportedCurrencies.class);
	}
	
	@SuppressWarnings("deprecation")
	private String readCurrencyDefinitionFile() throws IOException {
//		ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("currencies.json").getFile());
//		return file;
		
		String content = IOUtils.toString(resourceLoader.getResource("classpath:currencies.json").getInputStream());
		
		return content;
	}
}
