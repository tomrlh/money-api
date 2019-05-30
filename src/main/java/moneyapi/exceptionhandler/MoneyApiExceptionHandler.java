package moneyapi.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
		return handleExceptionInternal(ex, new Erro(msgUser, msgDev), headers, HttpStatus.BAD_REQUEST, request);
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
