package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataRetrievalFailureException;
import ru.otus.dao.BookDao;
import ru.otus.dao.BookDaoImpl;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exceptions.ConstraintException;
import ru.otus.exceptions.ObjectNotFound;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookServiceImpl.class,BookDaoImpl.class})
@DisplayName("Тесты на сервис с книгами")
public class BookServiceImplTest {

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
    BookDao bookDaoImpl;

    @Autowired
    BookService bookServiceImpl;


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
        when(bookDaoImpl.updateBook(1, "hey", 100, 100))
                .thenThrow(new DataRetrievalFailureException("example"));
        assertThatThrownBy(() -> bookServiceImpl.updateBook(1, "hey", 100, 100))
                .isInstanceOf(ConstraintException.class)
                .hasMessage("неверные ссылки на автора и жанр");
        verify(bookDaoImpl, times(1)).updateBook(1, "hey", 100, 100);
    }

    @DisplayName("обновление книги с несуществующей книгой")
    @Test
    void updateBookByIdThenThrowObjectNotFound() {
        when(bookDaoImpl.updateBook(1, "hey", 100, 100))
                .thenReturn(0L);
        assertThatThrownBy(() -> bookServiceImpl.updateBook(1, "hey", 100, 100))
                .isInstanceOf(ObjectNotFound.class)
                .hasMessage("книги с таким id нет");
        verify(bookDaoImpl, times(1)).updateBook(1, "hey", 100, 100);
    }

    @DisplayName("вставка книги корректно")
    @Test
    void insertBook() {
        when(bookDaoImpl.insertBook( "hey", 100, 100))
                .thenReturn(1L);
        assertThat(bookServiceImpl.insertBook("hey", 100, 100)).isEqualTo(1);
        verify(bookDaoImpl, times(1)).insertBook("hey", 100, 100);
    }

    @DisplayName("вставка книги некорректно")
    @Test
    void insertBookThenThrowConstraintException() {
        when(bookDaoImpl.insertBook( "hey", 100, 100))
                .thenThrow(new DataRetrievalFailureException("example"));
        assertThatThrownBy(() -> bookServiceImpl.insertBook("hey", 100, 100))
                .isInstanceOf(ConstraintException.class)
                .hasMessage("неверные ссылки на автора и жанр");
        verify(bookDaoImpl, times(1)).insertBook("hey", 100, 100);
    }


}
