package ru.otus.hw7.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw7.domain.Book;
import ru.otus.hw7.service.intrf.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellBookController {
    private final BookService bookServiceImpl;

    @ShellMethod(value = "Get all Books", key = "books")
    public void getAllBooks() {
        List<Book> books = bookServiceImpl.getAllBooks();
        for (Book book : books) {
            System.out.printf("id: %s, title: %s, genre: %s, author name: %s %s " + System.lineSeparator(),
                    book.getId(), book.getTitle(), book.getGenre().getNameGenre(), book.getAuthor().getFirstName(),
                    book.getAuthor().getLastName());
        }
    }

    @ShellMethod(value = "Get Book by Id", key = "book")
    public void getBookById(@ShellOption long id) {
        Book book =  bookServiceImpl.getBookById(id);
        System.out.println(book);
        book.getCommentList().forEach(System.out::println);
    }

    @ShellMethod(value = "Delete book by id", key = "delete")
    public long deleteBookById(@ShellOption long id) {
        bookServiceImpl.deleteBookById(id);
        return id;
    }

    @ShellMethod(value = "Update book", key = "update")
    public Book updateBookById(@ShellOption long id, @ShellOption String title, @ShellOption long authorId,
                               @ShellOption long genreId) {
        return bookServiceImpl.updateBook(id, title, authorId, genreId);
    }

    @ShellMethod(value = "save book", key = "save")
    public Book saveBook(@ShellOption long authorId,
                           @ShellOption long genreId, @ShellOption  String title) {
        return bookServiceImpl.saveBook(title, authorId, genreId);
    }

}
