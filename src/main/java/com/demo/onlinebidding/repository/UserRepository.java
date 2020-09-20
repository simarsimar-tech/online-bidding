package com.demo.onlinebidding.repository;

import com.demo.onlinebidding.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserNameAndPassword(String username, String password);

    User findByToken(String token);

}
