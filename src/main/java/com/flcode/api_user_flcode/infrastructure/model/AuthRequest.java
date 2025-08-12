package com.flcode.api_user_flcode.infrastructure.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank(message = "mail not blank")
    private String email;

    @NotBlank(message = "password not blank")
    private String password;
}
