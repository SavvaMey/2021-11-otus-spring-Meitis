package ru.otus.hw7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw7.domain.Genre;
import ru.otus.hw7.service.intrf.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellGenreController {
    private final GenreService genreServiceImpl;

    @ShellMethod(value = "Get all Genres", key = "Genres")
    public List<Genre> getAllGenres() {
        return genreServiceImpl.getAllGenres();
    }

    @ShellMethod(value = "Get Genre by Id", key = "Genre")
    public Genre getGenreById(@ShellOption long id) {
        return genreServiceImpl.getGenreById(id);
    }

    @ShellMethod(value = "Delete Genre by id", key = "deleteG")
    public long deleteGenreById(@ShellOption long id) {
        genreServiceImpl.deleteGenreById(id);
        return id;
    }

    @ShellMethod(value = "Update Genre", key = "updateG")
    public long updateGenreById(@ShellOption long id, @ShellOption String title) {
        return genreServiceImpl.updateGenre(id, title);
    }

    @ShellMethod(value = "Insert Genre", key = "insertG")
    public Genre insertGenre(@ShellOption String titleGenre) {
        return genreServiceImpl.insertGenre(titleGenre);
    }
}