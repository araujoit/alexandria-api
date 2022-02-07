package br.com.araujoit.alexandria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.araujoit.alexandria.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
