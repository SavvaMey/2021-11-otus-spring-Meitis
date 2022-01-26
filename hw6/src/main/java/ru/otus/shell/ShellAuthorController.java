package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Author;
import ru.otus.service.intrf.AuthorService;

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
    public long deleteAuthorById(@ShellOption long id) {
        return authorServiceImpl.deleteAuthorById(id);
    }

    @ShellMethod(value = "Update Author", key = "updateA")
    public long updateAuthorById(@ShellOption long id, @ShellOption String firstName,
                                 @ShellOption String lastName) {
        return authorServiceImpl.updateAuthor(id, firstName, lastName);
    }

    @ShellMethod(value = "Insert Author", key = "insertA")
    public Author insertAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        return authorServiceImpl.saveAuthor(firstName, lastName);
    }
}
