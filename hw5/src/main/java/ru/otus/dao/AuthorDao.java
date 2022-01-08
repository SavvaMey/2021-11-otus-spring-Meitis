package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.utils.AuthorMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
@Repository
@AllArgsConstructor
public class AuthorDao {
    private final NamedParameterJdbcTemplate dao;
    private final AuthorMapper authorMapper;

    public List<Author> getAllAuthors() {
        return dao.query("select * from authors", authorMapper);
    }

    public Author getAuthorById(long id) {
        return dao.queryForObject("select * from authors where id = :id", Map.of("id", id), authorMapper);
    }

    public long deleteAuthorById(long id) {
        return dao.update("delete from authors where id= :id", Map.of("id", id));
    }

    public long updateAuthor(long id, String firstName,  String lastName) {
        return dao.update("update authors set firstName = :firstName, lastName = :lastName where id = :id ",
                Map.of("firstName", firstName, "lastName", lastName, "id", id));
    }

    public long insertAuthor(String firstName, String lastName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of("firstName", firstName, "lastName", lastName));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        dao.update("insert into authors  (firstName, lastName) values (:firstName, :lastName)",
                params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
