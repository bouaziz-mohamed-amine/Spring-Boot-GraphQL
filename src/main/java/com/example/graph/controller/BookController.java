package com.example.graph.controller;

import com.example.graph.enums.SortType;
import com.example.graph.graphql.Author;
import com.example.graph.graphql.Book;
import com.example.graph.graphql.input.BookInput;
import com.example.graph.service.BookService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import org.slf4j.Logger;

@Controller
public class BookController {

    private final BookService bookService;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> getBooks(@Argument String sortBy, @Argument SortType sortType, @Argument Integer size, @Argument Integer start) {
        return bookService.getBooks(sortBy, sortType, size, start);
    }

    @QueryMapping
    public Book getBookById(@Argument Long id) {
        Book book = bookService.getBookById(id);
        return book; // Return null if not found
    }

    // Dynamically resolve the `author` field in `Book` based on `authorId`
    @SchemaMapping(typeName = "Book", field = "author")
    public Author getAuthor(Book book) {
        return bookService.getAuthorById(book.getAuthorId());
    }

    @MutationMapping
    public Book createBook(@Argument BookInput book) {
        Book createdBook = bookService.addBook(book);
        return createdBook; // Return null if not found
    }
    @MutationMapping
    public Book updateBook(@Argument BookInput book) {
        Book updatedBook = bookService.updateBook(book);
        return updatedBook; // Return null if not found
    }

    @MutationMapping
    public boolean removeBook(@Argument Long id) {
        boolean removed = bookService.removeBook(id);
        return removed; // Return null if not found
    }
}
