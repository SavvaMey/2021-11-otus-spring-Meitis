package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.dao.intrf.AuthorDao;
import ru.otus.domain.Author;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Author> getAllAuthors() {
        var query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author getAuthorById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public long deleteAuthorById(long id) {
        var author = em.find(Author.class, id);
        em.remove(author);
        return id;
    }

    @Override
    public long updateAuthor(Author author) {
        return em.merge(author).getId();
    }

    @Override
    public Author saveAuthor(Author author) {
        if (author.getId() == null) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }
}
