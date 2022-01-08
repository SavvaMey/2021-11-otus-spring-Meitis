package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellGenreController {
    private final GenreService genreService;

    @ShellMethod(value = "Get all Genres", key = "genres")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @ShellMethod(value = "Get Genre by Id", key = "genre")
    public Genre getGenreById(@ShellOption long id) {
        return genreService.getGenreById(id);
    }

    @ShellMethod(value = "Delete Genre by id", key = "deleteG")
    public long deleteGenreById(@ShellOption long id) {
        return genreService.deleteGenreById(id);
    }

    @ShellMethod(value = "Update Genre", key = "updateG")
    public long updateGenreById(@ShellOption long id, @ShellOption String genreName) {
        return genreService.updateGenre(id, genreName);
    }

    @ShellMethod(value = "Insert Genre", key = "insertG")
    public long insertGenre(@ShellOption String genreName) {
        return genreService.insertGenre(genreName);
    }
}
