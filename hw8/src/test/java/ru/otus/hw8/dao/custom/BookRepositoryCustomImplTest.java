package ru.otus.hw8.dao.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.hw8.AbstractRepoTest;
import ru.otus.hw8.dao.intrf.BookRepository;
import ru.otus.hw8.domain.Book;
import ru.otus.hw8.domain.Genre;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("кастомные методы для репо с книгами")
class BookRepositoryCustomImplTest extends AbstractRepoTest {

    @Autowired
    BookRepository bookRepository;

    private Book book;
    private String genreId;
    private String authorId;

    @PostConstruct
    private void postConstruct() {
      book = bookRepository.findAll().get(0);
      genreId = book.getGenre().getId();
      authorId = book.getAuthor().getId();
    }

    @Test
    void checkBookHaveAuthor() {
        assertThat(bookRepository.checkBookHaveAuthor(authorId))
                .isTrue();
    }

    @Test
    void checkBookHaveGenre() {
        bookRepository.checkBookHaveGenre(book.getId());
        assertThat(bookRepository.checkBookHaveGenre(genreId))
                .isTrue();
    }
}