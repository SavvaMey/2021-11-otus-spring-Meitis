package ru.otus.hw8.dao.custom;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.otus.hw8.domain.Author;
import ru.otus.hw8.domain.Book;
import ru.otus.hw8.domain.Genre;

@Service
@AllArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean checkBookHaveAuthor(String authorId) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("author._id").is(authorId));
        return mongoTemplate.exists(query, Book.class);
    }

    @Override
    public boolean checkBookHaveGenre(String genreId) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("genre._id").is(genreId));
        return mongoTemplate.exists(query, Book.class);
    }
}
