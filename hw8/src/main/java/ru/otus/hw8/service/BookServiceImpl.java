package ru.otus.hw8.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw8.dao.intrf.AuthorRepository;
import ru.otus.hw8.dao.intrf.BookRepository;
import ru.otus.hw8.dao.intrf.CommentRepository;
import ru.otus.hw8.dao.intrf.GenreRepository;
import ru.otus.hw8.domain.Book;
import ru.otus.hw8.exception.ObjectNotFound;
import ru.otus.hw8.service.intrf.AuthorService;
import ru.otus.hw8.service.intrf.BookService;
import ru.otus.hw8.service.intrf.GenreService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentRepository commentRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new ObjectNotFound("такой книги нет"));
    }

    @Override
    public Book getBookByName(String name) {
        return bookRepository.findBookByTitle(name).orElseThrow(() -> new ObjectNotFound("такой книги нет"));
    }

    @Override
    public void deleteBookById(String id) {
        var book = getBookById(id);
        commentRepository.deleteAllCommentsByBookId(id);
        bookRepository.delete(book);
    }

    @Override
    public Book updateBook(String id, String title) {
        var book = getBookById(id);
        book.setTitle(title);
        return bookRepository.save(book);
    }

    @Override
    public Book saveBook(String title, String authorId, String genreId) {
        var author = authorService.getAuthorById(authorId);
        var genre = genreService.getGenreById(genreId);
        var book = new Book(title, author, genre);
        return bookRepository.save(book);
    }
}
