package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;
import ru.otus.utils.GenreMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao{
    private final NamedParameterJdbcTemplate dao;
    private final GenreMapper genreMapper;

    @Override
    public List<Genre> getAllGenres() {
        return dao.query("select * from genres", genreMapper);
    }

    @Override
    public Genre getGenreById(long id) {
        return dao.queryForObject("select * from genres where id = :id", Map.of("id", id), genreMapper);
    }

    @Override
    public long deleteGenreById(long id) {
        return dao.update("delete from genres where id= :id", Map.of("id", id));
    }

    @Override
    public long updateGenre(long id, String nameGenre) {
        return  dao.update("update genres set nameGenre = :nameGenre where id = :id ",
                Map.of("nameGenre", nameGenre,"id", id));
    }

    @Override
    public long insertGenre(String nameGenre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of("nameGenre", nameGenre));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        dao.update("insert into genre  (nameGenre) values (:nameGenre)",
                params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
