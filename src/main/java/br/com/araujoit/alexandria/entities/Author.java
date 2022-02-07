package br.com.araujoit.alexandria.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "authors")
	private List<Book> books = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "authors")
	private List<Publisher> publishers = new ArrayList<>();

	public Author() {
		this(null, "");
	}
	
	public Author(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public List<Publisher> getPublishers() {
		return publishers;
	}
	
	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}
}
