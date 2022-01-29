package ru.otus.service.intrf;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBookId(long bookId);

    Comment getCommentById(long id);

    void deleteCommentById(long id);

    long updateComment(long id, String commentName, long idBook);

    Comment saveComment(String commentName, long idBook);
}
