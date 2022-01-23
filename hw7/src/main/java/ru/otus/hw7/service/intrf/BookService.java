package ru.otus.hw7.service.intrf;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(long id);

    void deleteBookById(long id);

    @Transactional
    Book updateBook(long id, String title, Long authorId, Long genreId);

    @Transactional
    Book insertBook(String title, Long authorId, Long genreId);
}
