package ru.otus.hw7.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.hw7.domain.Author;


public interface AuthorDao extends JpaRepository<Author, Long> {

    @Modifying
    @Query("update Author u set u.firstName = :firstname, u.lastName = :lastname where u.id = :id")
    int updateAuthorById(@Param("firstname") String firstname,
                          @Param("lastname") String lastname,
                          @Param("id") Long id);
}
