package br.com.votti.api.moneycount.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupportedCurrencyDefNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SupportedCurrencyDefNotFoundException() {
	}

	public SupportedCurrencyDefNotFoundException(String message) {
		super(message);
	}

	public SupportedCurrencyDefNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
