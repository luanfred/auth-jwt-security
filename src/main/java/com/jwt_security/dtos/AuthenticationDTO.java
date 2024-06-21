package com.jwt_security.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull
        String email,
        @NotNull
        String password) {
}
