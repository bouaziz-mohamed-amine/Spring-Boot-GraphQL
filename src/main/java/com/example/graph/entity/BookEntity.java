package com.example.graph.entity;

import jakarta.persistence.*;

@Entity()
@Table(name = "BOOKS")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private Double price;
    @ManyToOne()
    private  AuthorEntity author;

    public BookEntity(Long id, String title, Double price, Long authorId) {
        this.id = id;
        this.title = title;
        this.price = price;
//        this.author = new AuthorEntity(authorId,"");
    }

    public BookEntity() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }
}