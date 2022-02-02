package ru.otus.hw8.dao.custom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.hw8.AbstractRepoTest;
import ru.otus.hw8.dao.intrf.BookRepository;
import ru.otus.hw8.dao.intrf.CommentRepository;
import ru.otus.hw8.service.intrf.BookService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommentRepositoryCustomImplTest extends AbstractRepoTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void deleteAllCommentsByBookId() {
        var books = bookRepository.findAll();
        var bookId = books.get(0).getId();
        commentRepository.deleteAllCommentsByBookId(bookId);
        var book = bookRepository.findById(bookId);
        assertThat(commentRepository.findByBookId(bookId)).hasSize(0);
    }
}