package br.com.tiago.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*Essa classe retorna o COD de Erro BadRequest*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException{

	
	public UnsupportedMathOperationException(String ex) {
		super(ex);
	}

	private static final long serialVersionUID = 1L;

	
	
	
}
