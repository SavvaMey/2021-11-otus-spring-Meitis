package ru.otus.hw8.dao.intrf;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw8.dao.custom.CommentRepositoryCustom;
import ru.otus.hw8.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {
    List<Comment> findByBookId(String id);
}
