package com.tenpo.adder.auth.rest;

import com.tenpo.adder.auth.rest.dto.AuthResponse;
import com.tenpo.adder.auth.rest.dto.LoginRequest;
import com.tenpo.adder.auth.rest.dto.RegisterRequest;
import com.tenpo.adder.history.service.HistoryService;
import com.tenpo.adder.user.model.User;
import com.tenpo.adder.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.tenpo.adder.utils.ApiTenpoConstants.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final HistoryService historyService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, HistoryService historyService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.historyService = historyService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        log.info("Logging user: {}", loginRequest.getUsernameOrEmail());

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final AuthResponse authResponse = new AuthResponse(USER_LOGGED_IN);

        this.historyService.createHistory(LOGIN_USER_URI,  authResponse.toString());
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/logout")
    public ResponseEntity<AuthResponse> logoutUser() {
        log.info("Logging out user");
        SecurityContextHolder.getContext().setAuthentication(null);
        final AuthResponse authResponse = new AuthResponse(USER_LOGGED_OFF);
        this.historyService.createHistory(LOGOUT_USER_URI, authResponse.toString());

        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        if(userService.isNotAllowedUsername(registerRequest.getUsername())) {
            return this.saveHistory(USERNAME_ALREADY_REGISTERED);
        }
        if(userService.isNotAllowedEmail(registerRequest.getEmail())) {
            return this.saveHistory(EMAIL_ALREADY_REGISTERED);
        }
        log.info("Logging user with username: {} and email: {}", registerRequest.getUsername(), registerRequest.getEmail());

        User user = userService.registerUser(registerRequest);
        final AuthResponse authResponse = new AuthResponse(USER_REGISTERED,"/api/users/" + user.getId());
        this.historyService.createHistory(REGISTER_USER_URI, authResponse.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    private ResponseEntity<AuthResponse> saveHistory(String message){
        this.historyService.createHistory(REGISTER_USER_URI);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(message));
    }

}
