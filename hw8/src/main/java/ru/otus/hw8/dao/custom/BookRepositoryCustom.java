package ru.otus.hw8.dao.custom;

import ru.otus.hw8.domain.Author;
import ru.otus.hw8.domain.Genre;

public interface BookRepositoryCustom {
    boolean checkBookHaveAuthor(String authorId);
    boolean checkBookHaveGenre(String genreId);
}
