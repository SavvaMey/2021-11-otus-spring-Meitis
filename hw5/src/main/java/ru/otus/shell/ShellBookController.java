package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Book;
import ru.otus.service.BookService;
import ru.otus.service.BookServiceImpl;

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
    public Book getBookById(@ShellOption long id) {
        return bookServiceImpl.getBookById(id);
    }

    @ShellMethod(value = "Delete book by id", key = "delete")
    public long deleteBookById(@ShellOption long id) {
        return bookServiceImpl.deleteBookById(id);
    }

    @ShellMethod(value = "Update book", key = "update")
    public long updateBookById(@ShellOption long id, @ShellOption String title, @ShellOption long authorId,
                               @ShellOption long genreId) {
        return bookServiceImpl.updateBook(id, title, authorId, genreId);
    }

    @ShellMethod(value = "insert book", key = "insert")
    public long insertBook(@ShellOption long authorId,
                               @ShellOption long genreId, @ShellOption  String title) {
        return bookServiceImpl.insertBook(title, authorId, genreId);
    }

}
