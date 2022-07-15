package com.tenpo.adder.auth.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.adder.auth.rest.dto.AuthResponse;
import com.tenpo.adder.auth.rest.dto.LoginRequest;
import com.tenpo.adder.auth.rest.dto.RegisterRequest;
import com.tenpo.adder.auth.security.CustomUserDetailService;
import com.tenpo.adder.history.service.HistoryService;
import com.tenpo.adder.user.model.User;
import com.tenpo.adder.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.tenpo.adder.utils.ApiTenpoConstants.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomUserDetailService customUserDetailService;

    ObjectMapper objectMapper;

    @MockBean
    private HistoryService historyService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void loginOkTest() throws Exception {

        final AuthResponse authResponse = new AuthResponse(USER_LOGGED_IN);

        final LoginRequest loginRequest = LoginRequest.builder()
                .usernameOrEmail("mmarenna")
                .password("mmarenna123")
                .build();

        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User logged in"))
                .andReturn();

        verify(historyService).createHistory(LOGIN_USER_URI, authResponse.toString());
    }

    @Test
    void registerOkTest() throws Exception {

        final RegisterRequest registerRequest = RegisterRequest.builder()
                .username("matimarenna")
                .email("matimarenna@gmail.com")
                .password("mmarenna123")
                .build();

        final User user = new User();
        user.setId(1L);
        user.setUsername("matimarenna");
        user.setEmail("matimarenna@gmail.com");
        user.setPassword("mmarenna123");

        final AuthResponse authResponse = new AuthResponse(USER_REGISTERED,"/api/users/" + user.getId());

        when(userService.isNotAllowedUsername(registerRequest.getUsername())).thenReturn(false);
        when(userService.isNotAllowedEmail(registerRequest.getEmail())).thenReturn(false);
        when(userService.registerUser(registerRequest)).thenReturn(user);


        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User registered"))
                .andExpect(jsonPath("$.uri").value("/api/users/1"))
                .andReturn();

        verify(userService).isNotAllowedUsername(registerRequest.getUsername());
        verify(userService).isNotAllowedEmail(registerRequest.getEmail());
        verify(historyService).createHistory(REGISTER_USER_URI, authResponse.toString());
    }

    @Test
    void registerNotAllowedUsernameTest() throws Exception {

        final RegisterRequest registerRequest = RegisterRequest.builder()
                .username("matimarenna")
                .email("matimarenna@gmail.com")
                .password("mmarenna123")
                .build();

        when(userService.isNotAllowedUsername(registerRequest.getUsername())).thenReturn(true);

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("This username already registered"))
                .andReturn();

        verify(userService).isNotAllowedUsername(registerRequest.getUsername());
    }

    @Test
    void registerNotAllowedEmailTest() throws Exception {

        final RegisterRequest registerRequest = RegisterRequest.builder()
                .username("matimarenna")
                .email("matimarenna@gmail.com")
                .password("mmarenna123")
                .build();

        when(userService.isNotAllowedUsername(registerRequest.getUsername())).thenReturn(false);
        when(userService.isNotAllowedEmail(registerRequest.getEmail())).thenReturn(true);

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("This user email already registered"))
                .andReturn();

        verify(userService).isNotAllowedUsername(registerRequest.getUsername());
        verify(userService).isNotAllowedEmail(registerRequest.getEmail());
    }



    @Test
    @WithMockUser(value = "testUser")
    void logoutOkTest() throws Exception {

        final AuthResponse authResponse = new AuthResponse(USER_LOGGED_OFF);

        mvc.perform(get("/api/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User logged off"))
                .andReturn();

        verify(historyService).createHistory(LOGOUT_USER_URI, authResponse.toString());
    }

}