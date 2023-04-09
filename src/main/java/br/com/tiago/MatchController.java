package br.com.tiago;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.exceptions.UnsupportedMathOperationException;

@RestController
public class MatchController {
	
	
	private final AtomicLong counter = new AtomicLong();/*Mocar ID*/

	/*A URL tem o nome de SUM e recebe dois PATHVARIABLE
	 * se eu passar só um numero na URL a aplicação da erro
	 * o REQUESTMAPPING tem que ser igual ao definido aqui no EndPoint*/
	/*, method=RequestMethod.GET não é obrigatório porém é uma boa pratica
	 * quando eu não expecifico pow default ele ja é GET*/
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum (
			@PathVariable(value = "numberOne") String numberOne, /*variavel da URL*/
			@PathVariable(value = "numberTwo") String numberTwo /*variavel da URL*/

			) throws Exception{
		
		/*depois de receber as informações que o usuario passou vamos verificar
		 * se é numerico*/
		if (!isNumeric(numberOne) || !isNumeric (numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		/*se for numérico, vou fazer a conversão para DOUBLE e realizar a SOMA*/
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	private boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;/*verifica se é null, poderia retornar uma exceção*/
		String number = strNumber.replaceAll(",", ".");/*condiciona o usuario a poder usar , ou . nos numeros*/

		return number.matches("[-+]?[0-9]*\\.?[0-9]");
	}

	private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		String number = strNumber.replaceAll(",",".");
		if(isNumeric(number)) return Double.parseDouble(number);
		
		return null;
	}

}