package ru.otus.hw8.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import ru.otus.hw8.dao.intrf.AuthorRepository;
import ru.otus.hw8.dao.intrf.BookRepository;
import ru.otus.hw8.dao.intrf.CommentRepository;
import ru.otus.hw8.dao.intrf.GenreRepository;
import ru.otus.hw8.domain.Author;
import ru.otus.hw8.domain.Book;
import ru.otus.hw8.domain.Comment;
import ru.otus.hw8.domain.Genre;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

//    private final AuthorRepository authorRepository;
//    private final GenreRepository genreRepository;

    private Author author1 = new Author( "Gayle", "Laakmann McDowell");
    private Author author2 = new Author( "Ivan", "Ivanov");
    private Author author3 = new Author( "Alex", "Kodov");
    private Genre genre1 = new Genre("Textbook");
    private Genre genre2 = new Genre("Detective");
    private Genre genre3 = new Genre("Horror");
    private Book book1 = new Book("Cracking the Coding Interview", author1, genre1);
    private Book book2 = new Book("who kill bob", author2, genre2);
    private Book book3 = new Book("dreams", author3, genre3);
    private Comment comment1 = new Comment("great", book1);
    private Comment comment11 = new Comment("amazing", book1);
    private Comment comment2 = new Comment("wow", book2);
    private Comment comment3 = new Comment("bad", book3);

    @ChangeSet(order = "000", id = "dropDb", author = "SavvaMei", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "saveAuthors", author = "SavvaMei", runAlways = true)
    public void initAuthors(AuthorRepository aR) {
        aR.save(author1);
        aR.save(author2);
        aR.save(author3);
    }

    @ChangeSet(order = "002", id = "saveGenres", author = "SavvaMei", runAlways = true)
    public void initGenres(GenreRepository repository) {
        repository.save(genre1);
        repository.save(genre2);
        repository.save(genre3);
    }

    @ChangeSet(order = "003", id = "saveBooks", author = "SavvaMei", runAlways = true)
    public void initBooks(BookRepository repository) {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
    }

    @ChangeSet(order = "004", id = "saveComments", author = "SavvaMei", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(comment1);
        repository.save(comment11);
        repository.save(comment2);
        repository.save(comment3);
    }
}

