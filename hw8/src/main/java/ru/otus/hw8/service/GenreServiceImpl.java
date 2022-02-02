package ru.otus.hw8.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw8.dao.intrf.BookRepository;
import ru.otus.hw8.dao.intrf.GenreRepository;
import ru.otus.hw8.domain.Author;
import ru.otus.hw8.domain.Genre;
import ru.otus.hw8.exception.ObjectNotFound;
import ru.otus.hw8.service.intrf.GenreService;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreRepository.findByNameGenre(name)
                .orElseThrow(() -> new ObjectNotFound("нет такого жанра"));
    }

    @Override
    public Genre getGenreById(String id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("нет такого жанра"));
    }

    @Override
    public void deleteGenreById(String id) {
        if (!bookRepository.checkBookHaveGenre(id)) {
            var genre = getGenreById(id);
            genre.setId(id);
            genreRepository.delete(genre);
        } else {
            throw new ObjectNotFound("Жанр используется в книге");
        }
    }

    @Override
    public Genre saveOrUpdateGenre(String id, String genreName) {
        var genre = new Genre(id, genreName);
        return genreRepository.save(genre);
    }
}
