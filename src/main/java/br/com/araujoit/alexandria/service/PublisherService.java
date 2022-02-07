package br.com.araujoit.alexandria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.araujoit.alexandria.dto.PublisherDto;
import br.com.araujoit.alexandria.entities.Author;
import br.com.araujoit.alexandria.entities.Book;
import br.com.araujoit.alexandria.entities.Publisher;
import br.com.araujoit.alexandria.repositories.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	public Page<Publisher> findAll(Pageable pageable) {
		Page<Publisher> publishers = publisherRepository.findAll(pageable);
		// return publishers.stream().map(publisher -> new
		// PublisherDto(publisher)).collect(Collectors.toList());
		return publishers;
	}

	public PublisherDto save(PublisherDto publisher) {
		Publisher savedPublisher = publisherRepository.save(convertToEntity(publisher));
		return new PublisherDto(savedPublisher);
	}

	private Publisher convertToEntity(PublisherDto publisherDto) {
		// TODO Auto-generated method stub
		Publisher publisher = new Publisher(publisherDto.getId(), publisherDto.getCorporateName());
		publisher.setAuthors(publisherDto.getAuthors().stream().map(authorDto -> {
			Author author = new Author(authorDto.getId(), authorDto.getName());
			List<Book> bookList = authorDto.getBooks().stream().map(bookDto -> {
				Book book = new Book();
				book.setId(bookDto.getId());
				book.setTitle(bookDto.getTitle());
				return book;
			}).toList();
			author.setBooks(bookList);
			return author;
		}).collect(Collectors.toList()));
		return publisher;
	}

	public Optional<PublisherDto> findById(Long id) {
		Optional<Publisher> foundPublisher = publisherRepository.findById(id);
		if (!foundPublisher.isPresent()) {
			return Optional.empty();
		}

		return Optional.of(new PublisherDto(foundPublisher.get()));
	}
}
