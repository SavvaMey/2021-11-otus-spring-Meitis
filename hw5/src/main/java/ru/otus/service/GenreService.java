package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreDao genreDao;

    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }

    public Genre getGenreById(long id) {
        return genreDao.getGenreById(id);
    }

    public long deleteGenreById(long id) {
        return genreDao.deleteGenreById(id);
    }

    public long updateGenre(long id, String genreName) {
        return genreDao.updateGenre(id,genreName);
    }

    public long insertGenre(String genreName) {
        return genreDao.insertGenre(genreName);
    }
}
