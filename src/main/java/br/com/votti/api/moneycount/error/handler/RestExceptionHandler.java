package br.com.votti.api.moneycount.error.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
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

import br.com.votti.api.moneycount.error.ErrorDetail;
import br.com.votti.api.moneycount.error.ValidationError;

/**
 * Handler class responsible for handle exceptions on controllers.
 * 
 * The ResponseEntityExceptionHandler class contains a set of protected methods
 * that handle standard exception and return a ResponseEntity instance
 * containing error details. Extending the ResponseEntityExceptionHandler class
 * allows us to override the protected method associated with the exception and
 * return an ErrorDetail instance.
 * 
 * @author Luiz Gustavo S. de Souza (luizgustavoss@gmail.com)
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Handler method responsible for catching ResourceNotFoundException
	 * exceptions
	 * 
	 * @param rnfe
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe,
			HttpServletRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle("Resource Not Found");
		errorDetail.setDetail(rnfe.getMessage());
		errorDetail.setDeveloperMessage(rnfe.getClass().getName());

		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status.value());
		errorDetail.setTitle("Message Not Readable");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());

		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	/**
	 * Handler method responsible for catching MethodArgumentNotValidException
	 * exceptions
	 * 
	 * @param manve
	 * @param request
	 * @return
	 */
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

		String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri",
				RequestAttributes.SCOPE_REQUEST);

		errorDetail.setTitle("Validation Failed");
		errorDetail.setPath(requestPath);
		errorDetail.setDetail("Input validation failed");
		errorDetail.setDeveloperMessage(manve.getClass().getName());

		// Create ValidationError instances
		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();

		for (FieldError fe : fieldErrors) {

			List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());

			if (validationErrorList == null) {
				validationErrorList = new ArrayList<ValidationError>();
				errorDetail.getErrors().put(fe.getField(), validationErrorList);
			}

			ValidationError validationError = new ValidationError();
			validationError.setCode(fe.getCode());
			/* validationError.setMessage(fe.getDefaultMessage()); */
			validationError.setMessage(messageSource.getMessage(fe, null));
			validationErrorList.add(validationError);
		}

		return handleExceptionInternal(manve, errorDetail, headers, status, request);
	}

}
