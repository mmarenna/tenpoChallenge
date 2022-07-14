package com.tenpo.adder.user.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tenpo.adder.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> users;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

}
