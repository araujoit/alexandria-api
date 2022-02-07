package br.com.araujoit.alexandria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.araujoit.alexandria.entities.Author;
import br.com.araujoit.alexandria.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	// JPQL
	@Query("SELECT b FROM Book b JOIN FETCH b.authors WHERE b IN :books")
	List<Author> findBooksAuthor(List<Book> books);
}
