package br.com.tiago.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.data.vo.v1.PersonVO;
import br.com.tiago.data.vo.v2.PersonVOv2;
import br.com.tiago.exceptions.ResourceNotFoundException;
import br.com.tiago.mapper.DozerMapper;
import br.com.tiago.mapper.custom.PersonMapper;
import br.com.tiago.model.Person;
import br.com.tiago.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonMapper personMapper;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	/*-------------FINDBYID-------------*/
	public PersonVO findById(Long id) {
		logger.info("Finding one Person!");		

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	/*-------------FINDALL-------------*/
	public List<PersonVO> findAll() {
		logger.info("Finding all personVO!");
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class)  ;
		
	}
	
	
	/*-------------CREATE-------------*/
	public PersonVO create(PersonVO person) {
		logger.info("Creating one personVO!");
		
		/*recebe o VO e converte para Model*/
		var entity = DozerMapper.parseObject(person, Person.class);
		
		/*tranforma de volta para VO*/
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
		/*retorna VO*/
		return vo;
		
	}
	
	/*-------------CREATE v2-------------*/
	public PersonVOv2 createV2(PersonVOv2 personV2) {
		logger.info("Creating one personV2!");
		
		/*recebe o VOv2 e converte para Model*/
		var entity = personMapper.convertToModel(personV2);
		
		/*tranforma de volta para VOv2*/
		var vo2 = personMapper.convertEntityToV2(personRepository.save(entity));
		
		/*retorna VOv2*/
		return vo2;
		
	}
	
	/*-------------UPDATE-------------*/
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		
		var obj = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		obj.setFirstName(person.getFirstName());
		obj.setLastName(person.getLastName());
		obj.setAddress(person.getAddress());
		obj.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(personRepository.save(obj), PersonVO.class);
		return vo;
	}
	
	/*-------------DELETE-------------*/
	public void delete(Long id) {
		logger.info("deleting one person!");

		var obj = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personRepository.delete(obj);
	}
	
	
}
