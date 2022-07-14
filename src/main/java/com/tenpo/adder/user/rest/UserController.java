package com.tenpo.adder.user.rest;

import com.tenpo.adder.user.rest.dto.UserResponse;
import com.tenpo.adder.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getUsers() {
        final UserResponse userResponse = UserResponse.builder()
                            .users(Collections.unmodifiableList(userService.findAll()))
                            .build();
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        final UserResponse userResponse = UserResponse.builder()
                .user(userService.findUserById(userId))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

}
