package com.tenpo.adder.user.service;

import com.tenpo.adder.auth.rest.dto.RegisterRequest;
import com.tenpo.adder.user.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(Long id);

    User registerUser(RegisterRequest user);

    Boolean isNotAllowedUsername(String username);

    Boolean isNotAllowedEmail(String email);

}
