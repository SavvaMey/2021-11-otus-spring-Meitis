package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataRetrievalFailureException;
import ru.otus.dao.BookDaoImpl;
import ru.otus.dao.intrf.BookDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.exceptions.ObjectNotFound;
import ru.otus.service.intrf.BookService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = {BookServiceImpl.class, BookDaoImpl.class})
@DisplayName("Тесты на сервис с книгами")
class BookServiceImplTest {

    private final Book book1 = new Book(1, "Cracking the Coding Interview",
            new Author(1L, "Gayle", "Laakmann McDowell"),
            new Genre(1L, "Textbook"),
            List.of(new Comment(1L, "good book"),new Comment(2L, "wow")));

    private final Book book2 = new Book(2, "who kill bob",
            new Author(2L, "Ivan", "Ivanov"),
            new Genre(2L, "Detective"),
            List.of(new Comment(2L, "bad"),new Comment(3L, "worst")));

    private final  Book book3 = new Book(3, "bad dreams",
            new Author(3L, "Alex", "Kodov"),
            new Genre(3L, "Horror"),
            List.of(new Comment(4L, "hello"),new Comment(5L, "nice")));

    private final Book bookFail = new Book(1L, "hey", new Author(100L, null, null),
            new Genre(100L, null),
            null);
    @MockBean
    private BookDao bookDaoImpl;

    @Autowired
    private BookService bookServiceImpl;

    @DisplayName("Возвращает книгу по id (исключение)")
    @Test
    void getBookByIdThenThrow() {
        long id = 4;
        when(bookDaoImpl.getBookById(id)).thenThrow(new ObjectNotFound("книги с таким id нет"));
        assertThatThrownBy(() -> bookServiceImpl.getBookById(id))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("книги с таким id нет");
    }

    @DisplayName("Возвращает книгу по id (исключение)")
    @Test
    void getBookById() {
        long id = 1;
        when(bookDaoImpl.getBookById(id)).thenReturn(book1);
        assertThat(bookServiceImpl.getBookById(1)).usingRecursiveComparison().isEqualTo(book1);
        verify(bookDaoImpl, times(1)).getBookById(1);
    }

    @DisplayName("Возвращает все книги")
    @Test
    void getAllBooks() {
        List<Book> books = List.of(book1, book2, book3);
        when(bookDaoImpl.getAllBooks()).thenReturn(books);
        assertThat(bookServiceImpl.getAllBooks()).containsExactlyInAnyOrder(book1, book2, book3);
        verify(bookDaoImpl, times(1)).getAllBooks();
    }

    @DisplayName("Удаление книги корректно")
    @Test
    void deleteBookById() {
        when(bookDaoImpl.deleteBookById(1)).thenReturn(1L);
        assertThat(bookServiceImpl.deleteBookById(1)).isEqualTo(1);
        verify(bookDaoImpl, times(1)).deleteBookById(1);
    }

    @DisplayName("Удаление книги некорректное")
    @Test
    void deleteBookByIdThenThrow() {
        when(bookDaoImpl.deleteBookById(1)).thenReturn(0L);
        assertThatThrownBy(() -> bookServiceImpl.deleteBookById(1))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("книги с таким id нет");
        verify(bookDaoImpl, times(1)).deleteBookById(1);
    }

    @DisplayName("обновление книги с неправильными ссылками на автора и/или жанр")
    @Test
    void updateBookByIdThenThrowConstraintException() {

        when(bookDaoImpl.updateBook(bookFail))
                .thenThrow(new DataRetrievalFailureException("example"));
        assertThatThrownBy(() -> bookServiceImpl.updateBook(1, "hey", 100, 100))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("неверные ссылки на автора и жанр");
        verify(bookDaoImpl, times(1)).updateBook(bookFail);
    }

    @DisplayName("обновление книги с несуществующей книгой")
    @Test
    void updateBookByIdThenThrowObjectNotFound() {
        when(bookDaoImpl.updateBook(bookFail))
                .thenReturn(0L);
        assertThatThrownBy(() -> bookServiceImpl.updateBook(1, "hey", 100, 100))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("книги с таким id нет");
        verify(bookDaoImpl, times(1)).updateBook(bookFail);
    }

    @DisplayName("вставка книги корректно")
    @Test
    void insertBook() {
        Book bookOut = new Book(1L, "hello", 1L, 1L);
        Book bookIn = new Book(null, "hello", 1L, 1L);
        when(bookDaoImpl.insertBook(bookIn))
                .thenReturn(bookOut);
        assertThat(bookServiceImpl.insertBook("hello", 1L, 1L)).isEqualTo(bookOut);
        verify(bookDaoImpl, times(1)).insertBook(bookIn);
    }
}