package ru.otus.hw7.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw7.domain.Author;
import ru.otus.hw7.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager em;


    @Test
    void findAll() {
        var books = bookDao.findAll();
        assertThat(books).
                hasSize(3)
                .allMatch(s -> !s.getAuthor().getFirstName().equals(""))
                .allMatch(s -> !s.getGenre().getNameGenre().equals(""));
    }

    @Test
    void findById() {
        var book = em.find(Book.class, 1L);
        var book1 = bookDao.findById(1L);
        assertThat(book1.get()).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(book);
        assertThat(book1.get().getCommentList())
                .hasSize(5);
    }
}