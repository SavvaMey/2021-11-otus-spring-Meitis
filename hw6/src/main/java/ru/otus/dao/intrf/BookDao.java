package ru.otus.dao.intrf;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();
    Book getBookById(long id);
    void deleteBookById(long id);
    long updateBook(Book book);
    Book saveBook(Book book);
}
