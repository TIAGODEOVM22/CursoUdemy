package br.com.tiago.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.tiago.data.vo.v2.PersonVOv2;
import br.com.tiago.model.Person;

@Service
public class PersonMapper {

	public PersonVOv2 convertEntityToV2(Person person) {
		PersonVOv2 voV2 = new PersonVOv2();
		voV2.setId(person.getId());
		voV2.setAddress(person.getAddress());
		voV2.setBirthDate(new Date());/*person não contém esse atributo*/
		/*isso irá suprir a falta deste atributo e setar a data atual*/
		voV2.setFirstName(person.getFirstName());
		voV2.setLastName(person.getLastName());
		voV2.setGender(person.getGender());
		return voV2;
	}
	
	public Person convertToModel(PersonVOv2 personV2) {
		Person person = new Person();
		person.setId(personV2.getId());
		person.setAddress(personV2.getAddress());
		
		person.setFirstName(personV2.getFirstName());
		person.setLastName(personV2.getLastName());
		person.setGender(personV2.getGender());
		return person;
	}
	
}
