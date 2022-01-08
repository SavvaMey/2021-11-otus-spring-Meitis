package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;
import ru.otus.utils.BookMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class BookDao {
    private final NamedParameterJdbcTemplate dao;
    private final BookMapper bookMapper;

    public List<Book> getAllBooks() {
             return dao.query("select b.id as id, b.title as title, a.id as author_id," +
                    " a.firstName as firstName, a.lastName as lastName, g.id as genre_id," +
                    " g.nameGenre as genreName from books b  left join authors a  on a.id = b.author_id " +
                    "left join  genres g  on g.id = b.genre_id",bookMapper);
    }

    public Book getBookById(long id) {
        return dao.queryForObject("select b.id as id, b.title as title, a.id as author_id," +
                " a.firstName as firstName, a.lastName as lastName, g.id as genre_id," +
                " g.nameGenre as genreName from books b  left join authors a  on a.id = b.author_id " +
                "left join  genres g  on g.id = b.genre_id  where b.id = :id", Map.of("id", id), bookMapper);
    }

    public long deleteBookById(long id) {
        return dao.update("delete from books where id= :id", Map.of("id", id));
    }

    public long updateBook(long id, String title, long authorId, long genreId) {
        return  dao.update("update books set title = :title, author_id = :authorId, genre_id = :genreId " +
                "where id = :id ", Map.of("title", title, "authorId", authorId, "genreId", genreId,
                "id", id));
    }

    public long insertBook(String title, long authorId, long genreId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of("title", title, "authorId", authorId, "genreId", genreId));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        dao.update("insert into books  (title, author_id, genre_id) values (:title, :authorId, :genreId)",
                params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
