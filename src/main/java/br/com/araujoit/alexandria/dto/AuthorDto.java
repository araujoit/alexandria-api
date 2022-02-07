package br.com.araujoit.alexandria.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.araujoit.alexandria.entities.Author;

public class AuthorDto {

	private Long id;

	private String name;

	private List<BookDto> books = new ArrayList<>();

	private List<PublisherDto> publishers = new ArrayList<>();

	public AuthorDto() {
		super();
	}

	public AuthorDto(Author author) {
		super();
		this.id = author.getId();
		this.name = author.getName();
		/*
		 * this.books = author.getBooks().stream().map(book -> new
		 * BookDto(book)).collect(Collectors.toList());
		 */
		/*
		 * this.publishers = author.getPublishers().stream().map(publisher -> new
		 * PublisherDto(publisher)) .collect(Collectors.toList());
		 */
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

	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}

	public List<PublisherDto> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<PublisherDto> publishers) {
		this.publishers = publishers;
	}

	@Override
	public String toString() {
		return "AuthorDto [id=" + id + ", name=" + name + ", publishers=" + publishers + "]";
	}
}
