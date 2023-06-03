package br.com.tiago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiago.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
