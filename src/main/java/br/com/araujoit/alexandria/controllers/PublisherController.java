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

import br.com.araujoit.alexandria.dto.PublisherDto;
import br.com.araujoit.alexandria.entities.Publisher;
import br.com.araujoit.alexandria.service.PublisherService;

@RestController
@CrossOrigin
@RequestMapping(value = "/publishers")
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	@GetMapping
	public ResponseEntity<Page<Publisher>> findAll(Pageable pageable) {
		Page<Publisher> publishers = publisherService.findAll(pageable);
		return ResponseEntity.ok(publishers);
	}

	@PostMapping
	public ResponseEntity<PublisherDto> save(@RequestBody PublisherDto publisher) {
		PublisherDto savedPublisher = publisherService.save(publisher);
		return ResponseEntity.ok(savedPublisher);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PublisherDto> deleteById(HttpServletResponse res, @PathVariable Long id) {
		Optional<PublisherDto> foundPublisher = publisherService.findById(id);
		if (!foundPublisher.isPresent()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		PublisherDto publisher = foundPublisher.get();
		return ResponseEntity.ok(publisher);
	}
}
