package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDaoImpl;

    @Override
    public List<Author> getAllAuthors() {
        return authorDaoImpl.getAllAuthors();
    }

    @Override
    public Author getAuthorById(long id) {
        return authorDaoImpl.getAuthorById(id);
    }

    @Override
    public long deleteAuthorById(long id) {
        return authorDaoImpl.deleteAuthorById(id);
    }

    @Override
    public long updateAuthor(long id, String firstName, String lastName) {
        return authorDaoImpl.updateAuthor(id, firstName, lastName);
    }

    @Override
    public long insertAuthor(String firstName, String lastName) {
        return authorDaoImpl.insertAuthor(firstName, lastName);
    }
}
