package br.com.araujoit.alexandria.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.araujoit.alexandria.entities.Publisher;

public class PublisherDto {

	private Long id;

	private String corporateName;

	private List<AuthorDto> authors = new ArrayList<>();

	public PublisherDto() {
		super();
	}

	public PublisherDto(Publisher publisher) {
		super();
		this.id = publisher.getId();
		this.corporateName = publisher.getCorporateName();
		this.authors = publisher.getAuthors().stream().map(author -> new AuthorDto(author))
				.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	
	public List<AuthorDto> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}
}
