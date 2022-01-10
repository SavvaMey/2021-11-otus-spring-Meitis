package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(long id);
    long deleteAuthorById(long id);
    long updateAuthor(long id, String firstName, String lastName);
    long insertAuthor(String firstName, String lastName);
}
