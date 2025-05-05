package com.example.graph.service;

import com.example.graph.entity.AuthorEntity;
import com.example.graph.graphql.input.AuthorInput;
import com.example.graph.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorEntity create(AuthorInput authorInput) {
        AuthorEntity author = new AuthorEntity(null,authorInput.name());
        return authorRepository.save(author);
    }

    public List<AuthorEntity> getAll() {
        return  authorRepository.findAll();
    }
}
