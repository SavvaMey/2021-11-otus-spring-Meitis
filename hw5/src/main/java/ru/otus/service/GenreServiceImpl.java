package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService{
    private final GenreDao genreDaoImpl;

    @Override
    public List<Genre> getAllGenres() {
        return genreDaoImpl.getAllGenres();
    }

    @Override
    public Genre getGenreById(long id) {
        return genreDaoImpl.getGenreById(id);
    }

    @Override
    public long deleteGenreById(long id) {
        return genreDaoImpl.deleteGenreById(id);
    }

    @Override
    public long updateGenre(long id, String genreName) {
        return genreDaoImpl.updateGenre(id,genreName);
    }

    @Override
    public long insertGenre(String genreName) {
        return genreDaoImpl.insertGenre(genreName);
    }
}
