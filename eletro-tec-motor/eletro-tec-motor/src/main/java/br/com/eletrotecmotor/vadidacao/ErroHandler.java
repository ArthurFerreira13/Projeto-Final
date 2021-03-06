package br.com.eletrotecmotor.vadidacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroHandler {
	@Autowired
	private MessageSource messagemSource;
	@ResponseStatus (code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroCadastroDto> hanble (MethodArgumentNotValidException exception) {
		List<ErroCadastroDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e ->{
			String mensagem = messagemSource.getMessage(e, LocaleContextHolder.getLocale());
					ErroCadastroDto erro = new ErroCadastroDto (e.getField(),mensagem);
					dto.add(erro);
		});
		return dto;
	}
}
