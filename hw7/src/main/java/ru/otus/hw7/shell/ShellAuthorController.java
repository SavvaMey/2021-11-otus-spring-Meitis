package ru.otus.hw7.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw7.domain.Author;
import ru.otus.hw7.service.intrf.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellAuthorController {
    private final AuthorService authorServiceImpl;

    @ShellMethod(value = "Get all Authors", key = "authors")
    public List<Author> getAllAuthors() {
        return authorServiceImpl.getAllAuthors();
    }

    @ShellMethod(value = "Get Author by Id", key = "author")
    public Author getAuthorById(@ShellOption long id) {
        return authorServiceImpl.getAuthorById(id);
    }

    @ShellMethod(value = "Delete Author by id", key = "deleteA")
    public long  deleteAuthorById(@ShellOption long id) {
        authorServiceImpl.deleteAuthorById(id);
        return id;
    }

    @ShellMethod(value = "Update Author", key = "updateA")
    public Author updateAuthorById(@ShellOption long id, @ShellOption String firstName,
                                 @ShellOption String lastName) {
        return authorServiceImpl.saveOrUpdateAuthor(id, firstName, lastName);
    }

    @ShellMethod(value = "save Author", key = "saveA")
    public Author saveAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        return authorServiceImpl.saveOrUpdateAuthor(0, firstName, lastName);
    }
}
