package br.com.tiago.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tiago.model.Person;

@Service
public class PersonServices {
	
	
	private final AtomicLong counter = new AtomicLong();/*Mocar ID*/
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	/*-------------CREATE-------------*/
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return person;
	}
	
	/*-------------UPDATE-------------*/
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		return person;
	}
	
	/*-------------DELETE-------------*/
	public void delete(String id) {
		logger.info("deleting one person!");
		
		
	}
	
	/*-------------FINDBYID-------------*/
	public Person findById(String id) {
		
		logger.info("Finding one Person!");
	
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Tiago");
		person.setLastName("Oliveira");
		person.setAddress("Corumbá - MS - Brasil");
		person.setGender("male");

		return person;
	}

	/*-------------FINDALL-------------*/
	public List<Person> findAll() {
		
		logger.info("Finding all people!");
		
		List<Person> persons = new ArrayList<>();/*declara lista*/
		for (int i = 0; i<8; i++ ) {/*itera em um array de 0a8*/
			Person person = mockPerson(i);/*moca uma pessoa para ITEM da lista*/
			persons.add(person);/*add a listagem*/
		}
		return persons;
	}

	private Person mockPerson(int i) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name" + i);
		person.setLastName("Last name" + i);
		person.setAddress("Some address in Brasil" + i);
		person.setGender("male");
		
		return person;
	}
	
	
}
