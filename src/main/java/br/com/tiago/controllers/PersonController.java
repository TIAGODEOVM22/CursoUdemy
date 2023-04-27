package br.com.tiago.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.model.Person;
import br.com.tiago.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices personServices;

	/*------------------METODO FINDBYID---------------*/
	@GetMapping(value = "/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") Long id) throws Exception {

		return personServices.findById(id);
	}
	
	/*---------------METODO FINDALL--------------*/
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return personServices.findAll();
	}

		
	/*----------CREATE--------*/
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, /*cosome JSON*/
			produces = MediaType.APPLICATION_JSON_VALUE) /*Produz JSON*/
	public Person create(@RequestBody Person person) {
		
		return personServices.create(person);
	}
	
	/*----------UPDATE--------*/
	@PutMapping ( consumes = MediaType.APPLICATION_JSON_VALUE, /*cosome JSON*/
			produces = MediaType.APPLICATION_JSON_VALUE) /*Produz JSON*/
	public Person update (@RequestBody Person person) {
		return personServices.update(person);
	}

	
	/*-----------DELETE-----------*/
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
		personServices.delete(id);
		return ResponseEntity.noContent().build();
		/*noContent irá retornar statuscod 204*/
	}
	
	
}
