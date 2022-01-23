package ru.otus.hw7.service.intrf;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentById(long id);

    void deleteCommentById(long id);

    long updateComment(long id, String text);

    @Transactional
    Comment insertComment(String titleName, long idBook);
}
