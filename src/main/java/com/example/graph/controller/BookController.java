package com.example.graph.controller;

import com.example.graph.graphql.Author;
import com.example.graph.graphql.Book;
import com.example.graph.graphql.input.BookInput;
import com.example.graph.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;


@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
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
