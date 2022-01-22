package ru.otus.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@ToString(exclude = "commentList")
@ToString
@EqualsAndHashCode(exclude = "commentList")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "bookGraph",
        attributeNodes = {@NamedAttributeNode("author"),
                @NamedAttributeNode("genre"),
        }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    @ToString.Exclude
    private Genre genre;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "book")
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();

    public Book(Long id, String title, long authorId, long genreId) {
        this.id = id;
        this.title = title;
        this.author = new Author(authorId, null, null);
        this.genre = new Genre(genreId, null);
    }

    public Book(String title, long authorId, long genreId) {

        this.title = title;
        this.author = new Author(authorId, null, null);
        this.genre = new Genre(genreId, null);
    }

    public Book(Long id) {
        this.id = id;
    }

    public Book(long id, String text, Author author, Genre genre, List<Comment> commentList) {
        this.id = id;
        this.title = text;
        this.author = author;
        this.genre = genre;
        this.commentList = commentList;
    }
}
