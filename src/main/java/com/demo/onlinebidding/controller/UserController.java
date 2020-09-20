package com.demo.onlinebidding.controller;

import com.demo.onlinebidding.model.LoginResponse;
import com.demo.onlinebidding.service.UserDetailsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
@Controller
public class UserController {

    private UserDetailsService userDetailsService;

    @Inject
    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> getToken(@RequestParam("user_name") final String username,
                                         @RequestParam("password") final String password) {

        LoginResponse loginResponse = userDetailsService.login(username, password);
        return new ResponseEntity(loginResponse, HttpStatus.ACCEPTED);
    }
}
