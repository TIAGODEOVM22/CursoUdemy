package br.com.tiago.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.data.vo.v1.PersonVO;
import br.com.tiago.exceptions.ResourceNotFoundException;
import br.com.tiago.mapper.DozerMapper;
import br.com.tiago.model.Person;
import br.com.tiago.repository.PersonRepsitory;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepsitory personRepsitory;

	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	/*-------------FINDBYID-------------*/
	public PersonVO findById(Long id) {
		logger.info("Finding one Person!");		

		var entity = personRepsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	/*-------------FINDALL-------------*/
	public List<PersonVO> findAll() {
		logger.info("Finding all person!");
		return DozerMapper.parseListObjects(personRepsitory.findAll(), PersonVO.class)  ;
		
	}
	
	
	/*-------------CREATE-------------*/
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		
		/*recebe o VO e converte para Model*/
		var entity = DozerMapper.parseObject(person, Person.class);
		
		/*tranforma de volta para VO*/
		var vo = DozerMapper.parseObject(personRepsitory.save(entity), PersonVO.class);
		
		/*retorna VO*/
		return vo;
		
	}
	
	/*-------------UPDATE-------------*/
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		
		var obj = personRepsitory.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		obj.setFirstName(person.getFirstName());
		obj.setLastName(person.getLastName());
		obj.setAddress(person.getAddress());
		obj.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(personRepsitory.save(obj), PersonVO.class);
		return vo;
	}
	
	/*-------------DELETE-------------*/
	public void delete(Long id) {
		logger.info("deleting one person!");

		var obj = personRepsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personRepsitory.delete(obj);
	}
	
	
}
