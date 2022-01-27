package ru.otus.hw7.dao;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw7.domain.Book;
import ru.otus.hw7.domain.Comment;
import ru.otus.hw7.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TestEntityManager em;


    @Test
    void findById() {
        var comment = em.find(Comment.class, 1L);
        var comment1 = commentDao.findById(1L);
        assertThat(comment1.get()).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(comment);
        assertThat(comment1.get().getText()).isEqualTo("good book");
    }
}