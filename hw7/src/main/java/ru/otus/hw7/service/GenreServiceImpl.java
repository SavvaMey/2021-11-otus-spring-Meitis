package ru.otus.hw7.service;


import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7.dao.GenreDao;
import ru.otus.hw7.domain.Genre;
import ru.otus.hw7.exception.ObjectNotFound;
import ru.otus.hw7.service.intrf.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(long id) {
        Optional<Genre> genre = genreDao.findById(id);
        return genre.orElseThrow(() -> new ObjectNotFound("элемент не найден"));
    }

    @Transactional
    @Override
    public void deleteGenreById(long id) {
        try {
            genreDao.deleteById(id);
        } catch (DataAccessException ex) {
            throw new ObjectNotFound("не задан верный id");
        }
    }

    @Transactional
    @Override
    public long updateGenre(long id, String title) {
        return genreDao.updateGenreById(title, id);
    }

    @Transactional
    @Override
    public Genre insertGenre(String titleName) {
        var genre = new Genre(titleName);
        return genreDao.save(genre);
    }
}
