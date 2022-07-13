package com.tenpo.adder.user.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tenpo.adder.user.model.User;

import java.util.List;

public class UserResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> users;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    public UserResponse(List<User> users) {
        this.users = users;
    }

    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
