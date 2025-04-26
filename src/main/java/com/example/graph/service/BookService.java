package com.example.graph.service;

import com.example.graph.entity.BookEntity;
import com.example.graph.enums.SortType;
import com.example.graph.graphql.Author;
import com.example.graph.graphql.Book;
import com.example.graph.graphql.input.BookInput;
import com.example.graph.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final List<Author> authors = new ArrayList<>();
    @Autowired
    private BookRepository bookRepository;

    public BookService() {
        // Add sample authors
        authors.add(new Author("1", "J.D. Salinger"));
        authors.add(new Author("2", "Harper Lee"));
        authors.add(new Author("3", "George Orwell"));
    }

    public List<BookEntity> getBooks(String sortBy, SortType sortType, Integer size, Integer start) {
        Sort.Direction direction = (sortType == SortType.DESC) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String fieldToSort = sortBy != null ? sortBy : "id";
        Sort sort = Sort.by(new Sort.Order(direction,fieldToSort));
        Pageable pageable = PageRequest.of(start, size, sort);
        return bookRepository.findAll(pageable).getContent();
    }

    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Author getAuthorById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public BookEntity addBook(BookInput bookInput) {
        BookEntity book = new BookEntity(null, bookInput.title(), bookInput.price());
        book = bookRepository.save(book);
        return book;
    }

    public BookEntity updateBook(BookInput book) {
        BookEntity oldBook = this.getBookById(Long.valueOf(book.id()));
        oldBook.setTitle(book.title());
        oldBook.setPrice(book.price());
        return bookRepository.save(oldBook);
    }

    public boolean removeBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}