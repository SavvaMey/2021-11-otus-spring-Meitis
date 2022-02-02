package ru.otus.hw8.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw8.dao.intrf.BookRepository;
import ru.otus.hw8.dao.intrf.CommentRepository;
import ru.otus.hw8.domain.Author;
import ru.otus.hw8.domain.Book;
import ru.otus.hw8.domain.Genre;
import ru.otus.hw8.service.intrf.AuthorService;
import ru.otus.hw8.service.intrf.BookService;
import ru.otus.hw8.service.intrf.GenreService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookServiceImpl.class} )
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private GenreService genreService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private CommentRepository commentRepository;

    private Book book = new Book("example", "title", null, null);

    @Test
    void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(
                List.of(new Book(), new Book(), new Book()));
        assertThat(bookService.getAllBooks()).hasSize(3);
    }

    @Test
    void getBookById() {
        when(bookRepository.findById("example")).thenReturn(Optional.of(book));
        assertThat(bookService.getBookById("example")).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(book);
    }

    @Test
    void getBookByName() {
        when(bookRepository.findBookByTitle("title")).thenReturn(Optional.of(book));
        assertThat(bookService.getBookByName("title")).usingRecursiveComparison()
                .isEqualTo(book);
    }

    @Test
    void deleteBookById() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        bookService.deleteBookById(book.getId());
        verify(bookRepository, times(1)).delete(book);
        verify(commentRepository,times(1)).deleteAllCommentsByBookId(book.getId());
    }

    @Test
    void updateBook() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        bookService.updateBook(book.getId(), book.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void saveBook() {
        var author = new Author();
        var genre = new Genre();
        when(authorService.getAuthorById("authorId")).thenReturn(author);
        when(genreService.getGenreById("genreId")).thenReturn(genre);
        verify(bookRepository, times(1)).save(any());
    }
}