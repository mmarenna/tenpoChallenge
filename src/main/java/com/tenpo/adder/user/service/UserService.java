package com.tenpo.adder.user.service;

import com.tenpo.adder.auth.rest.dto.RegisterDTO;
import com.tenpo.adder.user.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(Long id);

    User registerUser(RegisterDTO user);

    Boolean isValidUsername(String username);

    Boolean isValidEmail(String email);

}
