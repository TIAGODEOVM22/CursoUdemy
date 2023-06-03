package br.com.tiago.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.data.vo.v1.BookVo;
import br.com.tiago.exceptions.ResourceNotFoundException;
import br.com.tiago.mapper.DozerMapper;
import br.com.tiago.model.Book;
import br.com.tiago.repository.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository bookRepository;
	
	//@Autowired
	//BookMapper bookMapper;
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	/*-------------FINDBYID-------------*/
	public BookVo findById(Long id) {
		logger.info("Finding one Book!");		

		var entity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		
		return DozerMapper.parseObject(entity, BookVo.class);
	}
	
	/*-------------FINDALL-------------*/
	public List<BookVo> findAll() {
		logger.info("Finding all bookVO!");
		return DozerMapper.parseListObjects(bookRepository.findAll(), BookVo.class)  ;
		
	}
	
	
	/*-------------CREATE-------------*/
	public BookVo create(BookVo book) {
		logger.info("Creating one bookVO!");
		
		/*recebe o VO e converte para Model*/
		var entity = DozerMapper.parseObject(book, Book.class);
		
		/*tranforma de volta para VO*/
		var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVo.class);
		
		/*retorna VO*/
		return vo;
		
	}
	
	
	/*-------------UPDATE-------------*/
	public BookVo update(BookVo book) {
		logger.info("Updating one book!");
		
		var obj = bookRepository.findById(book.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		obj.setAuthor(book.getAuthor());
		obj.setLaunchDate(book.getLaunchDate());
		obj.setPrice(book.getPrice());
		obj.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(bookRepository.save(obj), BookVo.class);
		return vo;
	}
	
	/*-------------DELETE-------------*/
	public void delete(Long id) {
		logger.info("deleting one book!");

		var obj = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		bookRepository.delete(obj);
	}
	
	
}
