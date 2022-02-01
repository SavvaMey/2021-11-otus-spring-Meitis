package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.dao.intrf.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void getAllAuthorsThen4() {
        List<Author> authors = authorDao.getAllAuthors();
        assertThat(authors)
                .hasSize(4);
    }

    @Test
    void getAuthorByIdThenFirstAuthor() {
        var author = em.find(Author.class, 1L);
        assertThat(authorDao.getAuthorById(1)).usingRecursiveComparison().isEqualTo(author);
    }

    @Test
    void deleteAuthorById() {
        authorDao.deleteAuthorById(4L);
        assertThat(authorDao.getAllAuthors())
                .hasSize(3);
    }

    @Test
    void updateAuthor() {
        var authorNew =  new Author(1L, "Gayle", "McDowell");
        authorDao.updateAuthor(authorNew);
        var author = em.find(Author.class, 1L);
        assertThat(author).usingRecursiveComparison().isEqualTo(authorNew);
    }

    @Test
    void insertAuthor() {
        var authorNew =  new Author( "new", "Author");
        authorDao.saveAuthor(authorNew);
        var author = em.find(Author.class, 5L);
        assertThat(author).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(authorNew);
    }
}