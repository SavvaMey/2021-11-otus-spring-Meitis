package ru.otus.dao.intrf;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllGenres();
    Genre getGenreById(long id);
    long deleteGenreById(long id);
    long updateGenre(Genre genre);
    Genre insertGenre(Genre genre);

}
