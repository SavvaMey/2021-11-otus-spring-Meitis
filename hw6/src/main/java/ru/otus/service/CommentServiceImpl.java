package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.intrf.BookDao;
import ru.otus.dao.intrf.CommentDao;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.exceptions.ObjectNotFound;
import ru.otus.service.intrf.CommentService;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final BookDao bookDao;
    private final CommentDao commentDaoImpl;
   
    @Override
    @Transactional
    public List<Comment> getCommentsByBookId(long bookId) {
        var book = bookDao.getBookById(bookId);
        return book.getCommentList();
    }

    @Override
    public Comment getCommentById(long id) {
            Comment comment = commentDaoImpl.getCommentById(id);
            if (comment == null) {
                throw new ObjectNotFound("Такой книги нет");
            }
            return comment;
    }

    @Override
    @Transactional
    public void deleteCommentById(long id) {
       commentDaoImpl.deleteCommentById(id);
    }

    @Override
    @Transactional
    public long updateComment(long id, String text, long idBook) {
        Book book = new Book(idBook);
        var comment = new Comment(id, text, book);
        return commentDaoImpl.updateComment(comment);
    }

    @Override
    @Transactional
    public Comment saveComment(String commentName, long idBook) {
        Book book = new Book(idBook);
        var comment = new Comment(commentName, book);
        return commentDaoImpl.saveComment(comment);
    }
}
