package ru.otus.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.dao.intrf.BookDao;
import ru.otus.domain.Book;
import ru.otus.domain.Book;

import java.lang.reflect.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookDaoImpl.class)
public class BookDaoImplTest {


    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void getAllBooksThen4() {
        assertThat(bookDao.getAllBooks())
                .hasSize(3)
                .allMatch(s -> !s.getAuthor().getFirstName().equals(""))
                .allMatch(s -> !s.getGenre().getNameGenre().equals(""));

    }

    @Test
    void getBookByIdThenFirstBook() {
        var book = em.find(Book.class, 1L);
        var book1 = bookDao.getBookById(1);
        assertThat(book1).usingRecursiveComparison().isEqualTo(book);
        assertThat(book1.getCommentList())
                .hasSize(2);
    }

    @Test
    void deleteBookById() {
        assertThat(bookDao.deleteBookById(3L))
                .isEqualTo(3);
        assertThat(bookDao.getAllBooks())
                .hasSize(2);
    }

    @Test
    void updateBook() {
        var bookNew =  new Book(1L, "Example", 1, 1);
        bookDao.updateBook(bookNew);
        em.flush();
        em.clear();
        var book = em.find(Book.class, 1L);
        assertThat(book.getTitle())
                .isEqualTo(bookNew.getTitle());
    }

    @Test
    void insertBook() {
        var bookNew =  new Book("Example", 1, 1);
        bookDao.saveBook(bookNew);
        var Book = em.find(Book.class, 4L);
        assertThat(Book)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(bookNew);
    }
}
