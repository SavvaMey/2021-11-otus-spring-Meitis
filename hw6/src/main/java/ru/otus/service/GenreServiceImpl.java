package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.intrf.GenreDao;
import ru.otus.domain.Genre;
import ru.otus.service.intrf.GenreService;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDaoImpl;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreDaoImpl.getAllGenres();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreById(long id) {
        return genreDaoImpl.getGenreById(id);
    }

    @Override
    @Transactional
    public long deleteGenreById(long id) {
        return genreDaoImpl.deleteGenreById(id);
    }

    @Override
    @Transactional
    public long updateGenre(long id, String genreName) {
        var genre = new Genre(id, genreName);
        return genreDaoImpl.updateGenre(genre);
    }

    @Override
    @Transactional
    public Genre saveGenre(String genreName) {
        var genre = new Genre(genreName);
        return genreDaoImpl.saveGenre(genre);
    }
}
