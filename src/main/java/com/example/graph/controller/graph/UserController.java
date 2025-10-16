package com.example.graph.controller.graph;


import com.example.graph.entity.UserEntity;
import com.example.graph.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @QueryMapping
    public UserEntity me(Authentication authentication){
        return userRepository.findById(Long.valueOf(authentication.getCredentials().toString())).get();
    }
}
