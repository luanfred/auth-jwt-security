package com.jwt_security.dtos;

import com.jwt_security.enums.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        RoleName role
) {

}
