package com.flcode.api_user_flcode.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private Integer codResponse;
    private String messageResponse;
    private String codEntity;
}
