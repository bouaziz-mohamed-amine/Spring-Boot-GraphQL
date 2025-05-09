package com.example.graph.graphql.input;

public record BookInput(String id, String title,Double price,Long authorId, AuthorInput author) {
}