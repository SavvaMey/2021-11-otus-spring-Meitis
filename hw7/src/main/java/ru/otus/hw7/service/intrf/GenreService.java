package ru.otus.hw7.service.intrf;

import ru.otus.hw7.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    Genre getGenreById(long id);

    void deleteGenreById(long id);

    long updateGenre(long id, String titleName);

    Genre insertGenre(String genreName);
}
