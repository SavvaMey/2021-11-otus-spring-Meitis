package ru.otus.hw8.dao.custom;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.otus.hw8.domain.Book;
import ru.otus.hw8.domain.Comment;

@Service
@AllArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{
    private final MongoTemplate mongoTemplate;

    @Override
    public void deleteAllCommentsByBookId(String bookId) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("book._id").is(bookId));
        mongoTemplate.remove(query, Comment.class);
    }
}
