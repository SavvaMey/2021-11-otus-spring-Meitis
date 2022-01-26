package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.dao.intrf.GenreDao;
import ru.otus.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> getAllGenres() {
        var query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre getGenreById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public long deleteGenreById(long id) {
        Genre genre = em.find(Genre.class, id);
        em.remove(genre);
        return id;
    }

    @Override
    public long updateGenre(Genre genre) {
        return em.merge(genre).getId();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        if (genre.getId() == null) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }
}
