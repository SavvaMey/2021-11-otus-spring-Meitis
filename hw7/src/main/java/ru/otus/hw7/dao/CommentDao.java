package ru.otus.hw7.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.hw7.domain.Comment;

import java.util.Optional;

public interface CommentDao extends JpaRepository<Comment, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Comment c set c.text = :text where c.id = :id")
    int updateCommentById( @Param("text") String text,
                         @Param("id") Long id);

    @EntityGraph("bookInfo")
    Optional<Comment> findById(Long aLong);

}
