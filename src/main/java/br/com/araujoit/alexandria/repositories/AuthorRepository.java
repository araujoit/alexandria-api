package br.com.araujoit.alexandria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.araujoit.alexandria.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
