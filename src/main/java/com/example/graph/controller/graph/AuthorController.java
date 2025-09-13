package com.example.graph.controller.graph;

import com.example.graph.entity.AuthorEntity;
import com.example.graph.graphql.input.AuthorInput;
import com.example.graph.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @MutationMapping
    public AuthorEntity createAuthor(@Argument AuthorInput author){
        return authorService.create(author);
    }

    @QueryMapping
    public List<AuthorEntity> getAuthors(){
        return authorService.getAll();
    }
}
