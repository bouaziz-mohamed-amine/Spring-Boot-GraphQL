package com.example.graph;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();

    public BookService() {
        // Add sample authors
        authors.add(new Author("1", "J.D. Salinger"));
        authors.add(new Author("2", "Harper Lee"));
        authors.add(new Author("3", "George Orwell"));

        // Add sample books with authorId
        books.add(new Book("1", "The Catcher in the Rye", "1"));
        books.add(new Book("2", "To Kill a Mockingbird", "2"));
        books.add(new Book("3", "1984", "3"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public Optional<Author> getAuthorById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst();
    }

    public void updateBookAuthor(String bookId, String newAuthorId) {
        Optional<Book> book = getBookById(bookId);
        if (book.isPresent()) {
            book.get().setAuthorId(newAuthorId);
        }
    }
}