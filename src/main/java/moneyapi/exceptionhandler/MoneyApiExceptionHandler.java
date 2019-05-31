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
		String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDev = ex.getCause().toString();
		List<Erro> erros = Arrays.asList(new Erro(msgUser, msgDev));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request
	) {
		List<Erro> erros = criarErrosList(ex.getBindingResult());
		return super.handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	
	
	
	
	private List<Erro> criarErrosList(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError erro : bindingResult.getFieldErrors()) {
			erros.add(new Erro(
					messageSource.getMessage(erro, LocaleContextHolder.getLocale()),
					erro.toString()
			));
		}
		return erros;
	}
	
	class Erro {
		public String mensagemUsuario;  
		public String mensagemDesenvolvedor;
		
		public Erro(String msgUser, String msgDev) {
			this.mensagemUsuario = msgUser;
			this.mensagemDesenvolvedor = msgDev;		
		}
	}
}
