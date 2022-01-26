package ru.otus.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.dao.intrf.GenreDao;

import ru.otus.domain.Genre;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(GenreDaoImpl.class)
public class GenreDaoImplTest {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void getAllGenresThen4() {
        List<Genre> genres = genreDao.getAllGenres();
        assertThat(genres)
                .hasSize(4);
    }

    @Test
    void getGenreByIdThenFirstGenre() {
        var genre = em.find(Genre.class, 1L);
        assertThat(genreDao.getGenreById(1)).usingRecursiveComparison().isEqualTo(genre);
    }

    @Test
    void deleteGenreById() {
        assertThat(genreDao.deleteGenreById(4L)).isEqualTo(4);
        assertThat(genreDao.getAllGenres())
                .hasSize(3);
    }

    @Test
    void updateGenre() {
        var genreNew =  new Genre(1L, "Example");
        genreDao.updateGenre(genreNew);
        var genre = em.find(Genre.class, 1L);
        assertThat(genre).usingRecursiveComparison().isEqualTo(genreNew);
    }

    @Test
    void insertGenre() {
        var genreNew =  new Genre( "genre");
        genreDao.saveGenre(genreNew);
        var genre = em.find(Genre.class, 5L);
        assertThat(genre).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(genreNew);
    }
    
}
