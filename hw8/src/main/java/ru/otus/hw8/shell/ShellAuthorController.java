package ru.otus.hw8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw8.domain.Author;
import ru.otus.hw8.service.intrf.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellAuthorController {
    private final AuthorService authorServiceImpl;

    @ShellMethod(value = "Get all Authors", key = "authors")
    public List<Author> getAllAuthors() {
        return authorServiceImpl.getAllAuthors();
    }

    @ShellMethod(value = "Get Author by Id", key = "authorId")
    public Author getAuthorById(@ShellOption String id) {
        return authorServiceImpl.getAuthorById(id);
    }

    @ShellMethod(value = "Get Author by Name", key = "authorName")
    public Author getAuthorByName(@ShellOption String firstName, @ShellOption String lastName) {
        return authorServiceImpl.getAuthorByFirstNameAndLastName(firstName, lastName);
    }

    @ShellMethod(value = "Delete Author by id", key = "deleteA")
    public void deleteAuthorByName(@ShellOption String id) {
        authorServiceImpl.deleteAuthorById(id);
    }

    @ShellMethod(value = "Update Author", key = "updateA")
    public Author updateAuthorById(@ShellOption String id, @ShellOption String firstName,
                                   @ShellOption String lastName) {
        return authorServiceImpl.saveOrUpdateAuthor(id, firstName, lastName);
    }

    @ShellMethod(value = "save Author", key = "saveA")
    public Author saveAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        return authorServiceImpl.saveOrUpdateAuthor(null, firstName, lastName);
    }
}
