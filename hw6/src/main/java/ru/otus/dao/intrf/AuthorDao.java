package ru.otus.dao.intrf;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getAuthorById(long id);
    List<Author> getAllAuthors();
    long deleteAuthorById(long id);
    long updateAuthor(Author author);
    Author saveAuthor(Author author);
}
