package com.example.graph;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @QueryMapping
    public Book getBookById(@Argument String id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.orElse(null); // Return null if not found
    }

    @QueryMapping
    public Author getAuthorById(@Argument String id) {
        Optional<Author> author = bookService.getAuthorById(id);
        return author.orElse(null); // Return null if not found
    }

    // Dynamically resolve the `author` field in `Book` based on `authorId`
    @SchemaMapping(typeName = "Book", field = "author")
    public Author getAuthor(Book book) {
        return bookService.getAuthorById(book.getAuthorId()).orElse(null);
    }
}
