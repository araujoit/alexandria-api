package br.com.araujoit.alexandria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.araujoit.alexandria.dto.AuthorDto;
import br.com.araujoit.alexandria.entities.Author;
import br.com.araujoit.alexandria.entities.Publisher;
import br.com.araujoit.alexandria.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public Page<Author> findAll(Pageable pageable) {
		Page<Author> authores = authorRepository.findAll(pageable);
		//authorRepository.findAuthorsBooks(authores);
		//authorRepository.findAuthorsPublishers(authores);
		//return authores.stream().map(author -> new AuthorDto(author)).collect(Collectors.toList());
		return authores;
	}

	public AuthorDto save(AuthorDto authorDto) {
		Author author = authorRepository.save(convertToEntity(authorDto));
		return new AuthorDto(author);
	}

	private Author convertToEntity(AuthorDto authorDto) {
		Author author = new Author();
		author.setId(authorDto.getId());
		author.setName(authorDto.getName());

		List<Publisher> publishers = authorDto.getPublishers().stream().map(publisher -> {
			return new Publisher(publisher.getId(), publisher.getCorporateName());
		}).collect(Collectors.toList());

		author.setPublishers(publishers);
		return author;
	}

	public Author delete(HttpServletResponse res, Long id) {
		Optional<Author> authorOpt = authorRepository.findById(id);
		if (!authorOpt.isPresent()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Author author = authorOpt.get();
		authorRepository.delete(author);
		return author;
	}
}
