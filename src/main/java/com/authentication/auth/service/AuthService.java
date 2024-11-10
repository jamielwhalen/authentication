package com.authentication.auth.service;

import com.authentication.auth.data.UserRepo;
import com.authentication.auth.data.Users;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Users register(String firstName, String lastName, String email, String password, String passwordConfirm){
        if(!Objects.equals(password, passwordConfirm)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passwords do not match");
        }

        return userRepo.save(Users.of(firstName, lastName, email, passwordEncoder.encode(password)));
    }
}
