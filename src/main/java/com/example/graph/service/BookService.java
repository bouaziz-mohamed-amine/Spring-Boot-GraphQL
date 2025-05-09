package com.example.graph.service;

import com.example.graph.entity.AuthorEntity;
import com.example.graph.entity.BookEntity;
import com.example.graph.enums.SortType;
import com.example.graph.graphql.input.BookInput;
import com.example.graph.repository.AuthorRepository;
import com.example.graph.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

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

    public AuthorEntity getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public BookEntity addBook(BookInput bookInput) {
        Long authorId = bookInput.author().id();
        AuthorEntity authorEntity;

        if (authorRepository.existsById(authorId)) {
            authorEntity = authorRepository.getReferenceById(authorId);
        } else {
            authorEntity = authorRepository.save(
                    new AuthorEntity(null, bookInput.author().name())
            );
        }

        BookEntity book = new BookEntity(null, bookInput.title(), bookInput.price(), authorEntity);
        return bookRepository.save(book);
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