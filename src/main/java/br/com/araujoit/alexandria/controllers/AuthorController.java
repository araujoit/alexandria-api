package br.com.araujoit.alexandria.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.araujoit.alexandria.entities.Author;
import br.com.araujoit.alexandria.repositories.AuthorRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/authors")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping
	public ResponseEntity<List<Author>> findAll() {
		List<Author> authores = authorRepository.findAll();
		return ResponseEntity.ok(authores);
	}

	@PostMapping
	public ResponseEntity<Author> persist(@RequestBody Author author) {
		Author persisted = authorRepository.save(author);
		return ResponseEntity.ok(persisted);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Author> delete(HttpServletResponse res, @PathVariable Long id) {
		Optional<Author> authorOpt = authorRepository.findById(id);
		if (!authorOpt.isPresent()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Author author = authorOpt.get();
		authorRepository.delete(author);
		return ResponseEntity.ok(author);
	}
}
