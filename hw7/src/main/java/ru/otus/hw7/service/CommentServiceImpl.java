package ru.otus.hw7.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7.dao.BookDao;
import ru.otus.hw7.dao.CommentDao;
import ru.otus.hw7.domain.Book;
import ru.otus.hw7.domain.Comment;
import ru.otus.hw7.exception.ObjectNotFound;
import ru.otus.hw7.service.intrf.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final BookDao bookDao;

    
    @Override
    public List<Comment> getCommentsByBookId(long bookId) {
        return commentDao.findAllByBookId(bookId);
    }

  
    @Override
    public Comment getCommentById(long id) {
        Optional<Comment> comment = commentDao.findById(id);
        return comment.orElseThrow(() -> new ObjectNotFound("элемент не найден"));
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        try {
           commentDao.deleteById(id);
        } catch (DataAccessException ex) {
            throw new ObjectNotFound("не задан верный id");
        }
    }

    @Transactional
    @Override
    public Comment saveOrUpdateComment(long id, String titleName, long idBook) {
        Book book = bookDao.getById(idBook);
        var comment = new Comment(id, titleName, book);
        return commentDao.save(comment);
    }
}
