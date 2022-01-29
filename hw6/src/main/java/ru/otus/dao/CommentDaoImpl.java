package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.dao.intrf.CommentDao;
import ru.otus.domain.Author;
import ru.otus.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

//    @Override
//    public List<Comment> getCommentsByBookId(long bookId) {
//        var query = em.createQuery("select c from Comment c join fetch  c.book t where c.book.id = :bookId", Comment.class);
//        query.setParameter("bookId", bookId);
//        return query.getResultList();
//    }

    @Override
    public Comment getCommentById(long id) {
        Optional<Comment> comment = Optional.of(em.find(Comment.class, id));
        comment.ifPresent(value -> Hibernate.initialize(value.getBook()));
        return comment.get();
    }

    @Override
    public long deleteCommentById(long id) {
        var comment = em.find(Comment.class, id);
        em.remove(comment);
        return id;
    }

    @Override
    public long updateComment(Comment comment) {
        return  em.merge(comment).getId();
    }

    @Override
    public Comment saveComment(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }
}
