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

import br.com.araujoit.alexandria.entities.Publisher;
import br.com.araujoit.alexandria.repositories.PublisherRepository;

@RestController
@RequestMapping(value = "/publishers")
public class PublisherController {

	@Autowired
	private PublisherRepository publisherRepository;

	@GetMapping
	public ResponseEntity<List<Publisher>> findAll() {
		List<Publisher> publishers = publisherRepository.findAll();
		return ResponseEntity.ok(publishers);
	}

	@PostMapping
	public ResponseEntity<Publisher> persist(@RequestBody Publisher publisher) {
		Publisher savedPublisher = publisherRepository.save(publisher);
		return ResponseEntity.ok(savedPublisher);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Publisher> deleteById(HttpServletResponse res, @PathVariable Long id) {
		Optional<Publisher> foundPublisher = publisherRepository.findById(id);
		if (!foundPublisher.isPresent()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Publisher publisher = foundPublisher.get();
		return ResponseEntity.ok(publisher);
	}
}
