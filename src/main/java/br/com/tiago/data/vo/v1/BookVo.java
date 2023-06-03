package br.com.tiago.data.vo.v1;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class BookVo extends RepresentationModel <BookVo> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String author;	
	private Date launchDate;
	private Double price;
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}