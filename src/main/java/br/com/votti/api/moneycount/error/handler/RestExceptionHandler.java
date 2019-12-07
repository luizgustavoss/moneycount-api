package br.com.votti.api.moneycount.error.handler;

import br.com.votti.api.moneycount.error.ErrorDetail;
import br.com.votti.api.moneycount.error.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Autowired
	public RestExceptionHandler(MessageSource messageSource){
		this.messageSource = messageSource;
	}

	@ExceptionHandler(org.springframework.boot.context.config.ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleResourceNotFoundException(org.springframework.boot.context.config.ResourceNotFoundException rnfe,
                                                             HttpServletRequest request) {

		ErrorDetail errorDetail = buildErrorDetail(HttpStatus.NOT_FOUND.value(),
				"Resource Not Found", rnfe.getMessage(), rnfe.getClass().getName(), null);
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(br.com.votti.api.moneycount.exceptions.ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleCustomResourceNotFoundException(br.com.votti.api.moneycount.exceptions.ResourceNotFoundException rnfe,
															 HttpServletRequest request) {

		ErrorDetail errorDetail = buildErrorDetail(HttpStatus.NOT_FOUND.value(),
				"Resource Not Found", rnfe.getMessage(), rnfe.getClass().getName(), null);

		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = buildErrorDetail(status.value(),
				"Message Not Readable", ex.getMessage(), ex.getClass().getName(), null);
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri",
				RequestAttributes.SCOPE_REQUEST);

		ErrorDetail errorDetail = buildErrorDetail(HttpStatus.BAD_REQUEST.value(),
				"Validation Failed", "Input validation failed", manve.getClass().getName(), requestPath);

		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();

		for (FieldError fe : fieldErrors) {

			List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());

			if (validationErrorList == null) {
				validationErrorList = new ArrayList<ValidationError>();
				errorDetail.getErrors().put(fe.getField(), validationErrorList);
			}

			ValidationError validationError = new ValidationError();
			validationError.setCode(fe.getCode());
			validationError.setMessage(fe.getDefaultMessage());
			validationError.setMessage(messageSource.getMessage(fe, null));
			validationErrorList.add(validationError);
		}

		return handleExceptionInternal(manve, errorDetail, headers, status, request);
	}


	private ErrorDetail buildErrorDetail(int status, String title, String detail, String developerMessage, String path){

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status);
		errorDetail.setTitle(title);
		errorDetail.setPath(path);
		errorDetail.setDetail(detail);
		errorDetail.setDeveloperMessage(developerMessage);

		return errorDetail;
	}
}
