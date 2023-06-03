package br.com.tiago.controllers;

import java.util.Collection;

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

import br.com.tiago.data.vo.v1.BookVo;
import br.com.tiago.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookServices bookServices;

	/*------------------METODO FINDBYID---------------*/
	@Operation(description = "Busca Book por ID")
	@ApiResponses(value = {
		@ApiResponse (responseCode = "200", description = "Retorna a Book" ),
		@ApiResponse (responseCode = "500", description = "Retorna execessão bookalizada")
	})
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, 
						MediaType.APPLICATION_XML_VALUE })
	public BookVo findById(@PathVariable(value = "id") Long id) throws Exception {
		BookVo BookVo = bookServices.findById(id);
		BookVo.add(WebMvcLinkBuilder.linkTo(
					 WebMvcLinkBuilder.methodOn(BookController.class).
					 findById(id)).withSelfRel());
		BookVo.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(BookController.class).
				findAll()).withRel("books"));
		return BookVo;
	}
	
	/*---------------METODO FINDALL--------------*/
	@Operation(description = "Busca Books")
	@ApiResponses(value = {
		@ApiResponse (responseCode = "200", description = "Retorna lista de Books" ),
		
	})
	@GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE, 
							MediaType.APPLICATION_XML_VALUE })
	public Collection <BookVo> findAll(){
		Collection <BookVo> listbook = bookServices.findAll();
			listbook.stream().
			forEach(p-> {
				try {
					p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).
							findById(p.getId())).withSelfRel());
					p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).
							findAll()).withRel("books"));
							
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
	
	
		return listbook;
	}

		
	/*----------CREATE--------*/
	@Operation(description = "Endpoint responsável por Criar um Objeto Book")
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public BookVo create(@RequestBody BookVo book) throws Exception {
		BookVo BookVo = bookServices.create(book);
		BookVo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).
				findById(BookVo.getId())).withSelfRel());
		return BookVo;
	}
	
	/*----------CREATE v2--------*/
	/*@Operation(description = "Endpoint responsável por Criar um Objeto BookV2")
	@PostMapping( value = "/v2", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public BookVov2 createV2(@RequestBody BookVov2 book) throws Exception {
		BookVov2 bookV2 = bookServices.createV2(book);
		bookV2.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).
				findById(bookV2.getId())).withSelfRel());
		return bookV2;
	}*/
	
	/*----------UPDATE--------*/
	@Operation(description = "Endpoint responsável por Atualizar um Objeto Book")
	@PutMapping ( 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public BookVo update (@RequestBody BookVo book) throws Exception {
		BookVo bookVo = bookServices.update(book);
		bookVo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).
				findById(bookVo.getId())).withSelfRel());
		return bookVo;
	}

	
	/*-----------DELETE-----------*/
	@Operation(description = "Endpoint responsável por Deletar um Objeto Book")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
		bookServices.delete(id);
		return ResponseEntity.noContent().build();
		/*noContent irá retornar statuscod 204*/
	}
	
	
}
