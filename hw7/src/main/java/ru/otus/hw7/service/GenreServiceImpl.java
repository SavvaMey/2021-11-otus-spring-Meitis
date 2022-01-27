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

   
    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

   
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
    public Genre saveOrUpdateGenre(long id, String titleName) {
        var genre = new Genre(id, titleName);
        return genreDao.save(genre);
    }
}
