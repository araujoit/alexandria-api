package br.com.araujoit.alexandria.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.araujoit.alexandria.dto.BookDto;
import br.com.araujoit.alexandria.entities.Book;
import br.com.araujoit.alexandria.service.BookService;

@RestController
@CrossOrigin
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping
	public ResponseEntity<Page<Book>> findAll(Pageable pageable) {
		Page<Book> books = service.findAll(pageable);
		return ResponseEntity.ok(books);
	}

	@PostMapping
	public ResponseEntity<BookDto> persist(HttpServletResponse res, @RequestBody BookDto book) {
		Optional<BookDto> savedBook = service.save(book);
		if (!savedBook.isPresent()) {
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			// TODO: ver como enviar mensagem de retorno
			return null;
		}

		return ResponseEntity.ok(savedBook.get());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<BookDto> deleteById(HttpServletResponse res, @PathVariable Long id) {
		return ResponseEntity.ok(service.deleteById(res, id));
	}
}