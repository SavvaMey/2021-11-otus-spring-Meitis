package ru.otus.hw8.dao.intrf;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw8.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author,String> {

    Optional<Author> findByFirstNameAndAndLastName(String firstName, String lastName);
}
