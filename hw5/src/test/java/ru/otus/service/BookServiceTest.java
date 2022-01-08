package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.dao.BookDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exceptions.ConstraintException;
import ru.otus.exceptions.ObjectNotFound;
import ru.otus.service.BookService;
import ru.otus.utils.BookMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BookService.class)
@DisplayName("Тесты на сервис с книгами")
public class BookServiceTest {

    private final Book book1 = new Book(1, "Cracking the Coding Interview",
            new Author(1, "Gayle", "Laakmann McDowell"),
            new Genre(1, "Textbook"));

    private final Book book2 = new Book(2, "who kill bob",
            new Author(2, "Ivan", "Ivanov"),
            new Genre(2, "Detective"));

    private final  Book book3 = new Book(3, "bad dreams",
            new Author(3, "Alex", "Kodov"),
            new Genre(3, "Horror"));

    @MockBean
    BookDao bookDao;

    @Autowired
    BookService bookService;


    @DisplayName("Возвращает книгу по id (исключение)")
    @Test
    void getBookByIdThenThrow() {
        long id = 4;
        when(bookDao.getBookById(id)).thenThrow(new ObjectNotFound("книги с таким id нет"));
        assertThatThrownBy(() -> bookService.getBookById(id))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("книги с таким id нет");
    }

    @DisplayName("Возвращает книгу по id (исключение)")
    @Test
    void getBookById() {
        long id = 1;
        when(bookDao.getBookById(id)).thenReturn(book1);
        assertThat(bookService.getBookById(1)).usingRecursiveComparison().isEqualTo(book1);
        verify(bookDao, times(1)).getBookById(1);
    }

    @DisplayName("Возвращает все книги")
    @Test
    void getAllBooks() {
        List<Book> books = List.of(book1, book2, book3);
        when(bookDao.getAllBooks()).thenReturn(books);
        assertThat(bookService.getAllBooks()).containsExactlyInAnyOrder(book1, book2, book3);
        verify(bookDao, times(1)).getAllBooks();
    }

    @DisplayName("Удаление книги корректно")
    @Test
    void deleteBookById() {
        when(bookDao.deleteBookById(1)).thenReturn(1L);
        assertThat(bookService.deleteBookById(1)).isEqualTo(1);
        verify(bookDao, times(1)).deleteBookById(1);
    }

    @DisplayName("Удаление книги некорректное")
    @Test
    void deleteBookByIdThenThrow() {
        when(bookDao.deleteBookById(1)).thenReturn(0L);
        assertThatThrownBy(() -> bookService.deleteBookById(1))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("книги с таким id нет");
        verify(bookDao, times(1)).deleteBookById(1);
    }

    @DisplayName("обновление книги с неправильными ссылками на автора и/или жанр")
    @Test
    void updateBookByIdThenThrowConstraintException() {
        when(bookDao.updateBook(1, "hey", 100, 100))
                .thenThrow(new DataRetrievalFailureException("example"));
        assertThatThrownBy(() -> bookService.updateBook(1, "hey", 100, 100))
                .isInstanceOf(ConstraintException.class)
                .hasMessage("неверные ссылки на автора и жанр");
        verify(bookDao, times(1)).updateBook(1, "hey", 100, 100);
    }

    @DisplayName("обновление книги с несуществующей книгой")
    @Test
    void updateBookByIdThenThrowObjectNotFound() {
        when(bookDao.updateBook(1, "hey", 100, 100))
                .thenReturn(0L);
        assertThatThrownBy(() -> bookService.updateBook(1, "hey", 100, 100))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("книги с таким id нет");
        verify(bookDao, times(1)).updateBook(1, "hey", 100, 100);
    }

    @DisplayName("вставка книги корректно")
    @Test
    void insertBook() {
        when(bookDao.insertBook( "hey", 100, 100))
                .thenReturn(1L);
        assertThat(bookService.insertBook("hey", 100, 100)).isEqualTo(1);
        verify(bookDao, times(1)).insertBook("hey", 100, 100);
    }

    @DisplayName("вставка книги некорректно")
    @Test
    void insertBookThenThrowConstraintException() {
        when(bookDao.insertBook( "hey", 100, 100))
                .thenThrow(new DataRetrievalFailureException("example"));
        assertThatThrownBy(() -> bookService.insertBook("hey", 100, 100))
                .isInstanceOf(ConstraintException.class)
                .hasMessage("неверные ссылки на автора и жанр");
        verify(bookDao, times(1)).insertBook("hey", 100, 100);
    }


}
