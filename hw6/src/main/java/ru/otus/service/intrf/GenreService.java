package ru.otus.service.intrf;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    Genre getGenreById(long id);
    long deleteGenreById(long id);
    long updateGenre(long id, String genreName);
    Genre insertGenre(String genreName);
}
