package ru.otus.hw7.service.intrf;


import ru.otus.hw7.domain.Author;

import java.util.List;


public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(long id);

    void deleteAuthorById(long id);

    long updateAuthor(long id, String firstName, String lastName);

    Author insertAuthor(String firstName, String lastName);
}
