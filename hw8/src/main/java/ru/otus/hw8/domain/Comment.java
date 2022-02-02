package ru.otus.hw8.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    private String text;
    private Book book;

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }
}
