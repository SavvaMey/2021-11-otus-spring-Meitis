package ru.otus.service.intrf;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(long id);
    void deleteBookById(long id);
    long updateBook(long id, String title, long authorId, long genreId);
    Book saveBook(String title, long authorId, long genreId);
}
