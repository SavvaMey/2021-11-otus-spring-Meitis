package ru.otus.hw8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw8.domain.Book;
import ru.otus.hw8.service.intrf.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellBookController {
    private final BookService bookServiceImpl;

    @ShellMethod(value = "Get all Books", key = "books")
    public List<Book> getAllBooks() {
        return bookServiceImpl.getAllBooks();
    }

    @ShellMethod(value = "Get Book by Id", key = "book")
    public Book getBookById(@ShellOption String id) {
        return bookServiceImpl.getBookById(id);
    }

    @ShellMethod(value = "Get Book by Name", key = "bookName")
    public Book getBookByName(@ShellOption String name) {
        return bookServiceImpl.getBookByName(name);
    }

    @ShellMethod(value = "Delete book by id", key = "delete")
    public void deleteBookById(@ShellOption String id) {
        bookServiceImpl.deleteBookById(id);
    }

    @ShellMethod(value = "Update book", key = "update")
    public Book updateBookById(@ShellOption String id, @ShellOption String title) {
        return bookServiceImpl.updateBook(id, title);
    }

    @ShellMethod(value = "save book", key = "save")
    public Book saveBook(@ShellOption String authorId,
                         @ShellOption String genreId, @ShellOption  String title) {
        return bookServiceImpl.saveBook(title, authorId, genreId);
    }

}
