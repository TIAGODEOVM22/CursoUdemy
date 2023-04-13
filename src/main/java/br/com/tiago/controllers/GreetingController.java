package br.com.tiago.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.Greeting;

@RestController
public class GreetingController {

	private static final String template = "Hellow, %s!";
	private final AtomicLong counter = new AtomicLong();/* Mocar ID */

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "WORD") /* Testar na URL */
	String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
