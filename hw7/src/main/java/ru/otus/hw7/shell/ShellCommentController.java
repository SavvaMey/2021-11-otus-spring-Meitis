package ru.otus.hw7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw7.domain.Comment;
import ru.otus.hw7.service.intrf.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommentController {
    private final CommentService commentServiceImpl;

    @ShellMethod(value = "Get all Comments", key = "commentBook")
    public List<Comment> getCommentsByBookId(@ShellOption long bookId) {
        return commentServiceImpl.getCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Get Comment by Id", key = "comment")
    public void getCommentById(@ShellOption long id) {
        Comment comment = commentServiceImpl.getCommentById(id);
        System.out.println(comment);
        System.out.println(comment.getBook());
    }

    @ShellMethod(value = "Delete Comment by id", key = "deleteC")
    public long deleteCommentById(@ShellOption long id) {
       commentServiceImpl.deleteCommentById(id);
        return id;
    }

    @ShellMethod(value = "Update Comment", key = "updateC")
    public Comment updateCommentById(@ShellOption long id, @ShellOption String commentName,
                                  @ShellOption long idBook) {
        return commentServiceImpl.saveOrUpdateComment(id, commentName, idBook);
    }

    @ShellMethod(value = "save Comment", key = "saveC")
    public Comment saveComment(@ShellOption String commentName, @ShellOption long idBook) {
        return commentServiceImpl.saveOrUpdateComment(0, commentName, idBook);
    }
}