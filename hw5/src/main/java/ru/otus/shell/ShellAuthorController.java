package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Author;
import ru.otus.domain.Genre;
import ru.otus.service.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellAuthorController {
    private final AuthorService authorService;

    @ShellMethod(value = "Get all Authors", key = "authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @ShellMethod(value = "Get Author by Id", key = "author")
    public Author getAuthorById(@ShellOption long id) {
        return authorService.getAuthorById(id);
    }

    @ShellMethod(value = "Delete Author by id", key = "deleteA")
    public long deleteAuthorById(@ShellOption long id) {
        return authorService.deleteAuthorById(id);
    }

    @ShellMethod(value = "Update Author", key = "updateA")
    public long updateAuthorById(@ShellOption long id, @ShellOption String firstName,
                                 @ShellOption String lastName) {
        return authorService.updateAuthor(id, firstName, lastName);
    }

    @ShellMethod(value = "Insert Author", key = "insertA")
    public long insertAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        return authorService.insertAuthor(firstName, lastName);
    }
}
