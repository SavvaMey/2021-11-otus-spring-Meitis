package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.otus.dao.intrf.BookDao;
import ru.otus.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getAllBooks() {
        var entityGraph = em.getEntityGraph("bookGraph");
        var query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Book getBookById(long id) {
        var entityGraph = em.getEntityGraph("bookGraph");
        var query = em.createQuery("select b from Book b where b.id = :id", Book.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        Book book = query.getSingleResult();
        Hibernate.initialize(book.getCommentList());
        return book;
    }

    @Override
    public long deleteBookById(long id) {
        var book = em.find(Book.class, id);
        em.remove(book);
        return id;
    }

    @Override
    public long updateBook(Book book) {
        return em.merge(book).getId();
    }

    @Override
    public Book insertBook(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }
}
