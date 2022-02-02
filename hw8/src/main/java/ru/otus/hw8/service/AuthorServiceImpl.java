package ru.otus.hw8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw8.dao.intrf.AuthorRepository;
import ru.otus.hw8.dao.intrf.BookRepository;
import ru.otus.hw8.domain.Author;
import ru.otus.hw8.exception.ObjectNotFound;
import ru.otus.hw8.service.intrf.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("автор не найден"));
    }

    @Override
    public Author getAuthorByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndAndLastName(firstName, lastName)
                .orElseThrow(() -> new ObjectNotFound("автор не найден"));
    }

    @Override
    public void deleteAuthorById(String id) {
        if (!bookRepository.checkBookHaveAuthor(id)) {
            var author = getAuthorById(id);
            authorRepository.delete(author);
        } else {
            throw new ObjectNotFound("Автор используется в книге");
        }
    }

    @Override
    public Author saveOrUpdateAuthor(String id, String firstName, String lastName) {
        var author = new Author(id, firstName, lastName);
       return authorRepository.save(author);
    }
}
