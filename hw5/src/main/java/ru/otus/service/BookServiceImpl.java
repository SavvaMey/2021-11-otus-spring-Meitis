package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;
import ru.otus.exceptions.ConstraintException;
import ru.otus.exceptions.ObjectNotFound;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookDao bookDaoImpl;

    @Override
    public List<Book> getAllBooks() {
        return bookDaoImpl.getAllBooks();
    }

    @Override
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
    public long deleteBookById(long id) {
        long idBook =  bookDaoImpl.deleteBookById(id);
        if (idBook == 0) {
            throw new ObjectNotFound("книги с таким id нет");
        }
        return idBook;
    }

    @Override
    public long updateBook(long id, String title, long authorId, long genreId) {
        long idBook;
        try {
            idBook = bookDaoImpl.updateBook(id, title, authorId, genreId);
        } catch (DataAccessException e) {
            throw new ConstraintException("неверные ссылки на автора и жанр");
        }
        if (idBook == 0) {
            throw new ObjectNotFound("книги с таким id нет");
        }
        return idBook;
    }

    @Override
    public long insertBook(String title, long authorId, long genreId) {
        long idBook;
        try {
            idBook =  bookDaoImpl.insertBook(title, authorId, genreId);
        } catch (DataAccessException e) {
            throw new ConstraintException("неверные ссылки на автора и жанр");
        }
        return idBook;
    }
}
