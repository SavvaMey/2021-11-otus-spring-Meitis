package ru.otus.service.intrf;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(long id);
    long deleteAuthorById(long id);
    long updateAuthor(long id, String firstName, String lastName);
    Author saveAuthor(String firstName, String lastName);
}
