package ru.otus.hw7.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw7.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@Import(AuthorDao.class)
class AuthorDaoTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TestEntityManager em;


    @Test
    void updateAuthorById() {
        var author = new Author(1L, "New", "New");
        int id = authorDao.updateAuthorById("New", "New", 1L);
        var authorNew = em.find(Author.class, 1L);
        assertThat(1L).isEqualTo(1L);
        assertThat(authorNew).isEqualTo(author);
    }
}