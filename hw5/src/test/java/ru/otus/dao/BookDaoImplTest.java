package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.utils.BookMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("dao books")
@JdbcTest
@Import({BookDaoImpl.class, BookMapper.class})
class BookDaoImplTest {
    private final  Book book1 = new Book(1, "Cracking the Coding Interview",
            new Author(1, "Gayle", "Laakmann McDowell"),
            new Genre(1, "Textbook"));

    private final Book book2 = new Book(2, "who kill bob",
            new Author(2, "Ivan", "Ivanov"),
            new Genre(2, "Detective"));

    private final  Book book3 = new Book(3, "bad dreams",
            new Author(3, "Alex", "Kodov"),
            new Genre(3, "Horror"));

    @Autowired
    BookDao bookDaoImpl;

    @Test
    @DisplayName("возвращает все книги")
    void getAllBooks() {
        List<Book> books = bookDaoImpl.getAllBooks();
        assertThat(books)
                .containsExactlyInAnyOrder(book1, book2, book3);
    }

    @Test
    @DisplayName("возвращает книгу по id")
    void getBookById() {
        Book book = bookDaoImpl.getBookById(1);
        assertThat(book).usingRecursiveComparison().isEqualTo(book1);
    }

    @Test
    @DisplayName("удаляет книгу")
    void deleteBookById() {
        long id = bookDaoImpl.deleteBookById(1);
        assertThat(id).isEqualTo(1);
        assertThat(bookDaoImpl.getAllBooks())
                .containsExactlyInAnyOrder(book2, book3);
    }

    @Test
    @DisplayName("меняет название книги")
    void updateBook() {
        Book book1New = new Book(1, "Interview",
                new Author(1, "Gayle", "Laakmann McDowell"),
                new Genre(1, "Textbook"));
        long id = bookDaoImpl.updateBook(1, "Interview", 1, 1);
        assertThat(id).isEqualTo(1);
        assertThat(bookDaoImpl.getBookById(1)).usingRecursiveComparison().isEqualTo(book1New);
    }

    @Test
    void insertBook() {
        assertThat(bookDaoImpl.insertBook("GG", 1, 1))
                .isEqualTo(4);
    }
}