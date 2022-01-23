package ru.otus.hw7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.hw7.domain.Genre;

@Repository
public interface GenreDao extends JpaRepository<Genre, Long> {

    @Modifying
    @Query("update Genre u set u.nameGenre = :nameGenre where u.id = :id")
    int updateGenreById( @Param("nameGenre") String nameGenre,
                         @Param("id") Long id);
}
