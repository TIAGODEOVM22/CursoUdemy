package br.com.tiago.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.exceptions.ResourceNotFoundException;
import br.com.tiago.model.Person;
import br.com.tiago.repository.PersonRepsitory;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepsitory personRepsitory;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	/*-------------FINDBYID-------------*/
	public Person findById(Long id) {
		
		logger.info("Finding one Person!");
	
		Person person = new Person();
		//person.setId(counter.incrementAndGet());
		person.setFirstName("Tiago");
		person.setLastName("Oliveira");
		person.setAddress("Corumbá - MS - Brasil");
		person.setGender("male");

		return personRepsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	/*-------------FINDALL-------------*/
	public List<Person> findAll() {
		
		logger.info("Finding all person!");
		return personRepsitory.findAll();
		
	}
	
	
	/*-------------CREATE-------------*/
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return personRepsitory.save(person);
	}
	
	/*-------------UPDATE-------------*/
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		Person obj = personRepsitory.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		obj.setFirstName(person.getFirstName());
		obj.setLastName(person.getLastName());
		obj.setAddress(person.getAddress());
		obj.setGender(person.getGender());
		
		return personRepsitory.save(obj);
	}
	
	/*-------------DELETE-------------*/
	public void delete(Long id) {
		logger.info("deleting one person!");

		Person obj = personRepsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personRepsitory.delete(obj);
	}
	
	
}
