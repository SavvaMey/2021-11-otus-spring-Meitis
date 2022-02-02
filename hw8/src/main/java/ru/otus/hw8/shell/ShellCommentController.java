package ru.otus.hw8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw8.domain.Comment;
import ru.otus.hw8.service.intrf.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommentController {
    private final CommentService commentServiceImpl;

    @ShellMethod(value = "Get Comments By BookId", key = "comments")
    public List<Comment> getCommentsByBookId(@ShellOption String bookId) {
        return commentServiceImpl.getCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Get Comment by Id", key = "comment")
    public Comment getCommentById(@ShellOption String id) {
        return commentServiceImpl.getCommentById(id);
    }

    @ShellMethod(value = "Delete Comment by id", key = "deleteC")
    public void deleteCommentById(@ShellOption String id) {
        commentServiceImpl.deleteCommentById(id);
    }

    @ShellMethod(value = "Update Comment", key = "updateC")
    public Comment updateCommentById(@ShellOption String id, @ShellOption String commentName) {
        return commentServiceImpl.updateComment(id, commentName);
    }

    @ShellMethod(value = "Insert Comment", key = "insertC")
    public Comment insertComment(@ShellOption String commentName, @ShellOption String idBook) {
        return commentServiceImpl.saveComment(commentName, idBook);
    }
}