package ru.otus.hw7.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.hw7.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {


    @EntityGraph("bookGraph")
    List<Book> findAll();

    @EntityGraph("bookGraphFull")
    Optional<Book> findById(long id);
}
