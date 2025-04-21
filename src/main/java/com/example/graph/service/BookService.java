package com.example.graph.service;

import com.example.graph.enums.SortType;
import com.example.graph.graphql.Author;
import com.example.graph.graphql.Book;
import com.example.graph.graphql.input.BookInput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        books.add(new Book("3", "Metaphysics", "3"));
    }

    public List<Book> getBooks(String sortBy, SortType sortType) {

      Comparator<Book> comparator = Comparator.comparing((Book book) -> book.getId());

      if(sortBy != null){
          switch (sortBy.trim().toLowerCase()) {
              case "title" :
                  comparator = Comparator.comparing((Book book) -> book.getTitle());
                  break;
              case "id" :
                  comparator = Comparator.comparing((Book book) -> book.getId());
                  break;
              default:
                  comparator = Comparator.comparing((Book book) -> book.getId());
                  break;
          }
          switch (sortType) {
              case DESC: comparator=comparator.reversed();
                  break;
              default: break;
          }
      }

      return books.stream()
              .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(String.valueOf(id))).findFirst().orElse(null);
    }

    public Author getAuthorById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public Book addBook(BookInput bookInput) {
        Book book = new Book(bookInput.id(),bookInput.title(),null,null);
        books.add(book);
        return book;
    }

    public Book updateBook(BookInput book) {
       Book oldBook = this.getBookById(Long.valueOf(book.id()));
       oldBook.setTitle(book.title());
       return oldBook;
    }

    public boolean removeBook(Long id) {
        Book book = this.getBookById(id);
        if (book != null) {
            return this.books.remove(book);
        }
        return false;
    }
}