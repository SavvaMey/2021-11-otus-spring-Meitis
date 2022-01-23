package ru.otus.hw7.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw7.domain.Author;
import ru.otus.hw7.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GenreDaoTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private GenreDao genreDao;

    @Test
    void updateGenreById() {
        var author = new Genre(1L, "New");
        int id = genreDao.updateGenreById("New", 1L);
        var genreNew = em.find(Genre.class, 1L);
        assertThat(1L).isEqualTo(1L);
        assertThat(genreNew).isEqualTo(author);

    }
}