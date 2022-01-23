CREATE TABLE IF NOT EXISTS authors (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
                                       firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL
    );
ALTER TABLE authors
    ADD CONSTRAINT uq_authors UNIQUE(firstName, lastName);

CREATE TABLE IF NOT EXISTS  genres (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
                                       nameGenre  VARCHAR(100) UNIQUE NOT NULL
    );


CREATE TABLE  IF NOT EXISTS books (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
                                      title VARCHAR(100) UNIQUE NOT NULL,
    author_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    foreign key (author_id) references authors(id),
    foreign key (genre_id) references genres(id)
    );
ALTER TABLE books
    ADD CONSTRAINT uq_books UNIQUE(title, author_id, genre_id);


CREATE TABLE IF NOT EXISTS  comments (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                         text  VARCHAR(250) UNIQUE NOT NULL,
    book_id BIGINT NOT NULL,
    foreign key (book_id) references books(id)
    );