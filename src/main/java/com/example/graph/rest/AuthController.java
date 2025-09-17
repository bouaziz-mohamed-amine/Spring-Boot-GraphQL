package com.example.graph.rest;


import com.example.graph.rest.dto.auth.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/auth")
public class AuthController  {


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginRequest);
    }

    @GetMapping("/logout")
    public ResponseEntity logOut() {
        return ResponseEntity.ok("logout");
    }

}
