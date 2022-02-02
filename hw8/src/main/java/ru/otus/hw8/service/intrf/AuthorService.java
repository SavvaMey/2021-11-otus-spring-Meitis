package ru.otus.hw8.service.intrf;

import ru.otus.hw8.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(String id);

    Author getAuthorByFirstNameAndLastName(String firstName, String lastName);

    void deleteAuthorById(String id);

    Author saveOrUpdateAuthor(String id, String firstName, String lastName);
}
