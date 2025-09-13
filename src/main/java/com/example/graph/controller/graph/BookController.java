package com.example.graph.controller.graph;

import com.example.graph.entity.BookEntity;
import com.example.graph.enums.SortType;
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
    public List<BookEntity> getBooks(@Argument String sortBy, @Argument SortType sortType, @Argument Integer size, @Argument Integer start) {
        return bookService.getBooks(sortBy, sortType, size, start);
    }

    @QueryMapping
    public BookEntity getBookById(@Argument Long id) {
        BookEntity book = bookService.getBookById(id);
        return book;
    }

    // Dynamically resolve the `author` field in `Book` based on `authorId`
    @SchemaMapping(typeName = "Book", field = "authorId")
    public Long getAuthor(BookEntity book) {
        if(book.getAuthor() != null)
            return  book.getAuthor().getId();
        return null;
    }

    @MutationMapping
    public BookEntity createBook(@Argument BookInput book) {
        BookEntity createdBook = bookService.addBook(book);
        return createdBook; // Return null if not found
    }
    @MutationMapping
    public BookEntity updateBook(@Argument BookInput book) {
        BookEntity updatedBook = bookService.updateBook(book);
        return updatedBook; // Return null if not found
    }

    @MutationMapping
    public boolean removeBook(@Argument Long id) {
        boolean removed = bookService.removeBook(id);
        return removed; // Return null if not found
    }
}
