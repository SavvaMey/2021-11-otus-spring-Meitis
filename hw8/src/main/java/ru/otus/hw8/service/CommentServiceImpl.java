package ru.otus.hw8.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw8.dao.intrf.CommentRepository;
import ru.otus.hw8.domain.Comment;
import ru.otus.hw8.exception.ObjectNotFound;
import ru.otus.hw8.service.intrf.BookService;
import ru.otus.hw8.service.intrf.CommentService;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookService bookService;
    @Override
    public List<Comment> getCommentsByBookId(String bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    public Comment getCommentById(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new ObjectNotFound("такеого комментария нет"));
    }

    @Override
    public void deleteCommentById(String id) {
        var comment = getCommentById(id);
        commentRepository.delete(comment);
    }

    @Override
    public Comment updateComment(String id, String commentName) {
        var comment = getCommentById(id);
        comment.setText(commentName);
        return commentRepository.save(comment);
    }

    @Override
    public Comment saveComment(String commentName, String idBook) {
        var book = bookService.getBookById(idBook);
        Comment comment = new Comment(commentName, book);
        return commentRepository.save(comment);
    }
}
