package br.com.araujoit.alexandria.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.araujoit.alexandria.entities.Book;

public class BookDto {
	private Long id;
	private String title;

	private List<AuthorDto> authors = new ArrayList<>();

	public BookDto() {
		super();
	}

	public BookDto(Book book) {
		super();
		this.id = book.getId();
		this.title = book.getTitle();
		this.authors = book.getAuthors().stream().map(author -> new AuthorDto(author)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}
}
