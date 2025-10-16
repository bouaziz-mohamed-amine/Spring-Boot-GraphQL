package com.example.graph.controller.rest;


import com.example.graph.controller.rest.dto.auth.LoginRequest;
import com.example.graph.entity.UserEntity;
import com.example.graph.enums.ROLE;
import com.example.graph.repository.UserRepository;
import com.example.graph.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/rest/auth")
public class AuthController  {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if(user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("bad credentials");
        if(passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", JwtUtil.generateToken(user)));
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("bad credentials");
    }

    @PostMapping("/register")
    public ResponseEntity logOut(@RequestBody LoginRequest loginRequest) {
        UserEntity user = new UserEntity(loginRequest.getUsername(),passwordEncoder.encode(loginRequest.getPassword()), ROLE.USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

}
