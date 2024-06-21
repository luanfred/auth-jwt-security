package com.jwt_security.dtos;

import java.util.List;

public record UserResponseDTO(
        String email,
        String password
) {

}
