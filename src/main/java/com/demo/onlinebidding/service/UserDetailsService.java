package com.demo.onlinebidding.service;

import com.demo.onlinebidding.model.LoginResponse;
import com.demo.onlinebidding.model.User;
import com.demo.onlinebidding.model.Users;
import com.demo.onlinebidding.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.UUID;

@Component
public class UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

    private UserRepository userRepository;

    @Inject
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public LoginResponse login(String username, String password) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameAndPassword(username, password);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        LOGGER.info("Loaded User By name : {}", username);
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        User saved = userRepository.save(user);
        return new LoginResponse(saved.getToken());
    }


    public UserDetails findByToken(String token) {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new Users(user);
    }


}
