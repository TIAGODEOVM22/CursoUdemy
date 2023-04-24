package br.com.tiago.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.tiago.exceptions.ExceptionResponse;

@ControllerAdvice /*Concentra tratamento de exc. nos controllers*/
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	/*Retorna nossa Excessão personalizada*/
	public final ResponseEntity <ExceptionResponse> handleAllExceptions(
			Exception ex, WebRequest request){/*recebe isso como parametro*/
		/*criando nossa excessaõ*/
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		/*redireciona nossa exc. para o statusCod InternalServerError*/
		return new ResponseEntity<> (exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	/*Retorna nossa Excessão personalizada*/
	public final ResponseEntity <ExceptionResponse> handleNotFoundExceptions(
			Exception ex, WebRequest request){/*recebe isso como parametro*/
		/*criando nossa excessaõ*/
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		/*redireciona nossa exc. para o statusCod*/
		return new ResponseEntity<> (exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
}
	