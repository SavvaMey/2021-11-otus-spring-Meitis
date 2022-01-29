package ru.otus.hw7.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7.dao.AuthorDao;
import ru.otus.hw7.domain.Author;
import ru.otus.hw7.exception.ObjectNotFound;
import ru.otus.hw7.service.intrf.AuthorService;


import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDaoImpl;

    @Override
    public List<Author> getAllAuthors() {
        return authorDaoImpl.findAll();
    }

    @Override
    public Author getAuthorById(long id) {
        Optional<Author> author = authorDaoImpl.findById(id);
        return author.orElseThrow(() -> new ObjectNotFound("элемент не найден"));
    }

    @Override
    @Transactional
    public void deleteAuthorById(long id) {
        try {
            authorDaoImpl.deleteById(id);
        } catch (DataAccessException ex) {
            throw new ObjectNotFound("не задан верный id");
        }
    }


    @Override
    @Transactional
    public Author saveOrUpdateAuthor(long id, String firstName, String lastName) {
        var author = new Author(id, firstName, lastName);
        return authorDaoImpl.save(author);
    }
}
