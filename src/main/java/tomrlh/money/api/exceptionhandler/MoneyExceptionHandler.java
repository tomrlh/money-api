package tomrlh.money.api.exceptionhandler;

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
public class MoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("invalid_message", null, LocaleContextHolder.getLocale());
        List<Error> errors = Arrays.asList(new Error(userMessage));
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        List<Error> errors = createErrorsList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> createErrorsList(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            errors.add(new Error(userMessage));
        }
        return errors;
    }
    
    class Error {
        private String userMessage;
    
        public Error(String userMessage) {
            this.userMessage = userMessage;
        }
    
        public String getUserMessage() {
            return this.userMessage;
        }
    }
}