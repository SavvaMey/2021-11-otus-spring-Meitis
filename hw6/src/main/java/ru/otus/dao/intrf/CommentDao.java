package ru.otus.dao.intrf;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentDao {
//    List<Comment> getCommentsByBookId(long bookId);

    Comment getCommentById(long id);

    long deleteCommentById(long id);

    long updateComment(Comment comment);

    Comment saveComment(Comment comment);
}
