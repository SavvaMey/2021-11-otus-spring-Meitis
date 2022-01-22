package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.intrf.BookDao;
import ru.otus.domain.Book;
import ru.otus.exceptions.ObjectNotFound;
import ru.otus.service.intrf.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDaoImpl;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookDaoImpl.getAllBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(long id) {
       Book book;
       try {
           book = bookDaoImpl.getBookById(id);
       } catch (DataAccessException e) {
           throw new ObjectNotFound("книги с таким id нет");
       }
        return book;
    }

    @Override
    @Transactional
    public long deleteBookById(long id) {
        long idBook =  bookDaoImpl.deleteBookById(id);
        if (idBook == 0) {
            throw new ObjectNotFound("книги с таким id нет");
        }
        return idBook;
    }

    @Override
    @Transactional
    public long updateBook(long id, String title, long authorId, long genreId) {
        Book book = new Book(id, title, authorId, genreId);
        long idBook;
        try {
            idBook = bookDaoImpl.updateBook(book);
        } catch (DataAccessException e) {
            throw new ObjectNotFound("неверные ссылки на автора и жанр");
        }
        if (idBook == 0) {
            throw new ObjectNotFound("книги с таким id нет");
        }
        return idBook;
    }

    @Override
    @Transactional
    public Book insertBook(String title, long authorId, long genreId) {
        Book book = new Book(title, authorId, genreId);
        try {
            book =  bookDaoImpl.insertBook(book);
        } catch (DataAccessException e) {
            throw new ObjectNotFound("неверные ссылки на автора и жанр");
        }
        return book;
    }
}
