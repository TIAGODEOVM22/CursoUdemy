package br.com.tiago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiago.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
