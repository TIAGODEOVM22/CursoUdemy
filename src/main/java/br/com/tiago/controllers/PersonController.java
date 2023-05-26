package br.com.tiago.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	private PersonServices personServices;

	/*------------------METODO FINDBYID---------------*/
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, 
						MediaType.APPLICATION_XML_VALUE })
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		PersonVO personVo = personServices.findById(id);
		personVo.add(WebMvcLinkBuilder.linkTo(
					 WebMvcLinkBuilder.methodOn(PersonController.class).
					 findById(id)).withSelfRel());
		personVo.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(PersonController.class).
				findAll()).withRel("Persons"));
		return personVo;
	}
	
	/*---------------METODO FINDALL--------------*/
	@GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE, 
							MediaType.APPLICATION_XML_VALUE })
	public Collection <PersonVO> findAll(){
		List<PersonVO> listPerson = personServices.findAll();
			listPerson.stream().
			forEach(p-> {
				try {
					p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).
							findById(p.getId())).withSelfRel());
					p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).
							findAll()).withRel("Persons"));
							
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
	
	
		return listPerson;
	}

		
	/*----------CREATE--------*/
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		PersonVO personVo = personServices.create(person);
		personVo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).
				findById(personVo.getId())).withSelfRel());
		return personVo;
	}
	
	/*----------CREATE v2--------*/
	@PostMapping( value = "/v2", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public PersonVOv2 createV2(@RequestBody PersonVOv2 person) throws Exception {
		PersonVOv2 personV2 = personServices.createV2(person);
		personV2.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).
				findById(personV2.getId())).withSelfRel());
		return personV2;
	}
	
	/*----------UPDATE--------*/
	@PutMapping ( 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public PersonVO update (@RequestBody PersonVO person) throws Exception {
		PersonVO personVo = personServices.update(person);
		personVo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).
				findById(personVo.getId())).withSelfRel());
		return personVo;
	}

	
	/*-----------DELETE-----------*/
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
		personServices.delete(id);
		return ResponseEntity.noContent().build();
		/*noContent irá retornar statuscod 204*/
	}
	
	
}
