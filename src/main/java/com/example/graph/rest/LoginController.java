package com.example.graph.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class LoginController {

    // GET /books
    @GetMapping
    public String getData() {
        return "books";
    }

}
