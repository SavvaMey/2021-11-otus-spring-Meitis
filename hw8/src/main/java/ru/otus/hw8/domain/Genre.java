package ru.otus.hw8.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Genre {

    @Id
    private String id;

    private String nameGenre;

    public Genre(String genreName) {
        this.nameGenre = genreName;
    }
}
