package br.com.tiago.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.converters.NumberConverter;
import br.com.tiago.exceptions.UnsupportedMathOperationException;
import br.com.tiago.math.SimpleMath;

@RestController
public class MatchController {
	
	
	private final AtomicLong counter = new AtomicLong();/*Mocar ID*/
	
	private SimpleMath match = new SimpleMath();
	
	/*------------------METODO DE SOMA---------------*/
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum (
			@PathVariable(value = "numberOne") String numberOne, /*variavel da URL*/
			@PathVariable(value = "numberTwo") String numberTwo /*variavel da URL*/

			) throws Exception{
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric (numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		/*se for numérico, vou fazer a conversão para DOUBLE e realizar a SOMA*/
		return match.sum(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	/*------------------METODO DE SUBSTRAÇÃO---------------*/
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtraction(
			@PathVariable(value = "numberOne") String numberOne, /*variavel da URL*/
			@PathVariable(value = "numberTwo") String numberTwo /*variavel da URL*/

			) throws Exception{
		
		/*depois de receber as informações que o usuario passou vamos verificar
		 * se é numerico*/
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric (numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		/*se for numérico, vou fazer a conversão para DOUBLE e realizar a SOMA*/
		return match.subtraction(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}

	
	/*------------------METODO DE MULTIPLICAÇÃO---------------*/
	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double multiplication(
			@PathVariable(value = "numberOne") String numberOne, /*variavel da URL*/
			@PathVariable(value = "numberTwo") String numberTwo /*variavel da URL*/

			) throws Exception{
		
		/*depois de receber as informações que o usuario passou vamos verificar
		 * se é numerico*/
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric (numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		/*se for numérico, vou fazer a conversão para DOUBLE e realizar a SOMA*/
		return match.multiplication(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	
	/*------------------METODO DE DIVISÃO---------------*/
	@RequestMapping(value = "/division/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double division(
			@PathVariable(value = "numberOne") String numberOne, /*variavel da URL*/
			@PathVariable(value = "numberTwo") String numberTwo /*variavel da URL*/

			) throws Exception{
		
		/*depois de receber as informações que o usuario passou vamos verificar
		 * se é numerico*/
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric (numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		/*se for numérico, vou fazer a conversão para DOUBLE e realizar a SOMA*/
		return match.division(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	
	/*------------------METODO DE MÉDIA---------------*/
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double mean (
			@PathVariable(value = "numberOne") String numberOne, /*variavel da URL*/
			@PathVariable(value = "numberTwo") String numberTwo /*variavel da URL*/
 
			) throws Exception{
		
		/*depois de receber as informações que o usuario passou vamos verificar
		 * se é numerico*/
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric (numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		/*se for numérico, vou fazer a conversão para DOUBLE e realizar a SOMA*/
		return match.mean(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	

	/*------------------METODO DE RAIZ QUADRADA---------------*/
	@RequestMapping(value = "/squareRoot/{number}", method=RequestMethod.GET)
	public Double squareRoot (
			@PathVariable(value = "number") String number

			) throws Exception{
		
		/*depois de receber as informações que o usuario passou vamos verificar
		 * se é numerico*/
		if (! NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		/*se for numérico, vou fazer a conversão para DOUBLE e realizar a SOMA*/
		return match.squareRoot(NumberConverter.convertToDouble(number));
	}
	

}
