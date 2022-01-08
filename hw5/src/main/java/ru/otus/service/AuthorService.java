package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorDao authorDao;

    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    public Author getAuthorById(long id) {
        return authorDao.getAuthorById(id);
    }

    public long deleteAuthorById(long id) {
        return authorDao.deleteAuthorById(id);
    }

    public long updateAuthor(long id, String firstName, String lastName) {
        return authorDao.updateAuthor(id, firstName, lastName);
    }

    public long insertAuthor(String firstName, String lastName) {
        return authorDao.insertAuthor(firstName, lastName);
    }
}
