package com.example.JWT.controller;

import com.example.JWT.model.AuthModel;
import com.example.JWT.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public AuthModel userSignIn(@RequestBody AuthModel authModel){
        return authService.signIn(authModel);
    }

    @PostMapping("/logIn")
    public String userLogIn(@RequestBody AuthModel authModel){
        String token = authService.logIn(authModel);
        if (token != null) {
            return token;
        } else {
            System.out.println("Error");
            throw new RuntimeException("Invalid credentials");
        }
    }

}
