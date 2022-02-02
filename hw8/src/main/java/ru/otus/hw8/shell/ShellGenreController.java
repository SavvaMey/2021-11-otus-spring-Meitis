package ru.otus.hw8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw8.domain.Genre;
import ru.otus.hw8.service.intrf.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellGenreController {
    private final GenreService genreServiceImpl;

    @ShellMethod(value = "Get all Genres", key = "Genres")
    public List<Genre> getAllGenres() {
        return genreServiceImpl.getAllGenres();
    }

    @ShellMethod(value = "Get Genre by Id", key = "GenreId")
    public Genre getGenreById(@ShellOption String id) {
        return genreServiceImpl.getGenreById(id);
    }

    @ShellMethod(value = "Get Genre by Name", key = "GenreName")
    public Genre getGenreByName(@ShellOption String name) {
        return genreServiceImpl.getGenreByName(name);
    }

    @ShellMethod(value = "Delete Genre by id", key = "deleteG")
    public void deleteGenreById(@ShellOption String id) {
        genreServiceImpl.deleteGenreById(id);
    }

    @ShellMethod(value = "Update Genre", key = "updateG")
    public Genre updateGenreById(@ShellOption String id, @ShellOption String title) {
        return genreServiceImpl.saveOrUpdateGenre(id, title);
    }

    @ShellMethod(value = "save Genre", key = "saveG")
    public Genre saveGenre(@ShellOption String titleGenre) {
        return genreServiceImpl.saveOrUpdateGenre(null, titleGenre);
    }
}