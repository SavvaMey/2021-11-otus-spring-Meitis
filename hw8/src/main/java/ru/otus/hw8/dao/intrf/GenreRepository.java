package ru.otus.hw8.dao.intrf;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw8.domain.Author;
import ru.otus.hw8.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findByNameGenre(String name);
}
