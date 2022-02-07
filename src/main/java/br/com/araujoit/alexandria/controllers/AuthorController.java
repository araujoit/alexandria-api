package br.com.araujoit.alexandria.controllers;

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

import br.com.araujoit.alexandria.dto.AuthorDto;
import br.com.araujoit.alexandria.entities.Author;
import br.com.araujoit.alexandria.service.AuthorService;

@RestController
@CrossOrigin
@RequestMapping(value = "/authors")
public class AuthorController {

	@Autowired
	private AuthorService service;

	@GetMapping
	public ResponseEntity<Page<Author>> findAll(Pageable pageable) {
		Page<Author> authores = service.findAll(pageable);
		return ResponseEntity.ok(authores);
	}

	@PostMapping
	public ResponseEntity<AuthorDto> persist(@RequestBody AuthorDto author) {
		AuthorDto persisted = service.save(author);
		return ResponseEntity.ok(persisted);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Author> delete(HttpServletResponse res, @PathVariable Long id) {
		Author author = service.delete(res, id);
		return ResponseEntity.ok(author);
	}
}
