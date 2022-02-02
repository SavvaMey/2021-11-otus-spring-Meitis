package ru.otus.hw8.service.intrf;

import ru.otus.hw8.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(String id);

    Book getBookByName(String name);

    void deleteBookById(String id);

    Book updateBook(String id, String title);

    Book saveBook(String title, String authorId, String genreId);
}
