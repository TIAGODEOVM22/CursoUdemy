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

import br.com.tiago.data.vo.v1.PersonVO;
import br.com.tiago.data.vo.v2.PersonVOv2;
import br.com.tiago.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices personServices;

	/*------------------METODO FINDBYID---------------*/
	@GetMapping(value = "/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {

		return personServices.findById(id);
	}
	
	/*---------------METODO FINDALL--------------*/
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll(){
		return personServices.findAll();
	}

		
	/*----------CREATE--------*/
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, /*cosome JSON*/
			produces = MediaType.APPLICATION_JSON_VALUE) /*Produz JSON*/
	public PersonVO create(@RequestBody PersonVO person) {
		
		return personServices.create(person);
	}
	
	/*----------CREATE v2--------*/
	@PostMapping( value = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, /*cosome JSON*/
			produces = MediaType.APPLICATION_JSON_VALUE) /*Produz JSON*/
	public PersonVOv2 createV2(@RequestBody PersonVOv2 personV2) {
		
		return personServices.createV2(personV2);
	}
	
	/*----------UPDATE--------*/
	@PutMapping ( consumes = MediaType.APPLICATION_JSON_VALUE, /*cosome JSON*/
			produces = MediaType.APPLICATION_JSON_VALUE) /*Produz JSON*/
	public PersonVO update (@RequestBody PersonVO person) {
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
