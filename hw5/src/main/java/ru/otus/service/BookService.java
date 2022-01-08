package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;
import ru.otus.exceptions.ConstraintException;
import ru.otus.exceptions.ObjectNotFound;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Book getBookById(long id) {
       Book book;
       try {
           book = bookDao.getBookById(id);
       } catch (DataAccessException e) {
           throw new ObjectNotFound("книги с таким id нет");
       }
        return book;
    }

    public long deleteBookById(long id) {
        long idBook =  bookDao.deleteBookById(id);
        if (idBook == 0) {
            throw new ObjectNotFound("книги с таким id нет");
        }
        return idBook;
    }

    public long updateBook(long id, String title, long authorId, long genreId) {
        long idBook;
        try {
            idBook = bookDao.updateBook(id, title, authorId, genreId);
        } catch (DataAccessException e) {
            throw new ConstraintException("неверные ссылки на автора и жанр");
        }
        if (idBook == 0) {
            throw new ObjectNotFound("книги с таким id нет");
        }
        return idBook;
    }

    public long insertBook(String title, long authorId, long genreId) {
        long idBook;
        try {
            idBook =  bookDao.insertBook(title, authorId, genreId);
        } catch (DataAccessException e) {
            throw new ConstraintException("неверные ссылки на автора и жанр");
        }
        return idBook;
    }
}
