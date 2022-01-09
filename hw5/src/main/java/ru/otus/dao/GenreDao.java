package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllGenres();
    Genre getGenreById(long id);
    long deleteGenreById(long id);
    long updateGenre(long id, String nameGenre);
    long insertGenre(String nameGenre);

}
