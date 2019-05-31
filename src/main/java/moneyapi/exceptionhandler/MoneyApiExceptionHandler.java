package moneyapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
		List<Erro> errors = Arrays.asList(new Erro(userMessage, developerMessage));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request
	) {
		List<Erro> errors = createErrorsList(ex.getBindingResult());
		return super.handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	
	
	
	
	private List<Erro> createErrorsList(BindingResult bindingResult) {
		List<Erro> errors = new ArrayList<>();
		
		for(FieldError error : bindingResult.getFieldErrors()) {
			errors.add(new Erro(
					messageSource.getMessage(error, LocaleContextHolder.getLocale()),
					error.toString()
			));
		}
		return errors;
	}
	
	class Erro {
		public String userMessage;  
		public String developerMessage;
		
		public Erro(String userMessage, String developerMessage) {
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;		
		}
	}
}
