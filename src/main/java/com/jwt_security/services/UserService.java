package com.jwt_security.services;

import com.jwt_security.dtos.AuthenticationDTO;
import com.jwt_security.dtos.UserRequestDTO;
import com.jwt_security.dtos.UserResponseDTO;
import com.jwt_security.entities.User;
import com.jwt_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.EncodingException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        if (this.userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        try{
            String password = passwordEncoder.encode(userRequestDTO.password());
            var user = User.builder()
                    .email(userRequestDTO.email())
                    .password(password)
                    .roles(userRequestDTO.role())
                    .build();
            this.userRepository.save(user);
            return new UserResponseDTO(
                    user.getEmail(),
                    user.getPassword()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
