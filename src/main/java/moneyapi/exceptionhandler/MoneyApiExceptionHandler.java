package moneyapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MoneyApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request
	) {
		String userMessage = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause().toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request
	) {
		List<Error> errors = createErrorsList(ex.getBindingResult());
		return super.handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	
	
	
	
	private List<Error> createErrorsList(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();
		
		for(FieldError error : bindingResult.getFieldErrors()) {
			errors.add(new Error(
					messageSource.getMessage(error, LocaleContextHolder.getLocale()),
					error.toString()
			));
		}
		return errors;
	}
	
	class Error {
		public String userMessage;  
		public String developerMessage;
		
		public Error(String userMessage, String developerMessage) {
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;		
		}
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String devMessage = ex.toString();
		List<Error> erros = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	
	@ExceptionHandler({ DataIntegrityViolationException.class, EntityNotFoundException.class, IllegalStateException.class, JpaObjectRetrievalFailureException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.operation-not-permitted", null, LocaleContextHolder.getLocale());
		String devMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
