package br.com.tiago.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.model.Person;
import br.com.tiago.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices personServices;

	/*----------CREATE--------*/
	@RequestMapping ( method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, /*cosome JSON*/
			produces = MediaType.APPLICATION_JSON_VALUE) /*Produz JSON*/
	public Person create(@RequestBody Person person) {
		
		return personServices.create(person);
	}
	
	/*----------UPDATE--------*/
	@RequestMapping ( method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE, /*cosome JSON*/
			produces = MediaType.APPLICATION_JSON_VALUE) /*Produz JSON*/
	public Person update (@RequestBody Person person) {
		return personServices.update(person);
	}
	
	
	
	/*-----------DELETE-----------*/
	@RequestMapping(value = "/{id}"
			, method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id")Long id){
	personServices.delete(id);
	}
	
	
	/*------------------METODO FINDBYID---------------*/
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") Long id) throws Exception {

		return personServices.findById(id);
	}
	
	/*---------------METODO FINDALL--------------*/
	@RequestMapping 
			( method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return personServices.findAll();
	}

}
