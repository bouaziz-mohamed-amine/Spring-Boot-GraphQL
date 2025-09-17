package com.example.graph.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

//    private final UserRepository userRepository;

//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    private final String adminPass;
    private final String userPass;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        // Encode ONCE when the bean is created
        this.adminPass = passwordEncoder.encode("password");
        this.userPass = passwordEncoder.encode("password");
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
//        AppUser user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        return User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRoles().toArray(new String[0]))
//                .disabled(!user.isEnabled())
//                .build();
//    }
        if (username.equals("admin")) {
            return User.builder()
                    .username("admin")
                    .password(this.adminPass)
                    .roles("ADMIN")
                    .build();
        } else if (username.equals("user")) {
            return User.builder()
                    .username("user")
                    .password(this.userPass)
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
