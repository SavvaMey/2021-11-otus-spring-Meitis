package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.intrf.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.service.intrf.AuthorService;

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
    @Transactional
    public void deleteAuthorById(long id) {
       authorDaoImpl.deleteAuthorById(id);
    }

    @Override
    @Transactional
    public long updateAuthor(long id, String firstName, String lastName) {
        var author = new Author(id, firstName, lastName);
        return authorDaoImpl.updateAuthor(author);
    }

    @Override
    @Transactional
    public Author saveAuthor(String firstName, String lastName) {
        var author = new Author(firstName, lastName);
        return authorDaoImpl.saveAuthor(author);
    }
}
