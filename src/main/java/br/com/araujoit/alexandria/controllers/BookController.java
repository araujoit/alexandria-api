package br.com.araujoit.alexandria.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.araujoit.alexandria.entities.Book;
import br.com.araujoit.alexandria.repositories.BookRepository;

@RestController
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping
	public ResponseEntity<List<Book>> findAll() {
		List<Book> books = bookRepository.findAll();
		return ResponseEntity.ok(books);
	}

	@PostMapping
	public ResponseEntity<Book> persist(@RequestBody Book book) {
		Book savedBook = bookRepository.save(book);
		return ResponseEntity.ok(savedBook);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Book> deleteById(HttpServletResponse res, @PathVariable Long id) {
		Optional<Book> foundBook = bookRepository.findById(id);
		if (!foundBook.isPresent()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Book book = foundBook.get();
		return ResponseEntity.ok(book);
	}

}
