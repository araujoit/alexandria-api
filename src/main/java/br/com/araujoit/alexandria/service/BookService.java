package br.com.araujoit.alexandria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.araujoit.alexandria.dto.BookDto;
import br.com.araujoit.alexandria.entities.Author;
import br.com.araujoit.alexandria.entities.Book;
import br.com.araujoit.alexandria.repositories.AuthorRepository;
import br.com.araujoit.alexandria.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	public Page<Book> findAll(Pageable pageable) {
		Page<Book> books = bookRepository.findAll(pageable);

		//return books.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
		return books;
	}

	public Optional<BookDto> save(BookDto bookDto) {
		List<Author> foundAuthors = authorRepository.findAllById(bookDto.getAuthors().stream().map(a -> a.getId()).toList());
		if(bookDto.getAuthors().size() > foundAuthors.size()) {
			// nem todos os autores parametrizados foram encontrados, não faz nada
			return Optional.empty();
		}
		
		Book savedBook = bookRepository.save(convertToEntity(bookDto));
		return Optional.of(new BookDto(savedBook));
	}

	private Book convertToEntity(BookDto bookDto) {
		Book book = new Book(bookDto.getId(), bookDto.getTitle());
		List<Author> authors = bookDto.getAuthors().stream().map(author -> {
			return new Author(author.getId(), author.getName());
		}).collect(Collectors.toList());
		book.setAuthors(authors);
		return book;
	}

	public BookDto deleteById(HttpServletResponse res, Long id) {
		Optional<Book> foundBook = bookRepository.findById(id);
		if (!foundBook.isPresent()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Book book = foundBook.get();
		return new BookDto(book);
	}
}
