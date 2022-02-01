package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.dao.intrf.BookDao;
import ru.otus.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getAllBooks() {
        var query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book getBookById(long id) {
        var book = em.find(Book.class, id);
        Hibernate.initialize(book.getCommentList());
        return book;
    }

    @Override
    public void deleteBookById(long id) {
        var book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public long updateBook(Book book) {
        return em.merge(book).getId();
    }

    @Override
    public Book saveBook(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }
}
