package ru.otus.hw8.service.intrf;

import ru.otus.hw8.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    Genre getGenreByName(String name);

    Genre getGenreById(String id);

    void deleteGenreById(String id);

    Genre saveOrUpdateGenre(String id, String genreName);
}
