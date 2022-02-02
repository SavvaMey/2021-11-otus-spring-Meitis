package ru.otus.hw8.service.intrf;

import ru.otus.hw8.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBookId(String bookId);

    Comment getCommentById(String id);

    void deleteCommentById(String id);

    Comment updateComment(String id, String commentName);

    Comment saveComment(String commentName, String idBook);
}
