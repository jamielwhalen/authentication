package com.authentication.auth;

import com.authentication.auth.data.Users;
import com.authentication.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
@Slf4j
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping(value="/hello")
    public String hello() {
        return "hello";
    }

    record RegisterRequest(
            String firstName,
            String lastName,
            String email,
            String password,
            String passwordConfirm) {}

    record RegisterResponse(
            Long id,
            String firstName,
            String lastName,
            String email) {}

    @PostMapping(value = "/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest){

        Users user =  authService.register(registerRequest.firstName(),
                        registerRequest.lastName(),
                        registerRequest.email(),
                        registerRequest.password(),
                registerRequest.passwordConfirm());
        return new RegisterResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }


}
