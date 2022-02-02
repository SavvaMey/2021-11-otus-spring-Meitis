package ru.otus.hw8.dao.intrf;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw8.dao.custom.BookRepositoryCustom;
import ru.otus.hw8.domain.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository <Book, String>, BookRepositoryCustom {

    Optional<Book> findBookByTitle(String title);
}
