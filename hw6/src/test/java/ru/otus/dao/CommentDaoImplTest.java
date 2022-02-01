package ru.otus.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.dao.intrf.CommentDao;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentDaoImpl.class)
public class CommentDaoImplTest {
   
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void getCommentByIdThenFirstComment() {
        var comment = em.find(Comment.class, 1L);
        Comment comment1 = commentDao
                .getCommentById(1);
        assertThat(comment1).usingRecursiveComparison().isEqualTo(comment);
        assertThat(comment1.getBook())
                .usingRecursiveComparison()
                .isEqualTo(comment.getBook());
    }

    @Test
    void deleteCommentById() {
        commentDao.deleteCommentById(1L);
        assertThat(em.find(Comment.class, 1L))
                .isNull();
    }

    @Test
    void updateComment() {
        var commentNew =  new Comment(1L, "Example");
        commentDao.updateComment(commentNew);
        var comment = em.find(Comment.class, 1L);
        assertThat(comment)
                .usingRecursiveComparison()
                .isEqualTo(commentNew);
    }

    @Test
    void insertComment() {
        var book = em.find(Book.class, 1L);
        var commentNew =  new Comment( "Comment", book);
        commentDao.saveComment(commentNew);
        var comment = em.find(Comment.class, 7L);
        assertThat(comment)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(commentNew);
    }
}
