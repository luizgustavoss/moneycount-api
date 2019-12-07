package br.com.votti.api.moneycount.infrastructure;

import br.com.votti.api.moneycount.domain.CurrencyRepository;
import br.com.votti.api.moneycount.domain.SupportedCurrencies;
import br.com.votti.api.moneycount.exceptions.ResourceNotFoundException;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class CurrencyRepositoryJson implements CurrencyRepository {

    private ResourceLoader resourceLoader;

	@Autowired
	public CurrencyRepositoryJson(ResourceLoader resourceLoader){
		this.resourceLoader = resourceLoader;
	}
	
	public SupportedCurrencies getSupportedCurrencies() throws ResourceNotFoundException {
		
		try {
			return loadSupportedCurrencies();
		} catch (IOException | RuntimeException e) {
			throw new ResourceNotFoundException(e.getMessage(), e);
		}
	}
	
	private SupportedCurrencies loadSupportedCurrencies() throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		return gson.fromJson(readCurrencyDefinitionFile(), SupportedCurrencies.class);
	}

	private String readCurrencyDefinitionFile() throws IOException {
		String content = IOUtils.toString(resourceLoader.getResource("classpath:currencies.json").getInputStream());
		
		return content;
	}
}
