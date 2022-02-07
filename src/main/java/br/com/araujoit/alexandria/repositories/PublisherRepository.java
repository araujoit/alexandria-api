package br.com.araujoit.alexandria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.araujoit.alexandria.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
