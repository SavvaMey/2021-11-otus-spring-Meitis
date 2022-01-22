package ru.otus.service.intrf;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentById(long id);

    long deleteCommentById(long id);

    long updateComment(long id, String commentName, long idBook);

    Comment insertComment(String commentName, long idBook);
}
