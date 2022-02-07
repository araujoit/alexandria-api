package br.com.araujoit.alexandria;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.araujoit.alexandria.entities.Author;
import br.com.araujoit.alexandria.entities.Book;
import br.com.araujoit.alexandria.entities.Publisher;
import br.com.araujoit.alexandria.repositories.AuthorRepository;
import br.com.araujoit.alexandria.repositories.BookRepository;
import br.com.araujoit.alexandria.repositories.PublisherRepository;

@SpringBootApplication
public class AlexandriaApiApplication implements CommandLineRunner {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	public static void main(String[] args) {
		SpringApplication.run(AlexandriaApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Publisher publisher1 = new Publisher(null, "Saraiva");
		Publisher publisher2 = new Publisher(null, "Cultura");

		publisherRepository.saveAll(Arrays.asList(publisher1, publisher2));

		Author author1 = new Author(null, "João Pessoa");
		Author author2 = new Author(null, "Paulo Coelho");
		author1.setPublishers(Arrays.asList(publisher1, publisher2));

		List<Author> authors = authorRepository.saveAll(Arrays.asList(author1, author2));

		Book book = new Book(null, "Livro I");
		book.setAuthors(authors);
		bookRepository.save(book);
	}
}
