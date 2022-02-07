package br.com.araujoit.alexandria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.araujoit.alexandria.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	// JPQL
	@Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a IN :authors")
	List<Author> findAuthorsBooks(List<Author> authors);
	
	@Query("SELECT a FROM Author a JOIN FETCH a.publishers WHERE a IN :authors")
	List<Author> findAuthorsPublishers(List<Author> authors);
}
