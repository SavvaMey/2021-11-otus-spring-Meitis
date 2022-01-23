package ru.otus.hw7.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7.dao.AuthorDao;
import ru.otus.hw7.dao.BookDao;
import ru.otus.hw7.dao.GenreDao;
import ru.otus.hw7.domain.Author;
import ru.otus.hw7.domain.Book;
import ru.otus.hw7.domain.Genre;
import ru.otus.hw7.exception.ObjectNotFound;
import ru.otus.hw7.service.intrf.BookService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(long id) {
        Optional<Book> comment = bookDao.findById(id);
        return comment.orElseThrow(() -> new ObjectNotFound("элемент не найден"));
    }

    @Transactional
    @Override
    public void deleteBookById(long id) {
        try {
            bookDao.deleteById(id);
        } catch (DataAccessException ex) {
            throw new ObjectNotFound("не задан верный id");
        }
    }

    @Override
    @Transactional
    public Book updateBook(long id, String title, Long authorId, Long genreId) {

        try {
            Author author = authorDao.getById(authorId);
            Genre genre = genreDao.getById(genreId);
            Book book = new Book(id, title, author, genre);
            return bookDao.save(book);
        } catch (Exception e) {
            throw new ObjectNotFound("error update");
        }
    }

    @Override
    @Transactional
    public Book insertBook(String title, Long authorId, Long genreId) {
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);


        Book book = new Book(null, title, author, genre);
        return bookDao.save(book);
    }


}
