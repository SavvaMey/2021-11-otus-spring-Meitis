package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();
    Book getBookById(long id);
    long deleteBookById(long id);
    long updateBook(long id, String title, long authorId, long genreId);
    long insertBook(String title, long authorId, long genreId);
}
