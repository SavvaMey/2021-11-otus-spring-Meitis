package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getAuthorById(long id);
    List<Author> getAllAuthors();
    long deleteAuthorById(long id);
    long updateAuthor(long id, String firstName,  String lastName);
    long insertAuthor(String firstName, String lastName);
}
