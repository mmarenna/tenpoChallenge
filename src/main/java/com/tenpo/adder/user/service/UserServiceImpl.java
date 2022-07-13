package com.tenpo.adder.user.service;

import com.tenpo.adder.auth.rest.dto.RegisterDTO;
import com.tenpo.adder.exception.ResourceNotFoundException;
import com.tenpo.adder.user.model.Role;
import com.tenpo.adder.user.model.User;
import com.tenpo.adder.user.repository.RoleRepository;
import com.tenpo.adder.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.tenpo.adder.user.repository.RoleRepository.ROLE_USER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    @Transactional
    public User registerUser(RegisterDTO registerDTO) {
        return userRepository.save(mapUser(registerDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isValidUsername(String username) {
            return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isValidEmail(String email) {
            return userRepository.existsByEmail(email);
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role roles = roleRepository.findByName(ROLE_USER).get();
        user.setRoles(Collections.singleton(roles));
        return user;
    }

}
