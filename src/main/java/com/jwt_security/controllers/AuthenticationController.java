package com.jwt_security.controllers;

import com.jwt_security.dtos.AuthenticationDTO;
import com.jwt_security.dtos.LoginResponseDTO;
import com.jwt_security.dtos.UserRequestDTO;
import com.jwt_security.entities.User;
import com.jwt_security.services.TokenService;
import com.jwt_security.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            var userResponse = this.userService.register(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
        try {
            var usernamaPassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
            var auth = authenticationManager.authenticate(usernamaPassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok().body(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
