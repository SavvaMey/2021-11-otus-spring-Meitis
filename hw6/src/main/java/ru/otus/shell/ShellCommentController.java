package ru.otus.shell;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Comment;
import ru.otus.service.intrf.CommentService;
import ru.otus.service.intrf.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommentController {
    private final CommentService commentServiceImpl;

    @ShellMethod(value = "Get Comments By BookId", key = "comments")
    public List<Comment> getCommentsByBookId(@ShellOption long bookId) {
        return commentServiceImpl.getCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Get Comment by Id", key = "comment")
    public Comment getCommentById(@ShellOption long id) {
        return commentServiceImpl.getCommentById(id);
    }

    @ShellMethod(value = "Delete Comment by id", key = "deleteC")
    public void deleteCommentById(@ShellOption long id) {
       commentServiceImpl.deleteCommentById(id);
    }

    @ShellMethod(value = "Update Comment", key = "updateC")
    public long updateCommentById(@ShellOption long id, @ShellOption String commentName, @ShellOption long idBook ) {
        return commentServiceImpl.updateComment(id, commentName, idBook);
    }

    @ShellMethod(value = "Insert Comment", key = "insertC")
    public Comment insertComment(@ShellOption String commentName, @ShellOption long idBook) {
        return commentServiceImpl.saveComment(commentName, idBook);
    }
}
